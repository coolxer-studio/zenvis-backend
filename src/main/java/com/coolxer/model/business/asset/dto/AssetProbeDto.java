package com.coolxer.model.business.asset.dto;

import com.coolxer.commons.enums.business.asset.*;
import lombok.Data;

/**
 * 数据探针SDK资产 DTO
 */
@Data
public class AssetProbeDto {

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
     * 资产状态，在网、停用、 下线、删除
     */
    private AssetStatus status;

    /**
     * 资产标签
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
     * 城市信息
     */
    private CityInfo cityInfo;

    // 数据探针SDK特有字段
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
     * 数据采集类型，多个类型用逗号分隔
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
     * 文件MD5值
     */
    private String fileMd5;

    /**
     * 签名方式
     */
    private String signatureMethod;

    /**
     * 证书MD5值
     */
    private String certificateMd5;

    /**
     * 证书详情，JSON格式
     */
    private String certificateDetails;
} 