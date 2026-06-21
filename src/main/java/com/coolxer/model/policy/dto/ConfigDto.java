package com.coolxer.model.policy.dto;

import lombok.Data;

/**
 * 配置文件传输实体
 */
@Data
public class ConfigDto {

    /**
     * ID（备用）
     */
    private Integer id;

    /**
     * 原文件名（更新操作才用）
     */
    private String originalFileName;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件内容
     */
    private String text;

    /**
     * 提交信息
     */
    private String commitMsg;
}
