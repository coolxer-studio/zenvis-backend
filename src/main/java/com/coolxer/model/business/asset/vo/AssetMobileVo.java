package com.coolxer.model.business.asset.vo;

import com.coolxer.dao.clickhouse.entity.asset.AssetMobile;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 资产移动设备视图对象
 */
@Data
public class AssetMobileVo implements Serializable {

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

    // 移动设备特有属性
    private String brand;
    private String model;
    private String manufacturer;
    private String systemName;
    private String systemVersion;
    private String androidId;
    private String buildId;
    private String bluetoothMac;
    private String displayInfo;
    private String appMode;
    private String instructionSet1;
    private String deviceFingerprint;
    private String hostname;
    private String gyroscopeInfo;
    private String location2;
    private String cellAreaCode;
    private Integer nearbyCellCount;
    private String bootloader;
    private String deviceForm;
    private String screenResolution;
    private String osName;
    private String imei;
    private String imsi;
    private String networkMac;
    private String deviceInfo;
    private String sdkVersion;
    private String systemBoot;
    private String serialNumber;
    private String hardwareName;
    private String instructionSet2;
    private String motherboardInfo;
    private String buildDate;
    private String timezone;
    private String wifiMac;
    private String cellId;
    private String carrierType;
    private String netType;
    private String lanIp;
    private String wanIp;
    private Date updateTime;
    private Date insertTime;

