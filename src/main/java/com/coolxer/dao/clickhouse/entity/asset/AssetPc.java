package com.coolxer.dao.clickhouse.entity.asset;

import com.coolxer.commons.enums.business.asset.*;
import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import com.coolxer.dao.clickhouse.converter.StringListConverter;
import com.coolxer.model.business.asset.dto.AssetPcDto;
import com.coolxer.model.business.asset.dto.CityInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * PC机资产表
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_ASSET_PC)
public class AssetPc {

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
     * 资产状态，在网、停用、 下线、删除
     */
    @Enumerated(EnumType.STRING)
    private AssetStatus status;
    /**
     * 行政区域编码
     */
    @Column(name = "area_code")
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
     * 网络类型
     */
    @Column(name = "net_type")
    private String netType;
    /**
     * 局域网IP
     */
    @Column(name = "lan_ip")
    private String lanIp;
    /**
     * 因特网IP
     */
    @Column(name = "wan_ip")
    private String wanIp;
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

    // PC资产特有信息
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
    @Column(name = "system_name")
    private String systemName;
    /**
     * 系统版本
     */
    @Column(name = "system_version")
    private String systemVersion;
    /**
     * CPU型号
     */
    @Column(name = "cpu_model")
    private String cpuModel;
    /**
     * CPU核心数
     */
    @Column(name = "cpu_cores")
    private Integer cpuCores;
    /**
     * 内存大小(GB)
     */
    @Column(name = "memory_size")
    private Integer memorySize;
    /**
     * 磁盘大小(GB)
     */
    @Column(name = "disk_size")
    private Integer diskSize;

    /**
     * 显卡型号
     */
    @Column(name = "gpu_model")
    private String gpuModel;

    /**
     * 显卡品牌
     */
    @Column(name = "gpu_brand")
    private String gpuBrand;

    /**
     * 显存容量(GB)
     */
    @Column(name = "gpu_memory_size")
    private Integer gpuMemorySize;

    /**
     * 显存类型
     */
    @Column(name = "gpu_memory_type")
    private String gpuMemoryType;

    /**
     * 显示器品牌
     */
    @Column(name = "monitor_brand")
    private String monitorBrand;

    /**
     * 显示器型号
     */
    @Column(name = "monitor_model")
    private String monitorModel;

    /**
     * 显示器分辨率
     */
    @Column(name = "monitor_resolution")
    private String monitorResolution;

    /**
     * 显示器接口类型
     */
    @Column(name = "monitor_interface")
    private String monitorInterface;

    /**
     * 根据 DTO 更新属性
     *
     * @param dto 数据传输对象 DTO
     */
    public void updateFromDto(AssetPcDto dto) {
        if (dto == null) {
            return;
        }
        // 更新基础属性
        if (dto.getSource() != null) {
            this.setSource(dto.getSource());
        }
        if (dto.getType() != null) {
            this.setType(dto.getType());
        }
        if (dto.getOwner() != null) {
            this.setOwner(dto.getOwner());
        }
        if (dto.getStatus() != null) {
            this.setStatus(dto.getStatus());
        }
        if (dto.getAreaCode() != null) {
            this.setAreaCode(dto.getAreaCode());
        }
        CityInfo cityInfo = dto.getCityInfo();
        if (cityInfo != null) {
            this.setCountry("中国"); // 默认设置国家为中国
            if (cityInfo.getProvince() != null) {
                this.setProvince(cityInfo.getProvince());
            }
            if (cityInfo.getCity() != null) {
                this.setCity(cityInfo.getCity());
            }
            if (cityInfo.getDistrict() != null) {
                this.setCounty(cityInfo.getDistrict());
            }
            if (cityInfo.getCode() != null) {
                this.setAreaCode(cityInfo.getCode().toString());
            }
        } else {
            if (dto.getCountry() != null) {
                this.setCountry(dto.getCountry());
            }
            if (dto.getProvince() != null) {
                this.setProvince(dto.getProvince());
            }
            if (dto.getCity() != null) {
                this.setCity(dto.getCity());
            }
            if (dto.getCounty() != null) {
                this.setCounty(dto.getCounty());
            }
        }
        if (dto.getLabel() != null) {
            this.setLabel(Arrays.stream(dto.getLabel().split(",")).toList());
        }
        if (dto.getAccess() != null) {
            this.setAccess(dto.getAccess());
        }
        if (dto.getLevel() != null) {
            this.setLevel(dto.getLevel());
        }
        if (dto.getRisk() != null) {
            this.setRisk(dto.getRisk());
        }
        if (dto.getManufacturer() != null) {
            this.setManufacturer(dto.getManufacturer());
        }
        if (dto.getModel() != null) {
            this.setModel(dto.getModel());
        }
        if (dto.getArchitecture() != null) {
            this.setArchitecture(dto.getArchitecture());
        }
        if (dto.getSystemName() != null) {
            this.setSystemName(dto.getSystemName());
        }
        if (dto.getSystemVersion() != null) {
            this.setSystemVersion(dto.getSystemVersion());
        }
        if (dto.getCpuModel() != null) {
            this.setCpuModel(dto.getCpuModel());
        }
        if (dto.getCpuCores() != null) {
            this.setCpuCores(dto.getCpuCores());
        }
        if (dto.getMemorySize() != null) {
            this.setMemorySize(dto.getMemorySize());
        }
        if (dto.getDiskSize() != null) {
            this.setDiskSize(dto.getDiskSize());
        }
        if (dto.getNetType() != null) {
            this.setNetType(dto.getNetType());
        }
        if (dto.getLanIp() != null) {
            this.setLanIp(dto.getLanIp());
        }
        if (dto.getWanIp() != null) {
            this.setWanIp(dto.getWanIp());
        }
        if (dto.getGpuModel() != null) {
            this.setGpuModel(dto.getGpuModel());
        }
        if (dto.getGpuBrand() != null) {
            this.setGpuBrand(dto.getGpuBrand());
        }
        if (dto.getGpuMemorySize() != null) {
            this.setGpuMemorySize(dto.getGpuMemorySize());
        }
        if (dto.getGpuMemoryType() != null) {
            this.setGpuMemoryType(dto.getGpuMemoryType());
        }
        if (dto.getMonitorBrand() != null) {
            this.setMonitorBrand(dto.getMonitorBrand());
        }
        if (dto.getMonitorModel() != null) {
            this.setMonitorModel(dto.getMonitorModel());
        }
        if (dto.getMonitorResolution() != null) {
            this.setMonitorResolution(dto.getMonitorResolution());
        }
        if (dto.getMonitorInterface() != null) {
            this.setMonitorInterface(dto.getMonitorInterface());
        }
        // 如果insertTime为空，则设置为当前时间
        if (this.getInsertTime() == null) {
            this.setInsertTime(new Date());
        }
        // 更新修改时间
        this.setUpdateTime(new Date());
    }
}
