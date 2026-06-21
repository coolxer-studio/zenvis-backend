package com.coolxer.model.business.asset.vo;

import com.coolxer.dao.clickhouse.entity.asset.AssetHost;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 系统信息
 */
@Data
public class AssetHostVo implements Serializable {

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
    private String room;
    private String cabinetNo;
    private String positionNo;
    private String manufacturer;
    private String model;
    private String architecture;
    private String systemName;
    private String systemVersion;
    private String cpuModel;
    private Integer cpuCores;
    private Integer memorySize;
    private Integer diskSize;
    private String netType;
    private String lanIp;
    private String wanIp;
    private Date updateTime;
    private Date insertTime;

    public AssetHostVo(AssetHost assetHost) {
        if (assetHost == null) {
            return;
        }

        if (assetHost.getId() != null) {
            this.id = assetHost.getId();
        }
        if (assetHost.getSource() != null) {
            this.source = assetHost.getSource().getDescription();
        }
        if (assetHost.getType() != null) {
            this.type = assetHost.getType().getDescription();
        }
        if (assetHost.getOwner() != null) {
            this.owner = assetHost.getOwner().getDescription();
        }
        if (assetHost.getStatus() != null) {
            this.status = assetHost.getStatus().getDescription();
        }
        if (assetHost.getAreaCode() != null) {
            this.location = String.join(",",
                    assetHost.getCountry() != null ? assetHost.getCountry() : "",
                    assetHost.getProvince() != null ? assetHost.getProvince() : "",
                    assetHost.getCity() != null ? assetHost.getCity() : "",
                    assetHost.getCounty() != null ? assetHost.getCounty() : ""
            );
        }
        if (assetHost.getLabel() != null) {
            this.label = assetHost.getLabel().stream().collect(Collectors.joining(","));
        }
        if (assetHost.getAccess() != null) {
            this.access = assetHost.getAccess();
        }
        if (assetHost.getLevel() != null) {
            this.level = assetHost.getLevel().getDescription();
        }
        if (assetHost.getRisk() != null) {
            this.risk = assetHost.getRisk().getDescription();
        }
        if (assetHost.getRiskInfo() != null) {
            this.riskInfo = assetHost.getRiskInfo();
        }
        if (assetHost.getInfo() != null) {
            this.info = assetHost.getInfo();
        }
        if (assetHost.getUpdateTime() != null) {
            this.updateTime = assetHost.getUpdateTime();
        }
        if (assetHost.getInsertTime() != null) {
            this.insertTime = assetHost.getInsertTime();
        }
        if (assetHost.getManufacturer() != null) {
            this.manufacturer = assetHost.getManufacturer();
        }
        if (assetHost.getModel() != null) {
            this.model = assetHost.getModel();
        }
        if (assetHost.getArchitecture() != null) {
            this.architecture = assetHost.getArchitecture();
        }
        if (assetHost.getSystemName() != null) {
            this.systemName = assetHost.getSystemName();
        }
        if (assetHost.getSystemVersion() != null) {
            this.systemVersion = assetHost.getSystemVersion();
        }
        if (assetHost.getNetType() != null) {
            this.netType = assetHost.getNetType();
        }
        if (assetHost.getLanIp() != null) {
            this.lanIp = assetHost.getLanIp();
        }
        if (assetHost.getWanIp() != null) {
            this.wanIp = assetHost.getWanIp();
        }
        if (assetHost.getRoom() != null) {
            this.room = assetHost.getRoom();
        }
        if (assetHost.getCabinetNo() != null) {
            this.cabinetNo = assetHost.getCabinetNo();
        }
        if (assetHost.getPositionNo() != null) {
            this.positionNo = assetHost.getPositionNo();
        }
        if (assetHost.getCpuModel() != null) {
            this.cpuModel = assetHost.getCpuModel();
        }
        if (assetHost.getCpuCores() != null) {
            this.cpuCores = assetHost.getCpuCores();
        }
        if (assetHost.getMemorySize() != null) {
            this.memorySize = assetHost.getMemorySize();
        }
        if (assetHost.getDiskSize() != null) {
            this.diskSize = assetHost.getDiskSize();
        }
    }
}
