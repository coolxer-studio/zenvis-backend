package com.coolxer.commons.enums;

import lombok.Getter;

/**
 * 插件状态类型
 */
@Getter
public enum PluginStatusType {

    UN_INSTALL("未加载"),
    INSTALLED("已加载");

    PluginStatusType(String description) {
        this.description = description;
    }

    private final String description;
} 