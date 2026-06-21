package com.coolxer.model.business.asset.dto;

import com.coolxer.commons.enums.business.asset.*;
import lombok.Data;

import java.util.Date;

/**
 * APP应用程序资产 DTO
 */
@Data
public class AssetAppDto {

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
     * 行政区域编码
     */
    private String areaCode;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

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

    // APP应用程序特有字段
    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用版本
     */
    private String appVersion;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 平台
     */
    private String platform;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 开发者
     */
    private String developer;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 更新渠道
     */
    private String updateChannel;

    /**
     * 最低系统版本
     */
    private String minOsVersion;

    /**
     * 目标系统版本
     */
    private String targetOsVersion;

    /**
     * 权限列表，多个权限用逗号分隔
     */
    private String permissions;

    /**
     * 依赖列表，多个依赖用逗号分隔
     */
    private String dependencies;

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