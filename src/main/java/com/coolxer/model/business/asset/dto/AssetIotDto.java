package com.coolxer.model.business.asset.dto;

import com.coolxer.commons.enums.business.asset.*;
import lombok.Data;

import java.util.Date;

/**
 * IoT设备资产 DTO
 */
@Data
public class AssetIotDto {

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
     * 城市信息
     */
    private CityInfo cityInfo;

    // IoT设备特有信息

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 制造商
     */
    private String manufacturer;

    /**
     * 型号
     */
    private String model;

    /**
     * 设备序列号
     */
    private String serialNumber;

    /**
     * 固件版本
     */
    private String firmwareVersion;

    /**
     * 固件更新时间
     */
    private Date firmwareUpdateTime;

    /**
     * 电源类型
     */
    private String powerType;

    /**
     * 电池电量
     */
    private Integer batteryLevel;

    /**
     * 传感器信息，json格式
     */
    private String sensorInfo;

    /**
     * 通信协议
     */
    private String communicationProtocol;

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