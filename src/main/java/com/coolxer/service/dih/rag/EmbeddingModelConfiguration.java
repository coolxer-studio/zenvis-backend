package com.coolxer.service.dih.rag;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.embedding.DashScopeEmbeddingModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Random;

@Configuration
public class EmbeddingModelConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddingModelConfiguration.class);

    @Value("${spring.ai.dashscope.api-key}")
    private String apiKey;

    @Value("${spring.ai.dashscope.embedding.options.dimensions:4096}")
    private int dimensions;

    @Bean("dashscopeEmbeddingModel")
    public EmbeddingModel dashscopeEmbeddingModel() {
        if (isValidApiKey(apiKey)) {
            logger.info("Using DashScope embedding model with valid API key");
            DashScopeApi dashScopeApi = DashScopeApi.builder()
                    .apiKey(apiKey)
                    .build();
            return new DashScopeEmbeddingModel(dashScopeApi);
        }

        logger.warn("API key is not configured (using placeholder). Using local dummy embedding model for development.");
        return new DummyEmbeddingModel(dimensions);
    }

    private boolean isValidApiKey(String key) {
        return key != null && !key.isEmpty() && !key.startsWith("sk-xxxxxxxx");
    }

    public static class DummyEmbeddingModel implements EmbeddingModel {

        private final int dimensions;
        private final Random random = new Random(42);

        public DummyEmbeddingModel(int dimensions) {
            this.dimensions = dimensions;
        }

        @Override
        public EmbeddingResponse call(EmbeddingRequest request) {
            return new EmbeddingResponse(Collections.emptyList());
        }

        @Override
        public float[] embed(Document document) {
            return embed(document.getText());
        }

        @Override
        public float[] embed(String text) {
            float[] vector = new float[dimensions];
            for (int i = 0; i < dimensions; i++) {
                vector[i] = random.nextFloat() * 2 - 1;
            }
            return vector;
        }
    }
}