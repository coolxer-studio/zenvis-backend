package com.coolxer.model.business.asset.dto;

import com.coolxer.commons.enums.business.asset.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 日志资产数据传输对象
 */
@Data
@NoArgsConstructor
public class AssetLogDto {

    /**
     * 资产ID，资产的唯一编号信息
     */
    private String id;

    /**
     * 资产来源
     */
    private AssetSource source;

    /**
     * 资产类型
     */
    private AssetType type;

    /**
     * 资产属主（企业、终端客户）
     */
    private AssetOwner owner;

    /**
     * 资产状态，在网、停用、下线、删除
     */
    private AssetStatus status;

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
    private AssetLevel level;

    /**
     * 风险等级
     *
     * @see AssetRiskLevel
     */
    private AssetRiskLevel risk;

    /**
     * 风险信息，漏洞详情等，json格式
     */
    private String riskInfo;

    /**
     * 资产信息，不同的资产信息不一样，json格式
     */
    private String info;

    // 日志资产特有字段
    /**
     * 日志名称
     */
    private String logName;

    /**
     * 日志类型（系统日志、应用日志、安全日志、审计日志等）
     */
    private String logType;

    /**
     * 日志格式（JSON、XML、纯文本等）
     */
    private String logFormat;

    /**
     * 日志存储路径
     */
    private String logPath;

    /**
     * 日志时间
     */
    private Date logTime;

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
} 