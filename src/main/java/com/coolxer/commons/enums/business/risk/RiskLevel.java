package com.coolxer.commons.enums.business.risk;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 风险等级
 */
@Getter
public enum RiskLevel {

    /**
     * 高风险
     */
    HIGH("high", "高风险", 1),

    /**
     * 中风险
     */
    MEDIUM("medium", "中风险", 2),

    /**
     * 低风险
     */
    LOW("low", "低风险", 3);

    RiskLevel(String code, String description, int sort) {
        this.code = code;
        this.description = description;
        this.sort = sort;
    }

    private final String code;
    private final String description;
    private final int sort;

    public static Map<String, Integer> levelSortMap = null;

    static {
        levelSortMap = Arrays.stream(RiskLevel.values())
                .collect(Collectors.toMap(RiskLevel::getCode, RiskLevel::getSort));
    }

    public static String getDescriptionByCode(String code) {
        for (RiskLevel riskLevel : RiskLevel.values()) {
            if (Objects.equals(code, riskLevel.getCode())) {
                return riskLevel.getDescription();
            }
        }
        return "";
    }

    public static List<String> getRiskLevelList() {
        return Arrays.stream(RiskLevel.values()).map(RiskLevel::getCode).toList();
    }

    public static List<String> getRiskLevelDescriptionList() {
        return Arrays.stream(RiskLevel.values()).map(RiskLevel::getDescription).toList();
    }
}
