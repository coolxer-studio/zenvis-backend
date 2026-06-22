package com.coolxer.dao.clickhouse.entity.asset;

import com.coolxer.commons.enums.business.asset.*;
import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import com.coolxer.dao.clickhouse.converter.StringListConverter;
import com.coolxer.model.business.asset.dto.AssetLogDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 日志资产表实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_ASSET_LOG)
public class AssetLog {

    /**
     * 资产ID，资产的唯一编号信息
     */
    @Id
    private String id;

    /**
     * 资产来源
     */
    @Enumerated(EnumType.STRING)
    private AssetSource source;

    /**
     * 资产类型
     */
    @Enumerated(EnumType.STRING)
    private AssetType type;

    /**
     * 资产属主（企业、终端客户）
     */
    @Enumerated(EnumType.STRING)
    private AssetOwner owner;

    /**
     * 资产状态，在网、停用、下线、删除
     */
    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    /**
     * 资产标签
     */
    @Convert(converter = StringListConverter.class)
    private List<String> label;

    /**
     * 资产是否开放
     */
    private Boolean access;

    /**
     * 资产重要等级
     */
    @Enumerated(EnumType.STRING)
    private AssetLevel level;

    /**
     * 风险等级
     */
    @Enumerated(EnumType.STRING)
    private AssetRiskLevel risk;

    /**
     * 风险信息，漏洞详情等，json格式
     */
    @Column(name = "risk_info")
    private String riskInfo;

    /**
     * 资产信息，不同的资产信息不一样，json格式
     */
    private String info;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "insert_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertTime;

    // 日志资产特有字段
    /**
     * 日志文件名称
     */
    @Column(name = "log_name")
    private String logName;

    /**
     * 日志存储路径
     */
    @Column(name = "log_path")
    private String logPath;

    /**
     * 日志类型（系统日志、应用日志、安全日志、审计日志等）
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * 日志格式（JSON、XML、纯文本等）
     */
    @Column(name = "log_format")
    private String logFormat;

    /**
     * 日志时间
     */
    @Column(name = "log_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logTime;

    /**
     * 日志级别
     */
    @Column(name = "log_level")
    private String logLevel;

    /**
     * 进程信息
     */
    @Column(name = "process")
    private String process;

    /**
     * 日志信息
     */
    @Column(name = "log_message")
    private String logMessage;

    /**
     * 从DTO转换为实体
     */
    public static AssetLog fromDto(AssetLogDto dto) {
        if (dto == null) {
            return null;
        }

        AssetLog entity = new AssetLog();
        entity.setId(dto.getId());
        entity.setSource(dto.getSource());
        entity.setType(dto.getType());
        entity.setOwner(dto.getOwner());
        entity.setStatus(dto.getStatus());
        entity.setAccess(dto.getAccess());
        entity.setLevel(dto.getLevel());
        entity.setRisk(dto.getRisk());
        entity.setRiskInfo(dto.getRiskInfo());
        entity.setInfo(dto.getInfo());

        // 处理标签
        if (dto.getLabel() != null && !dto.getLabel().isEmpty()) {
            entity.setLabel(Arrays.asList(dto.getLabel().split(",")));
        } else {
            entity.setLabel(new ArrayList<>());
        }

        // 日志资产特有字段
        entity.setLogName(dto.getLogName());
        entity.setLogPath(dto.getLogPath());
        entity.setLogType(dto.getLogType());
        entity.setLogFormat(dto.getLogFormat());
        entity.setLogTime(dto.getLogTime());
        entity.setLogLevel(dto.getLogLevel());
        entity.setProcess(dto.getProcess());
        entity.setLogMessage(dto.getLogMessage());

        entity.setUpdateTime(new Date());
        entity.setInsertTime(new Date());

        return entity;
    }
} 