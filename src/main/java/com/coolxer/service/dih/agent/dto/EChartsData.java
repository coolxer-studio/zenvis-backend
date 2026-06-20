package com.coolxer.service.dih.agent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * ECharts 图表数据结构
 * 用于前端直接渲染 ECharts 图表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EChartsData {

    /**
     * 图表类型: bar, line, pie
     */
    private String chartType;

    /**
     * 图表标题
     */
    private String title;

    /**
     * ECharts option 配置，前端可直接使用
     */
    private Map<String, Object> option;

    /**
     * 原始数据（可选，便于前端二次处理）
     */
    private List<Map<String, String>> rawData;

    /**
     * 列名列表
     */
    private List<String> columns;
}
