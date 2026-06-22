package com.coolxer.service.dih.agent.prompt;

import org.springframework.ai.chat.prompt.PromptTemplate;

public class PromptConstant {
    /**
     * 获取 ECharts 意图识别的 Prompt 模板
     * 目前只支持折线图、饼图、柱状图
     *
     * @return
     */
    public static PromptTemplate getEChartsIntentionPromptTemplate() {
        return new PromptTemplate(com.coolxer.service.dih.agent.prompt.PromptLoader.loadPrompt("echarts_intention"));
    }

    public static PromptTemplate getInitRewritePromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("init-rewrite"));
    }

    public static PromptTemplate getQuestionToKeywordsPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("question-to-keywords"));
    }

    public static PromptTemplate getMixSelectorPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("mix-selector"));
    }

    public static PromptTemplate getMixSqlGeneratorSystemPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("mix-sql-generator-system"));
    }

    public static PromptTemplate getMixSqlGeneratorPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("mix-sql-generator"));
    }

    public static PromptTemplate getExtractDatetimePromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("extract-datetime"));
    }

    public static PromptTemplate getSemanticConsistencyPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("semantic-consistency"));
    }

    public static PromptTemplate getMixSqlGeneratorSystemCheckPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("mix-sql-generator-system-check"));
    }

    public static PromptTemplate getPlannerPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("planner"));
    }

    public static PromptTemplate getReportGeneratorPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("report-generator"));
    }

    public static PromptTemplate getSqlErrorFixerPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("sql-error-fixer"));
    }

    public static PromptTemplate getPythonGeneratorPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("python-generator"));
    }

    public static PromptTemplate getPythonAnalyzePromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("python-analyze"));
    }

    public static PromptTemplate getQuestionExpansionPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("question-expansion"));
    }

    public static PromptTemplate getBusinessKnowledgePromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("business-knowledge"));
    }

    public static PromptTemplate getSemanticModelPromptTemplate() {
        return new PromptTemplate(PromptLoader.loadPrompt("semantic-model"));
    }

    // 兼容性方法，保持向后兼容
    @Deprecated
    public static final PromptTemplate INIT_REWRITE_PROMPT_TEMPLATE = getInitRewritePromptTemplate();

    @Deprecated
    public static final PromptTemplate QUESTION_TO_KEYWORDS_PROMPT_TEMPLATE = getQuestionToKeywordsPromptTemplate();

    @Deprecated
    public static final PromptTemplate MIX_SELECTOR_PROMPT_TEMPLATE = getMixSelectorPromptTemplate();

    @Deprecated
    public static final PromptTemplate MIX_SQL_GENERATOR_SYSTEM_PROMPT_TEMPLATE = getMixSqlGeneratorSystemPromptTemplate();

    @Deprecated
    public static final PromptTemplate MIX_SQL_GENERATOR_PROMPT_TEMPLATE = getMixSqlGeneratorPromptTemplate();

    @Deprecated
    public static final PromptTemplate EXTRACT_DATETIME_PROMPT_TEMPLATE = getExtractDatetimePromptTemplate();

    @Deprecated
    public static final PromptTemplate SEMANTIC_CONSISTENCY_PROMPT_TEMPLATE = getSemanticConsistencyPromptTemplate();

    @Deprecated
    public static final PromptTemplate MIX_SQL_GENERATOR_SYSTEM_PROMPT_CHECK_TEMPLATE = getMixSqlGeneratorSystemCheckPromptTemplate();

}
