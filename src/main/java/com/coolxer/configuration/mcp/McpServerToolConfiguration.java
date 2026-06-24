package com.coolxer.configuration.mcp;

import com.coolxer.controller.retrieval.RetrievalMcpTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * MCP服务器工具配置
 * 显式注册所有带有@Tool注解的方法
 */
@Slf4j
@Configuration
public class McpServerToolConfiguration {

    @Bean
    public MethodToolCallbackProvider retrievalToolCallbackProvider(RetrievalMcpTool retrievalMcpTool) {
        log.info("=== Creating MethodToolCallbackProvider for RetrievalMcpTool ===");
        
        // 扫描并记录所有 @Tool 方法
        Method[] methods = RetrievalMcpTool.class.getDeclaredMethods();
        for (Method method : methods) {
            Tool toolAnnotation = method.getAnnotation(Tool.class);
            if (toolAnnotation != null) {
                log.info("Found @Tool method: name={}, description={}", 
                    toolAnnotation.name(), toolAnnotation.description());
            }
        }
        
        // 创建 MethodToolCallbackProvider
        MethodToolCallbackProvider provider = MethodToolCallbackProvider.builder()
                .toolObjects(retrievalMcpTool)
                .build();
        
        log.info("=== MethodToolCallbackProvider created successfully ===");
        return provider;
    }
}