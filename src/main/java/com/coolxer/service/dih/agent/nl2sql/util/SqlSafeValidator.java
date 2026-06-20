package com.coolxer.service.dih.agent.nl2sql.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL 安全校验工具类。
 * <p>
 * 采用白名单 + 黑名单双重校验策略，对 LLM 生成的 SQL 进行安全检查，
 * 防止注入攻击和危险操作。纯工具类，无 Spring 依赖。
 * </p>
 * <p>
 * 校验流水线：空值检查 → 注释剥离 → 分号检查 → 白名单(语句类型) →
 * 关键词黑名单(DDL/DML/CL/DCL) → 危险模式 → 危险表函数 → 系统表访问
 * </p>
 */
@Slf4j
public final class SqlSafeValidator {

    private SqlSafeValidator() {
    }

    // ========== 注释剥离 ==========

    /** 多行注释: block comment style */
    private static final Pattern MULTI_LINE_COMMENT_PATTERN =
            Pattern.compile("/\\*.*?\\*/", Pattern.DOTALL);

    /** 单行注释: -- ... 或 # ... */
    private static final Pattern SINGLE_LINE_COMMENT_PATTERN =
            Pattern.compile("--[^\\n\\r]*|#[^\\n\\r]*");

    // ========== 分号检查 ==========

    private static final Pattern SEMICOLON_PATTERN = Pattern.compile(";");

    // ========== 白名单：语句类型 ==========

    private static final Pattern ALLOWED_STATEMENT_PATTERN =
            Pattern.compile("^\\s*(SELECT|WITH)\\b", Pattern.CASE_INSENSITIVE);

    // ========== 黑名单：DDL/DML/CL/DCL 关键词 ==========

    private static final Set<String> FORBIDDEN_KEYWORDS = Set.of(
            // DDL
            "CREATE", "DROP", "ALTER", "TRUNCATE", "RENAME",
            // DML
            "INSERT", "UPDATE", "DELETE", "REPLACE", "MERGE",
            // ClickHouse CL
            "KILL",
            // DCL
            "GRANT", "REVOKE"
    );

    private static final Pattern FORBIDDEN_KEYWORDS_PATTERN = buildForbiddenKeywordsPattern();

    // ========== 危险模式 ==========

