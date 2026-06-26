package com.coolxer.service.core;

import com.coolxer.model.retrieval.meta.MetaData;

/**
 * clickhouse数据库操作服务
 */
public interface ClickhouseSchemeService {
    /**
     * 初始化scheme
     */
    public void initScheme(String initSqlPath);

    /**
     * 通过MetaData数据初始化
     *
     * @param metaData
     */
    public void loadSchemeFromMetaData(MetaData metaData);

    /**
     * 删除表
     *
     * @param tableName
     */
    void deleteTable(String tableName);
}
