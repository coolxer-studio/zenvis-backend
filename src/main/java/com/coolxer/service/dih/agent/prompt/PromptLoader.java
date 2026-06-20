package com.coolxer.service.dih.agent.prompt;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

public class PromptLoader {

    private static final String PROMPT_PATH_PREFIX = "agent_prompt/";

    private static final ConcurrentHashMap<String, String> promptCache = new ConcurrentHashMap<>();

    /**
     * 从文件加载提示词模板
     * @param promptName 提示词文件名（不含路径和扩展名）
     * @return 提示词内容
     */
    public static String loadPrompt(String promptName) {
        return promptCache.computeIfAbsent(promptName, name -> {
            try {
                String fileName = PROMPT_PATH_PREFIX + name + ".txt";
                ClassPathResource resource = new ClassPathResource(fileName);
                if (!resource.exists()) {
                    throw new IllegalArgumentException("提示词文件不存在: " + fileName);
                }
                return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            }
            catch (IOException e) {
                throw new RuntimeException("加载提示词失败: " + name, e);
            }
        });
    }


    /**
     * 清除提示词缓存
     */
    public static void clearCache() {
        promptCache.clear();
    }

    /**
     * 获取缓存大小
     * @return 缓存中的提示词数量
     */
    public static int getCacheSize() {
        return promptCache.size();
    }

}
