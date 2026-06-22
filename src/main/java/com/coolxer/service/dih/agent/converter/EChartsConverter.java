package com.coolxer.service.dih.agent.converter;

import com.coolxer.service.dih.agent.nl2sql.connector.bo.ResultSetBO;
import com.coolxer.service.dih.agent.dto.EChartsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * SQL 结果集转 ECharts 数据转换器
 */
@Slf4j
@Component
public class EChartsConverter {

    /**
     * 根据图表类型转换数据
     *
     * @param resultSet SQL 查询结果
     * @param chartType 图表类型 (bar, line, pie)
     * @return ECharts 数据结构
     */
    public EChartsData convert(ResultSetBO resultSet, String chartType) {
        if (resultSet == null || resultSet.getData() == null || resultSet.getData().isEmpty()) {
            log.warn("ResultSet is empty, cannot convert to ECharts data");
            return null;
        }

        return switch (chartType.toLowerCase()) {
            case "bar" -> convertToBarChart(resultSet);
            case "line" -> convertToLineChart(resultSet);
            case "pie" -> convertToPieChart(resultSet);
            default -> {
                log.warn("Unsupported chart type: {}, falling back to bar chart", chartType);
                yield convertToBarChart(resultSet);
            }
        };
    }

    /**
     * 转换为柱状图数据
     * 假设第一列为 X 轴（类目），其余列为 Y 轴数据（系列）
     */
    private EChartsData convertToBarChart(ResultSetBO resultSet) {
        List<String> columns = resultSet.getColumn();
        List<Map<String, String>> data = resultSet.getData();

        if (columns.size() < 2) {
            log.warn("Bar chart requires at least 2 columns");
            return null;
        }

        // 第一列作为 X 轴
        String xAxisField = columns.get(0);
        List<String> xAxisData = data.stream()
                .map(row -> row.get(xAxisField))
                .toList();

        // 其余列作为系列数据
        List<Map<String, Object>> series = new ArrayList<>();
        for (int i = 1; i < columns.size(); i++) {
            String seriesName = columns.get(i);
            List<Object> seriesData = new ArrayList<>();
            for (Map<String, String> row : data) {
                seriesData.add(parseNumber(row.get(seriesName)));
            }
            series.add(Map.of(
                    "name", seriesName,
                    "type", "bar",
                    "data", seriesData,
                    "emphasis", Map.of("focus", "series")
            ));
        }

        // 构建 ECharts option
        Map<String, Object> option = new LinkedHashMap<>();
        option.put("tooltip", Map.of(
                "trigger", "axis",
                "axisPointer", Map.of("type", "shadow")
        ));

        // 如果有多个系列，显示图例
        if (series.size() > 1) {
            option.put("legend", Map.of(
                    "data", columns.subList(1, columns.size())
            ));
        }

        option.put("grid", Map.of(
                "left", "3%",
                "right", "4%",
                "bottom", "3%",
                "containLabel", true
        ));
        option.put("xAxis", Map.of(
                "type", "category",
                "data", xAxisData,
                "axisTick", Map.of("alignWithLabel", true)
        ));
        option.put("yAxis", Map.of("type", "value"));
        option.put("series", series);

        return EChartsData.builder()
                .chartType("bar")
                .option(option)
                .rawData(data)
                .columns(columns)
                .build();
    }

    /**
     * 转换为折线图数据
     * 假设第一列为 X 轴（类目/时间），其余列为 Y 轴数据（系列）
     */
    private EChartsData convertToLineChart(ResultSetBO resultSet) {
        List<String> columns = resultSet.getColumn();
        List<Map<String, String>> data = resultSet.getData();

        if (columns.size() < 2) {
            log.warn("Line chart requires at least 2 columns");
            return null;
        }

        // 第一列作为 X 轴
        String xAxisField = columns.get(0);
        List<String> xAxisData = data.stream()
                .map(row -> row.get(xAxisField))
                .toList();

        // 其余列作为系列数据
        List<Map<String, Object>> series = new ArrayList<>();
        for (int i = 1; i < columns.size(); i++) {
            String seriesName = columns.get(i);
            List<Object> seriesData = new ArrayList<>();
            for (Map<String, String> row : data) {
                seriesData.add(parseNumber(row.get(seriesName)));
            }
            series.add(Map.of(
                    "name", seriesName,
                    "type", "line",
                    "data", seriesData,
                    "smooth", true,
                    "emphasis", Map.of("focus", "series")
            ));
        }

        // 构建 ECharts option
        Map<String, Object> option = new LinkedHashMap<>();
        option.put("tooltip", Map.of(
                "trigger", "axis"
        ));

        // 如果有多个系列，显示图例
        if (series.size() > 1) {
            option.put("legend", Map.of(
                    "data", columns.subList(1, columns.size())
            ));
        }

        option.put("grid", Map.of(
                "left", "3%",
                "right", "4%",
                "bottom", "3%",
                "containLabel", true
        ));
        option.put("xAxis", Map.of(
                "type", "category",
                "boundaryGap", false,
                "data", xAxisData
        ));
        option.put("yAxis", Map.of("type", "value"));
        option.put("series", series);

        return EChartsData.builder()
                .chartType("line")
                .option(option)
                .rawData(data)
                .columns(columns)
                .build();
    }

    /**
     * 转换为饼图数据
     * 假设第一列为名称，第二列为数值
     */
    private EChartsData convertToPieChart(ResultSetBO resultSet) {
        List<String> columns = resultSet.getColumn();
        List<Map<String, String>> data = resultSet.getData();

        if (columns.size() < 2) {
            log.warn("Pie chart requires at least 2 columns");
            return null;
        }

        String nameField = columns.get(0);
        String valueField = columns.get(1);

        // 构建饼图数据
        List<Map<String, Object>> pieData = new ArrayList<>();
        for (Map<String, String> row : data) {
            pieData.add(Map.of(
                    "name", row.get(nameField),
                    "value", parseNumber(row.get(valueField))
            ));
        }

        // 构建 ECharts option
        Map<String, Object> option = new LinkedHashMap<>();
        option.put("tooltip", Map.of(
                "trigger", "item",
                "formatter", "{a} <br/>{b}: {c} ({d}%)"
        ));
        option.put("legend", Map.of(
                "orient", "vertical",
                "left", "left",
                "data", data.stream().map(row -> row.get(nameField)).toList()
        ));
        option.put("series", List.of(Map.of(
                "name", valueField,
                "type", "pie",
                "radius", List.of("40%", "70%"),
                "avoidLabelOverlap", true,
                "itemStyle", Map.of(
                        "borderRadius", 10,
                        "borderColor", "#fff",
                        "borderWidth", 2
                ),
                "label", Map.of(
                        "show", true,
                        "formatter", "{b}: {d}%"
                ),
                "emphasis", Map.of(
                        "label", Map.of(
                                "show", true,
                                "fontSize", 16,
                                "fontWeight", "bold"
                        )
                ),
                "data", pieData
        )));

        return EChartsData.builder()
                .chartType("pie")
                .option(option)
                .rawData(data)
                .columns(columns)
                .build();
    }

    /**
     * 将字符串解析为数字
     */
    private Object parseNumber(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        }
        try {
            if (value.contains(".")) {
                return Double.parseDouble(value);
            } else {
                return Long.parseLong(value);
            }
        } catch (NumberFormatException e) {
            // 如果无法解析为数字，返回原字符串
            return value;
        }
    }
}
