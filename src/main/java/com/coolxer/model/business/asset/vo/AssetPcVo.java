package com.coolxer.model.business.asset.vo;

import com.coolxer.dao.clickhouse.entity.asset.AssetPc;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 资产PC设备视图对象
 */
@Data
public class AssetPcVo implements Serializable {

    private String id;
    private String source;
    private String type;
    private String owner;
    private String status;
    private String location;
    private String label;
    private Boolean access;
    private String level;
    private String risk;
    private String riskInfo;
    private String info;

    // PC设备特有属性
    private String manufacturer;
    private String model;
    private String architecture;
    private String systemName;
    private String systemVersion;
    private String cpuModel;
    private Integer cpuCores;
    private Integer memorySize;
    private Integer diskSize;
    private String gpuModel;
    private String gpuBrand;
    private Integer gpuMemorySize;
    private String gpuMemoryType;
    private String monitorBrand;
    private String monitorModel;
    private String monitorResolution;
    private String monitorInterface;
    private String netType;
    private String lanIp;
    private String wanIp;
    private Date updateTime;
    private Date insertTime;

    public AssetPcVo(AssetPc assetPc) {
        if (assetPc == null) {
            return;
        }

        if (assetPc.getId() != null) {
            this.id = assetPc.getId();
        }
        if (assetPc.getSource() != null) {
            this.source = assetPc.getSource().getDescription();
        }
        if (assetPc.getType() != null) {
            this.type = assetPc.getType().getDescription();
        }
        if (assetPc.getOwner() != null) {
            this.owner = assetPc.getOwner().getDescription();
        }
        if (assetPc.getStatus() != null) {
            this.status = assetPc.getStatus().getDescription();
        }
        if (assetPc.getAreaCode() != null) {
            this.location = String.join(",",
                    assetPc.getCountry() != null ? assetPc.getCountry() : "",
                    assetPc.getProvince() != null ? assetPc.getProvince() : "",
                    assetPc.getCity() != null ? assetPc.getCity() : "",
                    assetPc.getCounty() != null ? assetPc.getCounty() : ""
            );
        }
        if (assetPc.getLabel() != null) {
            this.label = assetPc.getLabel().stream().collect(Collectors.joining(","));
        }
        if (assetPc.getAccess() != null) {
            this.access = assetPc.getAccess();
        }
        if (assetPc.getLevel() != null) {
            this.level = assetPc.getLevel().getDescription();
        }
        if (assetPc.getRisk() != null) {
            this.risk = assetPc.getRisk().getDescription();
        }
        if (assetPc.getRiskInfo() != null) {
            this.riskInfo = assetPc.getRiskInfo();
        }
        if (assetPc.getInfo() != null) {
            this.info = assetPc.getInfo();
        }
        if (assetPc.getUpdateTime() != null) {
            this.updateTime = assetPc.getUpdateTime();
        }
        if (assetPc.getInsertTime() != null) {
            this.insertTime = assetPc.getInsertTime();
        }

        // PC设备特有属性
        if (assetPc.getManufacturer() != null) {
            this.manufacturer = assetPc.getManufacturer();
        }
        if (assetPc.getModel() != null) {
            this.model = assetPc.getModel();
        }
        if (assetPc.getArchitecture() != null) {
            this.architecture = assetPc.getArchitecture();
        }
        if (assetPc.getSystemName() != null) {
            this.systemName = assetPc.getSystemName();
        }
        if (assetPc.getSystemVersion() != null) {
            this.systemVersion = assetPc.getSystemVersion();
        }
        if (assetPc.getCpuModel() != null) {
            this.cpuModel = assetPc.getCpuModel();
        }
        if (assetPc.getCpuCores() != null) {
            this.cpuCores = assetPc.getCpuCores();
        }
        if (assetPc.getMemorySize() != null) {
            this.memorySize = assetPc.getMemorySize();
        }
        if (assetPc.getDiskSize() != null) {
            this.diskSize = assetPc.getDiskSize();
        }
        if (assetPc.getGpuModel() != null) {
            this.gpuModel = assetPc.getGpuModel();
        }
        if (assetPc.getGpuBrand() != null) {
            this.gpuBrand = assetPc.getGpuBrand();
        }
        if (assetPc.getGpuMemorySize() != null) {
            this.gpuMemorySize = assetPc.getGpuMemorySize();
        }
        if (assetPc.getGpuMemoryType() != null) {
            this.gpuMemoryType = assetPc.getGpuMemoryType();
        }
        if (assetPc.getMonitorBrand() != null) {
            this.monitorBrand = assetPc.getMonitorBrand();
        }
        if (assetPc.getMonitorModel() != null) {
            this.monitorModel = assetPc.getMonitorModel();
        }
        if (assetPc.getMonitorResolution() != null) {
            this.monitorResolution = assetPc.getMonitorResolution();
        }
        if (assetPc.getMonitorInterface() != null) {
            this.monitorInterface = assetPc.getMonitorInterface();
        }
        if (assetPc.getNetType() != null) {
            this.netType = assetPc.getNetType();
        }
        if (assetPc.getLanIp() != null) {
            this.lanIp = assetPc.getLanIp();
        }
        if (assetPc.getWanIp() != null) {
            this.wanIp = assetPc.getWanIp();
        }
    }
} 