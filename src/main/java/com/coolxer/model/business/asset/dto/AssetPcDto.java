package com.coolxer.model.business.asset.dto;

import com.coolxer.commons.enums.business.asset.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资产主机数据传输对象
 */
@Data
@NoArgsConstructor
public class AssetPcDto {

    /**
     * 资产ID，资产的唯一编号信息
     */
    private String id;

    /**
     * 资产来源
     *
     * @see AssetSource
     */
    private AssetSource source;

    /**
     * 资产类型
     *
     * @see AssetType
     */
    private AssetType type;

    /**
     * 资产属主
     *
     * @see AssetOwner
     */
    private AssetOwner owner;

    /**
     * 资产状态
     *
     * @see AssetStatus
     */
    private AssetStatus status;

    /**
     * 行政区域编码
     */
    private String areaCode;

    /**
     * 城市信息
     */
    private CityInfo cityInfo;

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
     * 资产标签，多个标签用逗号分隔
     */
    private String label;

    /**
     * 资产是否开放
     */
    private Boolean access;

    /**
     * 资产重要等级
     *
     * @see AssetLevel
     */
    private AssetLevel level;

    /**
     * 风险等级
     *
     * @see AssetRiskLevel
     */
    private AssetRiskLevel risk;

    /**
     * 厂商
     */
    private String manufacturer;

    /**
     * 型号
     */
    private String model;

    /**
     * 架构
     */
    private String architecture;

    /**
     * 操作系统
     */
    private String systemName;

    /**
     * 系统版本
     */
    private String systemVersion;

    /**
     * CPU型号
     */
    private String cpuModel;

    /**
     * CPU核心数
     */
    private Integer cpuCores;

    /**
     * 内存大小(GB)
     */
    private Integer memorySize;

    /**
     * 磁盘大小(GB)
     */
    private Integer diskSize;

    /**
     * 网络类型
     */
    private String netType;

    /**
     * 显卡型号
     */
    private String gpuModel;

    /**
     * 显卡品牌
     */
    private String gpuBrand;

    /**
     * 显存容量(GB)
     */
    private Integer gpuMemorySize;

    /**
     * 显存类型
     */
    private String gpuMemoryType;

    /**
     * 显示器品牌
     */
    private String monitorBrand;

    /**
     * 显示器型号
     */
    private String monitorModel;

    /**
     * 显示器分辨率
     */
    private String monitorResolution;

    /**
     * 显示器接口类型
     */
    private String monitorInterface;

    /**
     * 局域网IP
     */
    private String lanIp;

    /**
     * 因特网IP
     */
    private String wanIp;

}
