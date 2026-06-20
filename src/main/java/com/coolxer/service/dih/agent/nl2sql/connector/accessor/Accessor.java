/*
 * Copyright 2024-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coolxer.service.dih.agent.nl2sql.connector.accessor;

import com.coolxer.service.dih.agent.nl2sql.connector.bo.*;
import com.coolxer.service.dih.agent.nl2sql.connector.config.DbConfig;

import java.util.List;

/**
 * Data access interface definition
 */
public interface Accessor {

    /**
     * 数据库访问方法入口
     *
     * @param dbConfig {@link DbConfig} 数据库配置信息
     * @param method   {@link String} 访问方法名称
     * @param param    {@link DbQueryParameter} 请求参数信息
     * @return 访问结果
     */
    <T> T accessDb(DbConfig dbConfig, String method, DbQueryParameter param) throws Exception;

    /**
     * 获取数据库列表
     */
    List<DatabaseInfoBO> showDatabases(DbConfig dbConfig) throws Exception;

    /**
     * 获取 schema 列表
     */
    List<DatabaseInfoBO> showSchemas(DbConfig dbConfig) throws Exception;

    /**
     * 获取表列表
     */
    List<TableInfoBO> showTables(DbConfig dbConfig, DbQueryParameter param) throws Exception;

    /**
     * 获取表详细信息
     */
    List<TableInfoBO> fetchTables(DbConfig dbConfig, DbQueryParameter param) throws Exception;

    /**
     * 获取列信息
     */
    List<ColumnInfoBO> showColumns(DbConfig dbConfig, DbQueryParameter param) throws Exception;

    /**
     * 获取外键信息
     */
    List<ForeignKeyInfoBO> showForeignKeys(DbConfig dbConfig, DbQueryParameter param) throws Exception;

    /**
     * 采样列数据
     */
    List<String> sampleColumn(DbConfig dbConfig, DbQueryParameter param) throws Exception;

    /**
     * 扫描表数据
     */
    ResultSetBO scanTable(DbConfig dbConfig, DbQueryParameter param) throws Exception;

    /**
     * 执行 SQL 并返回结果
     */
    ResultSetBO executeSqlAndReturnObject(DbConfig dbConfig, DbQueryParameter param) throws Exception;

}
