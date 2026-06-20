package com.coolxer.service.dih.agent.config;

import com.alibaba.cloud.ai.memory.jdbc.MysqlChatMemoryRepository;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * InspectionAgent 对话记忆配置
 * 复用项目已有的 MySQL 持久化方案，与 AIChatService 共享 ai_chat_memory 表
 */
@Configuration
public class InspectionAgentMemoryConfig {

	@Bean("inspectionAgentChatMemory")
	public ChatMemory inspectionAgentChatMemory(
			@Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
		return MessageWindowChatMemory.builder()
				.chatMemoryRepository(MysqlChatMemoryRepository.mysqlBuilder()
						.jdbcTemplate(new JdbcTemplate(mysqlDataSource))
						.build())
				.maxMessages(10)
				.build();
	}

}
