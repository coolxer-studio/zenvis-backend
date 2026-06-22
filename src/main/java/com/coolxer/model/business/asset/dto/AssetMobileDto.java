package com.coolxer.model.business.asset.dto;

import com.coolxer.commons.enums.business.asset.*;
import lombok.Data;

/**
 * 移动端资产 DTO
 */
@Data
public class AssetMobileDto {

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

    // 移动设备资产特有信息
    /**
     * 设备品牌
     */
    private String brand;

    /**
     * 设备型号代码
     */
    private String model;

    /**
     * 设备制造商
     */
    private String manufacturer;

    /**
     * 操作系统
     */
    private String systemName;

    /**
     * 系统版本
     */
    private String systemVersion;

    /**
     * AndroidID
     */
    private String androidId;

    /**
     * BuildID
     */
    private String buildId;

    /**
     * 蓝牙 MAC
     */
    private String bluetoothMac;

    /**
     * 显示设备信息
     */
    private String displayInfo;

    /**
     * 应用运行模式
     */
    private String appMode;

    /**
     * 指令集 1
     */
    private String instructionSet1;

    /**
     * 设备指纹
     */
    private String deviceFingerprint;

    /**
     * 主机名
     */
    private String hostname;

    /**
     * 陀螺仪信息
     */
    private String gyroscopeInfo;

    /**
     * 经纬度
     */
    private String location;

    /**
     * 基站位置区域码
     */
    private String cellAreaCode;

    /**
     * 临近基站个数
     */
    private Integer nearbyCellCount;

    /**
     * Bootloader
     */
    private String bootloader;

    /**
     * 设备形态
     */
    private String deviceForm;

    /**
     * 屏幕分辨率
     */
    private String screenResolution;

    /**
     * 系统名称
     */
    private String osName;

    /**
     * IMEI
     */
    private String imei;

    /**
     * IMSI
     */
    private String imsi;

    /**
     * 网卡 MAC
     */
    private String networkMac;

    /**
     * 设备信息
     */
    private String deviceInfo;

    /**
     * 系统 SDK 版本
     */
    private String sdkVersion;

    /**
     * 系统引导
     */
    private String systemBoot;

    /**
     * 序列号
     */
    private String serialNumber;

    /**
     * 硬件名称
     */
    private String hardwareName;

    /**
     * 指令集 2
     */
    private String instructionSet2;

    /**
     * 主板信息
     */
    private String motherboardInfo;

    /**
     * Build.date
     */
    private String buildDate;

    /**
     * 时区设置
     */
    private String timezone;

    /**
     * WiFi - MAC
     */
    private String wifiMac;

    /**
     * 基站编号
     */
    private String cellId;

    /**
     * 运营商类型
     */
    private String carrierType;

    /**
     * 网络类型
     */
    private String netType;
    /**
     * 局域网IP
     */
    private String lanIp;
    /**
     * 因特网IP
     */
    private String wanIp;
} 