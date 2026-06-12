package com.coolxer.commons.enums;

import lombok.Getter;

/**
 * 看板类型
 * 菜单、链接
 *
 * @author hunter
 */
@Getter
public enum DashboardType {

    BUILT("内置看板"),
    LINK("外链看板");

    DashboardType(String description) {
        this.description = description;
    }

    private final String description;
} 