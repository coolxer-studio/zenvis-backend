package com.coolxer.service.dih.agent;

import com.coolxer.service.dih.agent.nl2sql.connector.config.DbConfig;
import com.coolxer.service.dih.agent.nl2sql.request.SearchRequest;
import com.coolxer.service.dih.agent.nl2sql.service.base.BaseSchemaService;
import com.coolxer.service.dih.agent.nl2sql.service.base.BaseVectorStoreService;
import com.google.gson.Gson;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("redisSchemaService")
public class RedisSchemaService extends BaseSchemaService {
    public RedisSchemaService(DbConfig dbConfig, Gson gson,
                              @Qualifier("redisVectorManagementService") BaseVectorStoreService vectorStoreService) {
        super(dbConfig, gson, vectorStoreService);
    }

    /**
     *
     * @param tableDocuments 结果塞到这个字段里
     * @param tableName
     * @param vectorType
     */
    @Override
    protected void addTableDocument(List<Document> tableDocuments, String tableName, String vectorType) {
        handleDocumentQuery(tableDocuments, tableName, vectorType, name -> {
            SearchRequest req = new SearchRequest();
            req.setName(name);
            return req;
        }, vectorStoreService::searchTableByNameAndVectorType);
    }

    /**
     *
     * @param weightedColumns
     * @param columnName
     * @param vectorType
     */
    @Override
    protected void addColumnsDocument(Map<String, Document> weightedColumns, String columnName, String vectorType) {
        handleDocumentQuery(weightedColumns, columnName, vectorType, name -> {
            SearchRequest req = new SearchRequest();
            req.setName(name);
            return req;
        }, vectorStoreService::searchTableByNameAndVectorType);
    }
}