    public AssetMobileVo(AssetMobile assetMobile) {
        if (assetMobile == null) {
            return;
        }

        if (assetMobile.getId() != null) {
            this.id = assetMobile.getId();
        }
        if (assetMobile.getSource() != null) {
            this.source = assetMobile.getSource().getDescription();
        }
        if (assetMobile.getType() != null) {
            this.type = assetMobile.getType().getDescription();
        }
        if (assetMobile.getOwner() != null) {
            this.owner = assetMobile.getOwner().getDescription();
        }
        if (assetMobile.getStatus() != null) {
            this.status = assetMobile.getStatus().getDescription();
        }
        if (assetMobile.getAreaCode() != null) {
            this.location = String.join(",",
                    assetMobile.getCountry() != null ? assetMobile.getCountry() : "",
                    assetMobile.getProvince() != null ? assetMobile.getProvince() : "",
                    assetMobile.getCity() != null ? assetMobile.getCity() : "",
                    assetMobile.getCounty() != null ? assetMobile.getCounty() : ""
            );
        }
        if (assetMobile.getLabel() != null) {
            this.label = assetMobile.getLabel().stream().collect(Collectors.joining(","));
        }
        if (assetMobile.getAccess() != null) {
            this.access = assetMobile.getAccess();
        }
        if (assetMobile.getLevel() != null) {
            this.level = assetMobile.getLevel().getDescription();
        }
        if (assetMobile.getRisk() != null) {
            this.risk = assetMobile.getRisk().getDescription();
        }
        if (assetMobile.getRiskInfo() != null) {
            this.riskInfo = assetMobile.getRiskInfo();
        }
        if (assetMobile.getInfo() != null) {
            this.info = assetMobile.getInfo();
        }
        if (assetMobile.getUpdateTime() != null) {
            this.updateTime = assetMobile.getUpdateTime();
        }
        if (assetMobile.getInsertTime() != null) {
            this.insertTime = assetMobile.getInsertTime();
        }

        // 移动设备特有属性
        if (assetMobile.getBrand() != null) {
            this.brand = assetMobile.getBrand();
        }
        if (assetMobile.getModel() != null) {
            this.model = assetMobile.getModel();
        }
        if (assetMobile.getManufacturer() != null) {
            this.manufacturer = assetMobile.getManufacturer();
        }
        if (assetMobile.getSystemName() != null) {
            this.systemName = assetMobile.getSystemName();
        }
        if (assetMobile.getSystemVersion() != null) {
            this.systemVersion = assetMobile.getSystemVersion();
        }
        if (assetMobile.getAndroidId() != null) {
            this.androidId = assetMobile.getAndroidId();
        }
        if (assetMobile.getBuildId() != null) {
            this.buildId = assetMobile.getBuildId();
        }
        if (assetMobile.getBluetoothMac() != null) {
            this.bluetoothMac = assetMobile.getBluetoothMac();
        }
        if (assetMobile.getDisplayInfo() != null) {
            this.displayInfo = assetMobile.getDisplayInfo();
        }
        if (assetMobile.getAppMode() != null) {
            this.appMode = assetMobile.getAppMode();
        }
        if (assetMobile.getInstructionSet1() != null) {
            this.instructionSet1 = assetMobile.getInstructionSet1();
        }
        if (assetMobile.getDeviceFingerprint() != null) {
            this.deviceFingerprint = assetMobile.getDeviceFingerprint();
        }
        if (assetMobile.getHostname() != null) {
            this.hostname = assetMobile.getHostname();
        }
        if (assetMobile.getGyroscopeInfo() != null) {
            this.gyroscopeInfo = assetMobile.getGyroscopeInfo();
        }
        if (assetMobile.getLocation() != null) {
            this.location2 = assetMobile.getLocation();
        }
        if (assetMobile.getCellAreaCode() != null) {
            this.cellAreaCode = assetMobile.getCellAreaCode();
        }
        if (assetMobile.getNearbyCellCount() != null) {
            this.nearbyCellCount = assetMobile.getNearbyCellCount();
        }
        if (assetMobile.getBootloader() != null) {
            this.bootloader = assetMobile.getBootloader();
        }
        if (assetMobile.getDeviceForm() != null) {
            this.deviceForm = assetMobile.getDeviceForm();
        }
        if (assetMobile.getScreenResolution() != null) {
            this.screenResolution = assetMobile.getScreenResolution();
        }
        if (assetMobile.getOsName() != null) {
            this.osName = assetMobile.getOsName();
        }
        if (assetMobile.getImei() != null) {
            this.imei = assetMobile.getImei();
        }
        if (assetMobile.getImsi() != null) {
            this.imsi = assetMobile.getImsi();
        }
        if (assetMobile.getNetworkMac() != null) {
            this.networkMac = assetMobile.getNetworkMac();
        }
        if (assetMobile.getDeviceInfo() != null) {
            this.deviceInfo = assetMobile.getDeviceInfo();
        }
        if (assetMobile.getSdkVersion() != null) {
            this.sdkVersion = assetMobile.getSdkVersion();
        }
        if (assetMobile.getSystemBoot() != null) {
            this.systemBoot = assetMobile.getSystemBoot();
        }
        if (assetMobile.getSerialNumber() != null) {
            this.serialNumber = assetMobile.getSerialNumber();
        }
        if (assetMobile.getHardwareName() != null) {
            this.hardwareName = assetMobile.getHardwareName();
        }
        if (assetMobile.getInstructionSet2() != null) {
            this.instructionSet2 = assetMobile.getInstructionSet2();
        }
        if (assetMobile.getMotherboardInfo() != null) {
            this.motherboardInfo = assetMobile.getMotherboardInfo();
        }
        if (assetMobile.getBuildDate() != null) {
            this.buildDate = assetMobile.getBuildDate();
        }
        if (assetMobile.getTimezone() != null) {
            this.timezone = assetMobile.getTimezone();
        }
        if (assetMobile.getWifiMac() != null) {
            this.wifiMac = assetMobile.getWifiMac();
        }
        if (assetMobile.getCellId() != null) {
            this.cellId = assetMobile.getCellId();
        }
        if (assetMobile.getCarrierType() != null) {
            this.carrierType = assetMobile.getCarrierType();
        }
        if (assetMobile.getNetType() != null) {
            this.netType = assetMobile.getNetType();
        }
        if (assetMobile.getLanIp() != null) {
            this.lanIp = assetMobile.getLanIp();
        }
        if (assetMobile.getWanIp() != null) {
            this.wanIp = assetMobile.getWanIp();
        }
    }
} 