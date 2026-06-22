/*
 * Copyright 2024-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.coolxer.service.dih.agent.nl2sql.constant;

/**
 * NL2SQL 常量定义
 */
public class Constant {

	public static final String INPUT_KEY = "input";

	public static final String AGENT_ID = "agent_id";

	public static final String RESULT = "result";

	public static final String NL2SQL_GRAPH_NAME = "nl2sql_graph";

	public static final String QUERY_REWRITE_NODE_OUTPUT = "query_rewrite_node_output";

	public static final String KEYWORD_EXTRACT_NODE_OUTPUT = "keyword_extract_node_output";

	public static final String EVIDENCES = "evidences";

	public static final String TABLE_DOCUMENTS_FOR_SCHEMA_OUTPUT = "table_documents_for_schema_output";

	public static final String SCHEMA_RECALL_NODE_OUTPUT = "schema_recall_node_output";

	public static final String COLUMN_DOCUMENTS_BY_KEYWORDS_OUTPUT = "column_documents_by_keywords_output";

	public static final String TABLE_RELATION_OUTPUT = "table_relation_output";

	public static final String BUSINESS_KNOWLEDGE = "business_knowledge";

	public static final String SEMANTIC_MODEL = "semantic_model";

	public static final String SQL_GENERATE_OUTPUT = "sql_generate_output";

	public static final String SQL_GENERATE_SCHEMA_MISSING_ADVICE = "sql_generate_schema_missing_advice";

	public static final String SQL_GENERATE_SCHEMA_MISSING = "sql_generate_schema_missing";

	public static final String SQL_GENERATE_COUNT = "sql_generate_count";

	public static final String SQL_VALIDATE_NODE_OUTPUT = "sql_validate_node_output";

	public static final String SQL_VALIDATE_EXCEPTION_OUTPUT = "sql_validate_exception_output";

	public static final String SEMANTIC_CONSISTENCY_NODE_OUTPUT = "semantic_consistency_node_output";

	public static final String SEMANTIC_CONSISTENCY_NODE_RECOMMEND_OUTPUT = "semantic_consistency_node_recommend_output";

	public static final String PLANNER_NODE_OUTPUT = "planner_node_output";

	public static final String SQL_EXECUTE_NODE_OUTPUT = "sql_execute_node_output";

	public static final String SQL_EXECUTE_NODE_EXCEPTION_OUTPUT = "sql_execute_node_exception_output";

	public static final String PLAN_CURRENT_STEP = "plan_current_step";

	public static final String PLAN_NEXT_NODE = "plan_next_node";

	public static final String PLAN_VALIDATION_STATUS = "plan_validation_status";

	public static final String PLAN_VALIDATION_ERROR = "plan_validation_error";

	public static final String PLAN_REPAIR_COUNT = "plan_repair_count";

	// Node names
	public static final String PLANNER_NODE = "planner_node";

	public static final String PLAN_EXECUTOR_NODE = "plan_executor_node";

	public static final String QUERY_REWRITE_NODE = "query_rewrite_node";

	public static final String REPORT_GENERATOR_NODE = "report_generator_node";

	public static final String KEYWORD_EXTRACT_NODE = "keyword_extract_node";

	public static final String SCHEMA_RECALL_NODE = "schema_recall_node";

	public static final String TABLE_RELATION_NODE = "table_relation_node";

	public static final String SQL_GENERATE_NODE = "sql_generate_node";

	public static final String SQL_VALIDATE_NODE = "sql_validate_node";

	public static final String SQL_EXECUTE_NODE = "sql_execute_node";

	public static final String SEMANTIC_CONSISTENCY_NODE = "semantic_consistency_node";

	// Intent types
	public static final String SMALL_TALK_REJECT = "闲聊问题,请用自然语言回答";

	public static final String INTENT_UNCLEAR = "意图不明确,请补充更多细节";

	// Python related
	public static final String PYTHON_GENERATE_NODE = "python_generate_node";

	public static final String PYTHON_EXECUTE_NODE = "python_execute_node";

	public static final String PYTHON_ANALYZE_NODE = "python_analyze_node";

	public static final String SQL_RESULT_LIST_MEMORY = "sql_result_list_memory";

	public static final String PYTHON_IS_SUCCESS = "python_is_success";

	public static final String PYTHON_TRIES_COUNT = "python_tries_count";

	public static final String PYTHON_EXECUTE_NODE_OUTPUT = "python_execute_node_output";

	public static final String PYTHON_GENERATE_NODE_OUTPUT = "python_generate_node_output";

	public static final String PYTHON_ANALYSIS_NODE_OUTPUT = "python_analysis_node_output";

}
