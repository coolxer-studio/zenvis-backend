package com.coolxer.model.business.asset.dto;

import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资产PC设备搜索数据传输对象
 */
@Data
@NoArgsConstructor
public class AssetPcSearchDto extends SortPageDto {

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