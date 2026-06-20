package com.coolxer.service.dih.agent.nl2sql.connector.accessor;

import com.coolxer.service.dih.agent.nl2sql.connector.bo.*;
import com.coolxer.service.dih.agent.nl2sql.connector.config.DbConfig;
import com.coolxer.service.dih.agent.nl2sql.util.SqlSafeValidator;
import com.coolxer.service.dih.agent.nl2sql.util.SqlValidationResult;
import com.clickhouse.jdbc.ClickHouseConnection;
import com.clickhouse.jdbc.ClickHouseDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

@Slf4j
public class ClickHouseAccessor implements Accessor {

    @Value("${spring.datasource.clickhouse.jdbc-url}")
    private String url;
    @Value("${spring.datasource.clickhouse.username}")
    private String user;
    @Value("${spring.datasource.clickhouse.password}")
    private String password;
    @Value("${spring.datasource.clickhouse.socket-timeout:5000}")
    private int socketTimeout;

    @Override
    public <T> T accessDb(DbConfig dbConfig, String method, DbQueryParameter param) throws Exception {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public List<DatabaseInfoBO> showDatabases(DbConfig dbConfig) throws Exception {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public List<DatabaseInfoBO> showSchemas(DbConfig dbConfig) throws Exception {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public List<TableInfoBO> showTables(DbConfig dbConfig, DbQueryParameter param) throws Exception {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public List<TableInfoBO> fetchTables(DbConfig dbConfig, DbQueryParameter param) throws Exception {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public List<ColumnInfoBO> showColumns(DbConfig dbConfig, DbQueryParameter param) throws Exception {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public List<ForeignKeyInfoBO> showForeignKeys(DbConfig dbConfig, DbQueryParameter param) throws Exception {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public List<String> sampleColumn(DbConfig dbConfig, DbQueryParameter param) throws Exception {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public ResultSetBO scanTable(DbConfig dbConfig, DbQueryParameter param) throws Exception {
        throw new UnsupportedOperationException("unsupported operation");
    }

    /**
     * 执行sql, 直接使用原生的方式进行查询
     *
     * @param dbConfig
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ResultSetBO executeSqlAndReturnObject(DbConfig dbConfig, DbQueryParameter param) throws Exception {
        String sql = param.getSql();

        // 深度防御：在执行层再次校验 SQL 安全性
        SqlValidationResult validationResult = SqlSafeValidator.validate(sql);
        if (!validationResult.isValid()) {
            log.error("深度防御拦截：SQL 未通过安全校验，拒绝执行。原因: {}，SQL: {}",
                    validationResult.getRejectionReason(), sql);
            throw new SecurityException("SQL 未通过安全校验: " + validationResult.getRejectionReason());
        }

        Properties p = new Properties();
        p.setProperty("user", user);
        p.setProperty("password", password);
        p.setProperty("socket_timeout", String.valueOf(socketTimeout));

        ResultSetBO resultSetBO = new ResultSetBO();

        try (ClickHouseConnection conn =
                     new ClickHouseDataSource(url, p).getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            List<String> columns = new ArrayList<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                columns.add(md.getColumnName(i));   // 列名
            }
            resultSetBO.setColumn(columns);

            List<Map<String, String>> data = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> row = new LinkedHashMap<>(columnCount);
                for (String col : columns) {
                    row.put(col, rs.getObject(col).toString()); // 原始值
                }
                data.add(row);
            }
            resultSetBO.setData(data);
        }
        return resultSetBO;
    }
}