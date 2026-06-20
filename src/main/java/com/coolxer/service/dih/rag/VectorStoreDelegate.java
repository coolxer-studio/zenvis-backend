package com.coolxer.service.dih.rag;

import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;

/**
 * 矢量库代理
 */
public class VectorStoreDelegate {

    /**
     * 内存矢量库
     */
    private VectorStore simpleVectorStore;

    /**
     * redis矢量库
     */
    private RedisVectorStore redisVectorStore;


    public VectorStoreDelegate(RedisVectorStore redisVectorStore, VectorStore simpleVectorStore) {
        this.simpleVectorStore = simpleVectorStore;
        this.redisVectorStore = redisVectorStore;
    }

    public VectorStore getVectorStore(String vectorStoreType) {
        if ("redis".equals(vectorStoreType)) {
            return redisVectorStore;
        }
        return simpleVectorStore;
    }
}
