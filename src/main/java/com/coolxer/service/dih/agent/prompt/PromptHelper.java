package com.coolxer.service.dih.agent.prompt;

import com.coolxer.service.dih.agent.common.BizDataSourceTypeEnum;
import com.coolxer.service.dih.agent.nl2sql.connector.config.DbConfig;
import com.coolxer.service.dih.agent.dto.schema.ColumnDTO;
import com.coolxer.service.dih.agent.dto.schema.SchemaDTO;
import com.coolxer.service.dih.agent.dto.schema.TableDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.prompt.PromptTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class PromptHelper {

    private static final List<String> DATE_TIME_TYPES = Arrays.asList("DATE", "TIME", "DATETIME", "TIMESTAMP");

    public static String buildRewritePrompt(String query, SchemaDTO schemaDTO, List<String> evidenceList) {
        return buildRewritePrompt(query, schemaDTO, evidenceList, null);
    }

    /**
     * 构建带对话历史的 query rewrite 提示词
     * 将历史消息格式化为 init-rewrite.txt 要求的多轮输入格式：
     * >用户：...\n>助理：...\n<最新>用户: current query
     */
    public static String buildRewritePrompt(String query, SchemaDTO schemaDTO, List<String> evidenceList,
                                            List<Message> conversationHistory) {
        StringBuilder dbContent = new StringBuilder();
        dbContent.append("库名: 默认数据库, 包含以下表:\n");
        for (TableDTO tableDTO : schemaDTO.getTable()) {
            dbContent.append(buildMacSqlTablePrompt(tableDTO)).append("\n");
        }

        // 构建多轮对话输入
        StringBuilder multiTurn = new StringBuilder();
        if (conversationHistory != null && !conversationHistory.isEmpty()) {
            for (Message msg : conversationHistory) {
                MessageType type = msg.getMessageType();
                if (type == MessageType.USER) {
                    multiTurn.append(">").append("用户：").append(msg.getText()).append("\n");
                }
                else if (type == MessageType.ASSISTANT) {
                    multiTurn.append(">").append("助理：").append(msg.getText()).append("\n");
                }
            }
        }
        multiTurn.append("<最新>用户: ").append(query);

        String evidence = CollectionUtils.isEmpty(evidenceList) ? "" : StringUtils.join(evidenceList, ";\n");
        Map<String, Object> params = new HashMap<>();
        params.put("db_content", dbContent.toString());
        params.put("evidence", evidence);
        params.put("multi_turn", multiTurn.toString());
        return PromptConstant.INIT_REWRITE_PROMPT_TEMPLATE.render(params);
    }

    public static String buildMacSqlTablePrompt(TableDTO tableDTO) {
        StringBuilder sb = new StringBuilder();
        sb.append("# 表名: ").append(tableDTO.getName()).append(", 包含字段:\n");
        sb.append("[\n");
        List<String> columnLines = new ArrayList<>();
        for (ColumnDTO columnDTO : tableDTO.getColumn()) {
            StringBuilder line = new StringBuilder();
            line.append("  (").append(StringUtils.defaultString(columnDTO.getDescription(), columnDTO.getName()));
            if (CollectionUtils.isNotEmpty(columnDTO.getData())) {
                line.append(", 示例值:[");
                List<String> data = columnDTO.getData()
                        .subList(0, Math.min(3, columnDTO.getData().size()))
                        .stream()
                        .map(d -> "'" + d + "'")
                        .collect(Collectors.toList());
                line.append(StringUtils.join(data, ",")).append("])");
            }
            else {
                line.append(")");
            }
            columnLines.add(line.toString());
        }
        sb.append(StringUtils.join(columnLines, ",\n"));
        sb.append("\n]");
        return sb.toString();
    }

    public static String buildQueryToKeywordsPrompt(String question) {
        Map<String, Object> params = new HashMap<>();
        params.put("question", question);
        return PromptConstant.QUESTION_TO_KEYWORDS_PROMPT_TEMPLATE.render(params);
    }

    public static String buildMixSelectorPrompt(List<String> evidences, String question, SchemaDTO schemaDTO) {
        String schemaInfo = buildMixMacSqlDbPrompt(schemaDTO, true);
        Map<String, Object> params = new HashMap<>();
        params.put("schema_info", schemaInfo);
        params.put("question", question);
        String evidence = CollectionUtils.isEmpty(evidences) ? "" : StringUtils.join(evidences, ";\n");
        params.put("evidence", evidence);
        return PromptConstant.MIX_SELECTOR_PROMPT_TEMPLATE.render(params);
    }

    public static String buildDateTimeExtractPrompt(String question) {
        Map<String, Object> params = new HashMap<>();
        params.put("question", question);
        return PromptConstant.EXTRACT_DATETIME_PROMPT_TEMPLATE.render(params);
    }

    public static String buildMixMacSqlDbPrompt(SchemaDTO schemaDTO, Boolean withColumnType) {
        StringBuilder sb = new StringBuilder();
        sb.append("【DB_ID】 ").append(schemaDTO.getName() == null ? "" : schemaDTO.getName()).append("\n");
        for (TableDTO tableDTO : schemaDTO.getTable()) {
            sb.append(buildMixMacSqlTablePrompt(tableDTO, withColumnType)).append("\n");
        }
        if (CollectionUtils.isNotEmpty(schemaDTO.getForeignKeys())
                && CollectionUtils.isNotEmpty(schemaDTO.getForeignKeys().get(0))) {
            sb.append("【Foreign keys】\n").append(StringUtils.join(schemaDTO.getForeignKeys().get(0), "\n"));
        }
        return sb.toString();
    }

    public static String buildMixMacSqlTablePrompt(TableDTO tableDTO, Boolean withColumnType) {
        StringBuilder sb = new StringBuilder();
        // sb.append("# Table:
        // ").append(tableDTO.getName()).append(StringUtils.isBlank(tableDTO.getDescription())
        // ? "" : ", " + tableDTO.getDescription()).append("\n");
        sb.append("# Table: ").append(tableDTO.getName());
        if (!StringUtils.equals(tableDTO.getName(), tableDTO.getDescription())) {
            sb.append(StringUtils.isBlank(tableDTO.getDescription()) ? "" : ", " + tableDTO.getDescription())
                    .append("\n");
        }
        else {
            sb.append("\n");
        }
        sb.append("[\n");
        List<String> columnLines = new ArrayList<>();
        for (ColumnDTO columnDTO : tableDTO.getColumn()) {
            StringBuilder line = new StringBuilder();
            line.append("(")
                    .append(columnDTO.getName())
                    .append(BooleanUtils.isTrue(withColumnType)
                            ? ":" + StringUtils.defaultString(columnDTO.getType(), "").toUpperCase(Locale.ROOT) : "");
            if (!StringUtils.equals(columnDTO.getDescription(), columnDTO.getName())) {
                line.append(", ").append(StringUtils.defaultString(columnDTO.getDescription(), ""));
            }
            if (CollectionUtils.isNotEmpty(tableDTO.getPrimaryKeys())
                    && tableDTO.getPrimaryKeys().contains(columnDTO.getName())) {
                line.append(", Primary Key");
            }
            List<String> enumData = Optional.ofNullable(columnDTO.getData())
                    .orElse(new ArrayList<>())
                    .stream()
                    .filter(d -> !StringUtils.isEmpty(d))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(enumData) && !"id".equals(columnDTO.getName())) {
                line.append(", Examples: [");
                List<String> data = new ArrayList<>(enumData.subList(0, Math.min(3, enumData.size())));
                line.append(StringUtils.join(data, ",")).append("]");
            }
            else if (CollectionUtils.isNotEmpty(columnDTO.getSamples())) {
                List<String> data = columnDTO.getSamples().subList(0, Math.min(3, columnDTO.getSamples().size()));
                data = data.stream().filter(item -> StringUtils.isNotBlank(item)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(data)) {
                    line.append(", Examples: [");
                    data = processSamples(data, columnDTO);
                    line.append(StringUtils.join(data, ",")).append("]");
                }
            }
            line.append(")");
            columnLines.add(line.toString());
        }
        sb.append(StringUtils.join(columnLines, ",\n"));
        sb.append("\n]");
        return sb.toString();
    }

    private static List<String> processSamples(List<String> samples, ColumnDTO columnDTO) {
        final List<String> data = new ArrayList<>(samples);
        if (data.stream().anyMatch(item -> item.length() > 50)) {
            return new ArrayList<>();
        }
        String type = columnDTO.getType();
        if (type != null && DATE_TIME_TYPES.contains(type.toUpperCase(Locale.ROOT))) {
            return data.isEmpty() ? Collections.emptyList() : Collections.singletonList(data.get(0));
        }
        if (type != null && type.equalsIgnoreCase("NUMBER")) {
            return data.isEmpty() ? Collections.emptyList() : Collections.singletonList(data.get(0));
        }
        String columnName = columnDTO.getName();
        if (columnName != null && columnName.trim().toLowerCase(Locale.ROOT).endsWith("id")) {
            return data.isEmpty() ? Collections.emptyList() : Collections.singletonList(data.get(0));
        }
        List<String> longSamples = data.stream().filter(item -> item.length() > 20).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(longSamples)) {
            return Collections.singletonList(longSamples.get(0));
        }

        return data;
    }

    public static List<String> buildMixSqlGeneratorPrompt(String question, DbConfig dbConfig, SchemaDTO schemaDTO,
                                                          List<String> evidenceList) {
        String evidence = StringUtils.join(evidenceList, ";\n");
        String schemaInfo = buildMixMacSqlDbPrompt(schemaDTO, true);
        String dialect = BizDataSourceTypeEnum.fromTypeName(dbConfig.getDialectType()).getDialect();
        Map<String, Object> params = new HashMap<>();
        params.put("dialect", dialect);
        params.put("question", question);
        params.put("schema_info", schemaInfo);
        params.put("evidence", evidence);
        List<String> prompts = new ArrayList<>();
        prompts.add(PromptConstant.MIX_SQL_GENERATOR_SYSTEM_PROMPT_TEMPLATE.render(params));
        prompts.add(PromptConstant.MIX_SQL_GENERATOR_PROMPT_TEMPLATE.render(params));
        return prompts;
    }

    public static String mixSqlGeneratorSystemCheckPrompt(String question, DbConfig dbConfig, SchemaDTO schemaDTO,
                                                          List<String> evidenceList) {
        String evidence = StringUtils.join(evidenceList, ";\n");
        String schemaInfo = buildMixMacSqlDbPrompt(schemaDTO, true);
        String dialect = BizDataSourceTypeEnum.fromTypeName(dbConfig.getDialectType()).getDialect();
        Map<String, Object> params = new HashMap<>();
        params.put("dialect", dialect);
        params.put("question", question);
        params.put("schema_info", schemaInfo);
        params.put("evidence", evidence);
        return PromptConstant.MIX_SQL_GENERATOR_SYSTEM_PROMPT_CHECK_TEMPLATE.render(params);
    }

    public static String buildSemanticConsistenPrompt(String nlReq, String sql) {
        Map<String, Object> params = new HashMap<>();
        params.put("nl_req", nlReq);
        params.put("sql", sql);
        return PromptConstant.SEMANTIC_CONSISTENCY_PROMPT_TEMPLATE.render(params);
    }

    /**
     * 构建带自定义提示词的报告生成提示词
     * @param userRequirementsAndPlan 用户需求和计划
     * @param analysisStepsAndData 分析步骤和数据
     * @param summaryAndRecommendations 总结和建议
     * @param customPrompt 用户自定义的提示词内容，如果为null则使用默认提示词
     * @return 构建的提示词
     */
    public static String buildReportGeneratorPromptWithCustom(String userRequirementsAndPlan,
                                                              String analysisStepsAndData, String summaryAndRecommendations, String customPrompt) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_requirements_and_plan", userRequirementsAndPlan);
        params.put("analysis_steps_and_data", analysisStepsAndData);
        params.put("summary_and_recommendations", summaryAndRecommendations);

        if (customPrompt != null && !customPrompt.trim().isEmpty()) {
            // 使用自定义提示词
            return new org.springframework.ai.chat.prompt.PromptTemplate(customPrompt).render(params);
        }
        else {
            // 使用默认提示词
            return PromptConstant.getReportGeneratorPromptTemplate().render(params);
        }
    }

    public static String buildSqlErrorFixerPrompt(String question, DbConfig dbConfig, SchemaDTO schemaDTO,
                                                  List<String> evidenceList, String errorSql, String errorMessage) {
        String evidence = StringUtils.join(evidenceList, ";\n");
        String schemaInfo = buildMixMacSqlDbPrompt(schemaDTO, true);
        String dialect = BizDataSourceTypeEnum.fromTypeName(dbConfig.getDialectType()).getDialect();

        Map<String, Object> params = new HashMap<>();
        params.put("dialect", dialect);
        params.put("question", question);
        params.put("schema_info", schemaInfo);
        params.put("evidence", evidence);
        params.put("error_sql", errorSql);
        params.put("error_message", errorMessage);

        return PromptConstant.getSqlErrorFixerPromptTemplate().render(params);
    }

