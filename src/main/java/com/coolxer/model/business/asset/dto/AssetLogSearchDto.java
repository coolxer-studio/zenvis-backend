package com.coolxer.model.business.asset.dto;

import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志资产搜索条件数据传输对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetLogSearchDto extends SortPageDto {

    /**
     * 资产ID，资产的唯一编号信息
     */
    private String id;

    /**
     * 资产来源
     */
    private String source;

    /**
     * 资产类型
     */
    private String type;

    /**
     * 资产属主
     */
    private String owner;

    /**
     * 资产状态
     */
    private String status;

    /**
     * 资产标签，多个标签用逗号分隔
     */
    private String label;

    /**
     * 资产是否开放
     */
    private Boolean access;

    /**
     * 资产重要等级
     */
    private String level;

    /**
     * 风险等级
     */
    private String risk;

    /**
     * 日志文件名称
     */
    private String logName;

    /**
     * 日志存储路径
     */
    private String logPath;

    /**
     * 日志类型（系统日志、应用日志、安全日志、审计日志等）
     */
    private String logType;

    /**
     * 日志格式（JSON、XML、纯文本等）
     */
    private String logFormat;

    /**
     * 日志时间范围（开始时间）
     */
    private String logTimeStart;

    /**
     * 日志时间范围（结束时间）
     */
    private String logTimeEnd;

    /**
     * 日志级别
     */
    private String logLevel;

    /**
     * 进程信息
     */
    private String process;

    /**
     * 日志信息
     */
    private String logMessage;

    /**
     * 活跃时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String insertTime;
} 