    /** SELECT ... INTO OUTFILE — 可写入服务器文件系统 */
    private static final Pattern SELECT_INTO_OUTFILE_PATTERN =
            Pattern.compile("\\bSELECT\\b.*\\bINTO\\s+OUTFILE\\b", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    /** SELECT ... INTO FORMAT — ClickHouse 特有，可导出数据 */
    private static final Pattern SELECT_INTO_FORMAT_PATTERN =
            Pattern.compile("\\bSELECT\\b.*\\bINTO\\s+FORMAT\\b", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    // ========== 危险表函数 ==========

    private static final Set<String> DANGEROUS_TABLE_FUNCTIONS = Set.of(
            "file(",        // 读写服务器文件
            "url(",         // 访问任意 URL
            "mysql(",       // 访问远程 MySQL
            "postgresql(",  // 访问远程 PostgreSQL
            "sqlite(",      // 访问本地 SQLite
            "clickhouse(",  // 访问远程 ClickHouse
            "exec("         // 执行系统命令
    );

    // ========== 系统表访问 ==========

    private static final Pattern SYSTEM_TABLE_PATTERN =
            Pattern.compile("\\bsystem\\s*\\.\\s*\\w+", Pattern.CASE_INSENSITIVE);

    // ========== 公开 API ==========

    /**
     * 校验 SQL 是否安全，可安全执行。
     *
     * @param sql 待校验的 SQL 语句
     * @return 校验结果，包含是否通过及拒绝原因
     */
    public static SqlValidationResult validate(String sql) {
        if (sql == null || sql.trim().isEmpty()) {
            return SqlValidationResult.reject("SQL 为空");
        }

        String originalSql = sql;
        String normalizedSql = sql.trim();

        // 剥离注释
        String strippedSql = stripComments(normalizedSql);

        // 分号检查
        SqlValidationResult semicolonResult = checkSemicolon(strippedSql);
        if (!semicolonResult.isValid()) {
            return semicolonResult;
        }

        // 白名单：语句类型
        SqlValidationResult statementResult = checkStatementType(strippedSql);
        if (!statementResult.isValid()) {
            return statementResult;
        }

        // 黑名单：关键词全文扫描
        SqlValidationResult keywordResult = checkForbiddenKeywords(strippedSql);
        if (!keywordResult.isValid()) {
            return keywordResult;
        }

        // 危险模式
        SqlValidationResult patternResult = checkDangerousPatterns(strippedSql);
        if (!patternResult.isValid()) {
            return patternResult;
        }

        // 危险表函数
        SqlValidationResult functionResult = checkDangerousTableFunctions(strippedSql);
        if (!functionResult.isValid()) {
            return functionResult;
        }

        // 系统表访问
        SqlValidationResult systemTableResult = checkSystemTableAccess(strippedSql);
        if (!systemTableResult.isValid()) {
            return systemTableResult;
        }

        log.debug("SQL 安全校验通过: {}", originalSql);
        return SqlValidationResult.ok();
    }

    /**
     * 便捷方法：校验 SQL 是否安全。
     */
    public static boolean isSafe(String sql) {
        return validate(sql).isValid();
    }

    // ========== 内部实现 ==========

    private static String stripComments(String sql) {
        String result = MULTI_LINE_COMMENT_PATTERN.matcher(sql).replaceAll(" ");
        result = SINGLE_LINE_COMMENT_PATTERN.matcher(result).replaceAll(" ");
        return result;
    }

    private static SqlValidationResult checkSemicolon(String sql) {
        if (SEMICOLON_PATTERN.matcher(sql).find()) {
            return SqlValidationResult.reject("SQL 包含分号，疑似多语句注入");
        }
        return SqlValidationResult.ok();
    }

    private static SqlValidationResult checkStatementType(String sql) {
        if (!ALLOWED_STATEMENT_PATTERN.matcher(sql).find()) {
            String prefix = sql.length() > 20 ? sql.substring(0, 20) + "..." : sql;
            return SqlValidationResult.reject("SQL 不是以 SELECT/WITH 开头的查询语句，实际开头: " + prefix);
        }
        return SqlValidationResult.ok();
    }

    private static SqlValidationResult checkForbiddenKeywords(String sql) {
        Matcher matcher = FORBIDDEN_KEYWORDS_PATTERN.matcher(sql);
        if (matcher.find()) {
            String found = matcher.group(1);
            return SqlValidationResult.reject("SQL 包含禁止的关键词: " + found.toUpperCase());
        }
        return SqlValidationResult.ok();
    }

    private static SqlValidationResult checkDangerousPatterns(String sql) {
        if (SELECT_INTO_OUTFILE_PATTERN.matcher(sql).find()) {
            return SqlValidationResult.reject("SQL 包含危险的 SELECT INTO OUTFILE 操作");
        }
        if (SELECT_INTO_FORMAT_PATTERN.matcher(sql).find()) {
            return SqlValidationResult.reject("SQL 包含危险的 SELECT INTO FORMAT 操作");
        }
        return SqlValidationResult.ok();
    }

    private static SqlValidationResult checkDangerousTableFunctions(String sql) {
        String lowerSql = sql.toLowerCase();
        for (String func : DANGEROUS_TABLE_FUNCTIONS) {
            if (lowerSql.contains(func.toLowerCase())) {
                return SqlValidationResult.reject("SQL 包含危险的表函数: " + func.replace("(", "()"));
            }
        }
        return SqlValidationResult.ok();
    }

    private static SqlValidationResult checkSystemTableAccess(String sql) {
        if (SYSTEM_TABLE_PATTERN.matcher(sql).find()) {
            return SqlValidationResult.reject("SQL 尝试访问 system 系统表，已被禁止");
        }
        return SqlValidationResult.ok();
    }

    private static Pattern buildForbiddenKeywordsPattern() {
        String keywordsRegex = String.join("|", FORBIDDEN_KEYWORDS);
        return Pattern.compile("\\b(" + keywordsRegex + ")\\b", Pattern.CASE_INSENSITIVE);
    }
}