//    public static String buildBusinessKnowledgePrompt(List<BusinessKnowledgeDTO> businessKnowledgeDTOS) {
//        Map<String, Object> params = new HashMap<>();
//        String businessKnowledge = CollectionUtils.isEmpty(businessKnowledgeDTOS) ? ""
//                : StringUtils.join(businessKnowledgeDTOS, ";\n");
//        params.put("businessKnowledge", businessKnowledge);
//        return PromptConstant.getBusinessKnowledgePromptTemplate().render(params);
//    }
//
//    public static String buildSemanticModelPrompt(List<SemanticModelDTO> semanticModelDTOS) {
//        Map<String, Object> params = new HashMap<>();
//        String semanticModel = CollectionUtils.isEmpty(semanticModelDTOS) ? ""
//                : StringUtils.join(semanticModelDTOS, ";\n");
//        params.put("semanticModel", semanticModel);
//        return PromptConstant.getSemanticModelPromptTemplate().render(params);
//    }

    public static String buildEChartsIntentionPrompt(String query) {
        return buildEChartsIntentionPrompt(query, null);
    }

    /**
     * 构建带对话历史的 ECharts 意图识别提示词
     */
    public static String buildEChartsIntentionPrompt(String query, List<Message> conversationHistory) {
        PromptTemplate template = PromptConstant.getEChartsIntentionPromptTemplate();

        // 构建历史上下文
        String history = "";
        if (conversationHistory != null && !conversationHistory.isEmpty()) {
            StringBuilder sb = new StringBuilder("【对话历史】\n");
            for (Message msg : conversationHistory) {
                MessageType type = msg.getMessageType();
                if (type == MessageType.USER) {
                    sb.append("用户：").append(msg.getText()).append("\n");
                }
                else if (type == MessageType.ASSISTANT) {
                    sb.append("助理：").append(msg.getText()).append("\n");
                }
            }
            history = sb.toString();
        }

        Map<String, Object> params = new HashMap<>();
        params.put("query", query);
        params.put("history", history);
        return template.render(params);
    }


}
