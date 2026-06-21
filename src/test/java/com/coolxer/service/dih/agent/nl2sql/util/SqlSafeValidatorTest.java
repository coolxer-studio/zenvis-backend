package com.coolxer.service.dih.agent.nl2sql.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SqlSafeValidatorTest {

    // ===== 空值和空白 =====

    @Test
    @DisplayName("null SQL 应被拒绝")
    void testNullSql() {
        SqlValidationResult result = SqlSafeValidator.validate(null);
        assertFalse(result.isValid());
        assertTrue(result.getRejectionReason().contains("空"));
    }

    @Test
    @DisplayName("空字符串应被拒绝")
    void testEmptySql() {
        SqlValidationResult result = SqlSafeValidator.validate("");
        assertFalse(result.isValid());
    }

    @Test
    @DisplayName("纯空白字符串应被拒绝")
    void testBlankSql() {
        SqlValidationResult result = SqlSafeValidator.validate("   \n\t  ");
        assertFalse(result.isValid());
    }

    // ===== 正常 SELECT 查询（应通过）=====

    @ParameterizedTest
    @ValueSource(strings = {
            "SELECT * FROM events",
            "select * from events",
            "  SELECT 1",
            "SELECT col1, col2 FROM table1 WHERE id = 1",
            "SELECT COUNT(*) FROM events GROUP BY type",
            "SELECT * FROM events ORDER BY created_at DESC LIMIT 100",
            "WITH cte AS (SELECT * FROM t1) SELECT * FROM cte",
            "with cte as (select * from t1) select * from cte",
            "SELECT * FROM events WHERE name LIKE '%test%'",
            "SELECT e.*, r.name FROM events e JOIN regions r ON e.region_id = r.id",
            "SELECT * FROM events WHERE id IN (SELECT event_id FROM mappings)",
            "SELECT * FROM events WHERE date >= '2024-01-01' AND date <= '2024-12-31'",
            "SELECT * FROM events SETTINGS max_threads = 4",
            "SELECT * FROM events FORMAT JSON",
            "SELECT arrayJoin(label) as distinct_label FROM asset_host WHERE label IS NOT NULL",
            "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_host GROUP BY group_key",
    })
    @DisplayName("正常 SELECT 查询应通过校验")
    void testValidSelectQueries(String sql) {
        assertTrue(SqlSafeValidator.isSafe(sql), "SQL should be safe: " + sql);
    }

    // ===== 白名单：非 SELECT/WITH 开头 =====

    @ParameterizedTest
    @ValueSource(strings = {
            "INSERT INTO t VALUES (1)",
            "UPDATE t SET a = 1",
            "DELETE FROM t",
            "DROP TABLE t",
            "CREATE TABLE t (a Int32)",
            "EXPLAIN SELECT * FROM t",
    })
    @DisplayName("非 SELECT/WITH 开头的语句应被拒绝")
    void testNonSelectStatements(String sql) {
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
    }

    // ===== 黑名单：DDL 关键词在子查询中 =====

    @ParameterizedTest
    @ValueSource(strings = {
            "SELECT * FROM (CREATE TABLE t (a Int32))",
            "SELECT * FROM t WHERE 1 = (DROP TABLE t)",
            "WITH cte AS (ALTER TABLE t ADD COLUMN b Int32) SELECT * FROM cte",
    })
    @DisplayName("子查询中包含 DDL 关键词应被拒绝")
    void testDdlInSubquery(String sql) {
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
        assertTrue(result.getRejectionReason().contains("禁止的关键词"));
    }

    // ===== 黑名单：DML 关键词在子查询中 =====

    @ParameterizedTest
    @ValueSource(strings = {
            "SELECT * FROM t WHERE id IN (INSERT INTO t VALUES (1))",
            "SELECT * FROM t WHERE (UPDATE t SET a = 1)",
            "SELECT * FROM t WHERE (DELETE FROM t)",
    })
    @DisplayName("子查询中包含 DML 关键词应被拒绝")
    void testDmlInSubquery(String sql) {
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
    }

    // ===== 分号/多语句 =====

    @ParameterizedTest
    @ValueSource(strings = {
            "SELECT 1; DROP TABLE t",
            "SELECT * FROM t; INSERT INTO t VALUES (1)",
    })
    @DisplayName("包含分号的多语句应被拒绝")
    void testSemicolon(String sql) {
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
        assertTrue(result.getRejectionReason().contains("分号"));
    }

    // ===== 注释剥离绕过 =====

    @Test
    @DisplayName("注释剥离后暴露的 DELETE 关键词应被检测到")
    void testDeleteExposedAfterCommentStripped() {
        String sql = "SELECT * FROM events WHERE 1 = 1 AND /* safe */ DELETE FROM events";
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
    }

    @Test
    @DisplayName("注释剥离后暴露的 DROP 关键词应被检测到")
    void testDropExposedAfterCommentStripped() {
        String sql = "SELECT * FROM events WHERE /* safe */ DROP TABLE events";
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
    }

    @Test
    @DisplayName("正常注释不影响校验")
    void testNormalComment() {
        String sql = "SELECT * FROM events /* 这是注释 */ WHERE id = 1 -- 行尾注释";
        assertTrue(SqlSafeValidator.isSafe(sql));
    }

    // ===== SELECT INTO OUTFILE =====

    @Test
    @DisplayName("SELECT INTO OUTFILE 应被拒绝")
    void testSelectIntoOutfile() {
        String sql = "SELECT * FROM events INTO OUTFILE '/tmp/data.csv'";
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
        assertTrue(result.getRejectionReason().contains("OUTFILE"));
    }

    // ===== SELECT INTO FORMAT =====

    @Test
    @DisplayName("SELECT INTO FORMAT 应被拒绝")
    void testSelectIntoFormat() {
        String sql = "SELECT * FROM events INTO FORMAT JSON";
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
        assertTrue(result.getRejectionReason().contains("FORMAT"));
    }

    // ===== 危险表函数 =====

    @ParameterizedTest
    @ValueSource(strings = {
            "SELECT * FROM file('data.csv', CSV)",
            "SELECT * FROM url('http://evil.com/data')",
            "SELECT * FROM mysql('host', 'db', 'table', 'user', 'pass')",
            "SELECT * FROM clickhouse('host', 'db', 'table')",
            "SELECT * FROM exec('rm -rf /')",
    })
    @DisplayName("危险表函数应被拒绝")
    void testDangerousTableFunctions(String sql) {
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
        assertTrue(result.getRejectionReason().contains("表函数"));
    }

    // ===== 系统表访问 =====

    @ParameterizedTest
    @ValueSource(strings = {
            "SELECT * FROM system.tables",
            "SELECT * FROM system.users",
            "SELECT * FROM system . tables",
            "SELECT * FROM system.query_log WHERE query LIKE '%password%'",
    })
    @DisplayName("访问 system 系统表应被拒绝")
    void testSystemTableAccess(String sql) {
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
        assertTrue(result.getRejectionReason().contains("system"));
    }

    // ===== KILL 操作 =====

    @Test
    @DisplayName("KILL 操作应被拒绝")
    void testKillQuery() {
        String sql = "SELECT * FROM t WHERE id = (KILL QUERY 'abc')";
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
    }

    // ===== GRANT/REVOKE =====

    @Test
    @DisplayName("GRANT/REVOKE 操作应被拒绝")
    void testGrantRevoke() {
        SqlValidationResult result1 = SqlSafeValidator.validate("SELECT * FROM (GRANT ALL ON * TO 'user')");
        assertFalse(result1.isValid());

        SqlValidationResult result2 = SqlSafeValidator.validate("SELECT 1 WHERE (REVOKE ALL ON * FROM 'user')");
        assertFalse(result2.isValid());
    }

    // ===== 单词边界防误杀 =====

    @Test
    @DisplayName("列名包含关键词子串不应被误杀")
    void testKeywordInIdentifier() {
        String sql = "SELECT description, selection FROM events";
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertTrue(result.isValid(),
                "Column name 'description' should not trigger keyword filter. Reason: "
                        + result.getRejectionReason());
    }

    // ===== 大小写混合绕过 =====

    @Test
    @DisplayName("大小写混合 + 注释剥离后暴露关键词应被拦截")
    void testMixedCaseCommentBypass() {
        String sql = "SeLeCt 1 FROM events WHERE /* safe */ DrOp TaBlE t";
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
    }

    // ===== isSafe 便捷方法 =====

    @Test
    @DisplayName("isSafe 便捷方法应正确返回 true")
    void testIsSafeTrue() {
        assertTrue(SqlSafeValidator.isSafe("SELECT 1"));
    }

    @Test
    @DisplayName("isSafe 便捷方法应正确返回 false")
    void testIsSafeFalse() {
        assertFalse(SqlSafeValidator.isSafe("DROP TABLE t"));
    }

    // ===== 复杂绕过尝试 =====

    @Test
    @DisplayName("多层嵌套注释绕过尝试应被拦截")
    void testNestedCommentBypass() {
        String sql = "SELECT 1 /* outer /* inner */ DROP TABLE t */ FROM events";
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
    }

    @Test
    @DisplayName("TRUNCATE 在子查询中应被拦截")
    void testTruncateInSubquery() {
        String sql = "SELECT * FROM t WHERE (TRUNCATE TABLE t)";
        SqlValidationResult result = SqlSafeValidator.validate(sql);
        assertFalse(result.isValid());
    }
}
