package com.coolxer.model.business.asset.dto;

import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据探针SDK资产搜索数据传输对象
 */
@Data
@NoArgsConstructor
public class AssetProbeSearchDto extends SortPageDto {

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
     * 探针名称
     */
    private String probeName;

    /**
     * 探针版本
     */
    private String probeVersion;

    /**
     * 探针类型
     */
    private String probeType;

    /**
     * 开发语言
     */
    private String language;

    /**
     * 框架
     */
    private String framework;

    /**
     * 兼容版本
     */
    private String compatibleVersions;

    /**
     * 数据采集类型
     */
    private String dataCollectionTypes;

    /**
     * 加密方式
     */
    private String encryptionMethod;

    /**
     * 认证方式
     */
    private String authenticationMethod;

    /**
     * 数据传输协议
     */
    private String dataTransmissionProtocol;


    /**
     * 更新时间范围，格式：startTime,endTime
     */
    private String updateTime;

    /**
     * 创建时间范围，格式：startTime,endTime
     */
    private String insertTime;
} 