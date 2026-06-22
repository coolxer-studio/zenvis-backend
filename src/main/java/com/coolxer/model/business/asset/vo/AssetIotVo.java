package com.coolxer.model.business.asset.vo;

import com.coolxer.dao.clickhouse.entity.asset.AssetIot;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 资产IoT设备视图对象
 */
@Data
public class AssetIotVo implements Serializable {

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
    private String deviceName;
    private String deviceType;
    private String manufacturer;
    private String model;
    private String serialNumber;
    private String firmwareVersion;
    private Date firmwareUpdateTime;
    private String powerType;
    private Integer batteryLevel;
    private String sensorInfo;
    private String communicationProtocol;
    private String netType;
    private String lanIp;
    private String wanIp;
    private Date updateTime;
    private Date insertTime;

    public AssetIotVo(AssetIot assetIot) {
        if (assetIot == null) {
            return;
        }

        if (assetIot.getId() != null) {
            this.id = assetIot.getId();
        }
        if (assetIot.getSource() != null) {
            this.source = assetIot.getSource().getDescription();
        }
        if (assetIot.getType() != null) {
            this.type = assetIot.getType().getDescription();
        }
        if (assetIot.getOwner() != null) {
            this.owner = assetIot.getOwner().getDescription();
        }
        if (assetIot.getStatus() != null) {
            this.status = assetIot.getStatus().getDescription();
        }
        if (assetIot.getAreaCode() != null) {
            this.location = String.join(",",
                    assetIot.getCountry() != null ? assetIot.getCountry() : "",
                    assetIot.getProvince() != null ? assetIot.getProvince() : "",
                    assetIot.getCity() != null ? assetIot.getCity() : "",
                    assetIot.getCounty() != null ? assetIot.getCounty() : ""
            );
        }
        if (assetIot.getLabel() != null) {
            this.label = assetIot.getLabel().stream().collect(Collectors.joining(","));
        }
        if (assetIot.getAccess() != null) {
            this.access = assetIot.getAccess();
        }
        if (assetIot.getLevel() != null) {
            this.level = assetIot.getLevel().getDescription();
        }
        if (assetIot.getRisk() != null) {
            this.risk = assetIot.getRisk().getDescription();
        }
        if (assetIot.getRiskInfo() != null) {
            this.riskInfo = assetIot.getRiskInfo();
        }
        if (assetIot.getInfo() != null) {
            this.info = assetIot.getInfo();
        }
        if (assetIot.getUpdateTime() != null) {
            this.updateTime = assetIot.getUpdateTime();
        }
        if (assetIot.getInsertTime() != null) {
            this.insertTime = assetIot.getInsertTime();
        }

        // IoT设备特有属性
        if (assetIot.getDeviceName() != null) {
            this.deviceName = assetIot.getDeviceName();
        }
        if (assetIot.getDeviceType() != null) {
            this.deviceType = assetIot.getDeviceType();
        }
        if (assetIot.getManufacturer() != null) {
            this.manufacturer = assetIot.getManufacturer();
        }
        if (assetIot.getModel() != null) {
            this.model = assetIot.getModel();
        }
        if (assetIot.getSerialNumber() != null) {
            this.serialNumber = assetIot.getSerialNumber();
        }
        if (assetIot.getFirmwareVersion() != null) {
            this.firmwareVersion = assetIot.getFirmwareVersion();
        }
        if (assetIot.getFirmwareUpdateTime() != null) {
            this.firmwareUpdateTime = assetIot.getFirmwareUpdateTime();
        }
        if (assetIot.getPowerType() != null) {
            this.powerType = assetIot.getPowerType();
        }
        if (assetIot.getBatteryLevel() != null) {
            this.batteryLevel = assetIot.getBatteryLevel();
        }
        if (assetIot.getSensorInfo() != null) {
            this.sensorInfo = assetIot.getSensorInfo();
        }
        if (assetIot.getCommunicationProtocol() != null) {
            this.communicationProtocol = assetIot.getCommunicationProtocol();
        }
        if (assetIot.getNetType() != null) {
            this.netType = assetIot.getNetType();
        }
        if (assetIot.getLanIp() != null) {
            this.lanIp = assetIot.getLanIp();
        }
        if (assetIot.getWanIp() != null) {
            this.wanIp = assetIot.getWanIp();
        }
    }
} 