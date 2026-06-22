package com.coolxer.service.dih.agent;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import com.coolxer.service.dih.agent.nl2sql.request.SearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

// 应该考虑如何将现有的ck 的表让LLM理解

@Service
public class AIAgentService {
    private static final Logger log = LoggerFactory.getLogger(AIAgentService.class);


    @Autowired
    @Qualifier("redisVectorManagementService")
    private RedisVectorManagementService redisVectorManagementService;

    private ReactAgent inspectionAgent;

    public AIAgentService(@Value("${spring.ai.dashscope.api-key}") String dashscopeApiKey) {
        DashScopeApi dashScopeApi = DashScopeApi.builder()
                .apiKey(dashscopeApiKey)
                .build();

//        ToolCallback databaseQueryToolCallback = FunctionToolCallback
//                .builder("databaseQuery", new DatabaseQueryTool())
//                .description("A tool to query the database with SQL statements")
//                .inputType(DatabaseQueryToolRequest.class)
//                .build();
        ChatModel chatModel = DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .build();
        inspectionAgent = ReactAgent.builder()
//                .tools(databaseQueryToolCallback)
                .name("inspection_agent")
                .model(chatModel)
                .build();
    }

    public String chat(String promot) {
        AssistantMessage response;
        try {
            response = inspectionAgent.call(promot);
        } catch (GraphRunnerException e) {
            throw new RuntimeException(e);
        }
        if (response == null) {
            return "";
        }
        return response.getText();
    }

    // -------------------- 下面为测试的代码

    public void buildSchemaVector() {
        redisVectorManagementService.schema();
    }

    public void simlaritySearch(String query) {
        SearchRequest req = new SearchRequest();
        req.setQuery(query);
        req.setTopK(100);
        req.setVectorType("table");
        req.setName(query);
        List<Document> docs = redisVectorManagementService.searchWithVectorType(req);
        log.info("Simlarity search results: {}", docs);
    }
}

