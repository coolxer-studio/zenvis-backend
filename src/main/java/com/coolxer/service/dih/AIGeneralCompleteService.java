package com.coolxer.service.dih;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.api.DashScopeResponseFormat;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.dashscope.spec.DashScopeModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * AI自动补全提示服务
 */

@Service
public class AIGeneralCompleteService {

    private final ChatClient chatClient;

    public AIGeneralCompleteService(
            @Value("${spring.ai.dashscope.api-key}") String dashscopeApiKey,
            @Qualifier("completeSystemPromptTemplate") PromptTemplate systemPromptTemplate
    ) {

        DashScopeApi dashScopeApi = DashScopeApi.builder()
                .apiKey(dashscopeApiKey)
                .build();

        ChatModel chatModel = DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .build();
        this.chatClient = ChatClient.builder(chatModel)
                .defaultSystem(
                        systemPromptTemplate.getTemplate()
                ).defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    public String complete(String prompt) {
        String model = DashScopeModel.ChatModel.QWEN_PLUS.getValue();
        var runtimeOptions = DashScopeChatOptions.builder()
                .withModel(model)
                .withTemperature(0.8)
                .withResponseFormat(DashScopeResponseFormat.builder()
                        .type(DashScopeResponseFormat.Type.TEXT)
                        .build()
                ).build();

        return chatClient.prompt()
                .options(runtimeOptions)
                .user(prompt)
                .call()
                .content();
    }
}
