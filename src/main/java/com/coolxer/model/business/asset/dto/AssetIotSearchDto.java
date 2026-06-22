package com.coolxer.model.business.asset.dto;

import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资产IoT设备搜索数据传输对象
 */
@Data
@NoArgsConstructor
public class AssetIotSearchDto extends SortPageDto {

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
     * 行政区域编码
     */
    private String areaCode;

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
     * 电源类型
     */
    private String powerType;

    /**
     * 电池电量
     */
    private Integer batteryLevel;

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

    /**
     * 活跃时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String insertTime;
} 