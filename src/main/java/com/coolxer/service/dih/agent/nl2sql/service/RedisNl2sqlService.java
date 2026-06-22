package com.coolxer.service.dih.agent.nl2sql.service;

import com.coolxer.service.dih.agent.nl2sql.connector.accessor.Accessor;
import com.coolxer.service.dih.agent.nl2sql.connector.bo.DbQueryParameter;
import com.coolxer.service.dih.agent.nl2sql.connector.bo.ResultSetBO;
import com.coolxer.service.dih.agent.nl2sql.connector.config.DbConfig;
import com.coolxer.service.dih.agent.nl2sql.service.base.BaseNl2SqlService;
import com.coolxer.service.dih.agent.nl2sql.service.base.BaseSchemaService;
import com.coolxer.service.dih.agent.nl2sql.service.base.BaseVectorStoreService;
import com.coolxer.service.dih.agent.dto.EChartsIntention;
import com.coolxer.service.dih.agent.prompt.PromptHelper;
import org.springframework.ai.chat.messages.Message;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 使用Redis 作为向量存储的nl2sql 服务
 */
@Slf4j
@Service("redisNl2sqlService")
public class RedisNl2sqlService extends BaseNl2SqlService {

    @Autowired
    private ObjectMapper objectMapper;

    public RedisNl2sqlService(@Qualifier("redisVectorManagementService") BaseVectorStoreService vectorStoreService,
                              @Qualifier("redisSchemaService") BaseSchemaService schemaService, LlmService aiService,
                              Accessor dbAccessor, DbConfig dbConfig) {
        super(vectorStoreService, schemaService, aiService, dbAccessor, dbConfig);
    }

    /**
     * 根据用户的query 判断是否需要进行ECharts渲染
     *
     * @param query 用户输入的查询
     */
    public EChartsIntention needRenderECharts(String query) {
        return needRenderECharts(query, null);
    }

    /**
     * 带对话历史的 ECharts 意图识别
     * @param query 用户当前输入
     * @param conversationHistory 对话历史（可为 null）
     */
    public EChartsIntention needRenderECharts(String query, List<Message> conversationHistory) {
        String intention = aiService.call(PromptHelper.buildEChartsIntentionPrompt(query, conversationHistory));
        log.info("ECharts 意图识别结果: {}", intention);
        try {
            return objectMapper.readValue(intention, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("ECharts 意图识别结果解析失败: ", e);
        }
        return new EChartsIntention(false);
    }

    public ResultSetBO executeSqlOri(String sql) throws Exception {
        log.info("Executing SQL: {}", sql);
        try {
            DbQueryParameter param = DbQueryParameter.from(dbConfig).setSql(sql);
            log.debug("Created DbQueryParameter for SQL execution");
            ResultSetBO resultSet = dbAccessor.executeSqlAndReturnObject(dbConfig, param);
            log.debug("SQL executed successfully, generating table format");
            return resultSet;
        }
        catch (Exception e) {
            log.error("Failed to execute SQL: {}", sql, e);
            throw e;
        }
    }
}
