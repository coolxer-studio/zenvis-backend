package com.coolxer.dao.clickhouse.entity.asset;

import com.coolxer.commons.enums.business.asset.*;
import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import com.coolxer.dao.clickhouse.converter.StringListConverter;
import com.coolxer.model.business.asset.dto.AssetMobileDto;
import com.coolxer.model.business.asset.dto.CityInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 移动端资产实体
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_ASSET_MOBILE)
public class AssetMobile {

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

    // 移动设备资产特有信息
    /**
     * 设备品牌
     */
    private String brand;

    /**
     * 设备型号代码
     */
    @Column(name = "model")
    private String model;

    /**
     * 设备制造商
     */
    private String manufacturer;

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
     * AndroidID
     */
    @Column(name = "android_id")
    private String androidId;

    /**
     * BuildID
     */
    @Column(name = "build_id")
    private String buildId;

    /**
     * 蓝牙 MAC
     */
    @Column(name = "bluetooth_mac")
    private String bluetoothMac;

    /**
     * 显示设备信息
     */
    @Column(name = "display_info")
    private String displayInfo;

    /**
     * 应用运行模式
     */
    @Column(name = "app_mode")
    private String appMode;

    /**
     * 指令集 1
     */
    @Column(name = "instruction_set1")
    private String instructionSet1;

    /**
     * 设备指纹
     */
    @Column(name = "device_fingerprint")
    private String deviceFingerprint;

    /**
     * 主机名
     */
    private String hostname;

    /**
     * 陀螺仪信息
     */
    @Column(name = "gyroscope_info")
    private String gyroscopeInfo;

    /**
     * 经纬度
     */
    private String location;

    /**
     * 基站位置区域码
     */
    @Column(name = "cell_area_code")
    private String cellAreaCode;

    /**
     * 临近基站个数
     */
    @Column(name = "nearby_cell_count")
    private Integer nearbyCellCount;

    /**
     * Bootloader
     */
    private String bootloader;

    /**
     * 设备形态
     */
    @Column(name = "device_form")
    private String deviceForm;

    /**
     * 屏幕分辨率
     */
    @Column(name = "screen_resolution")
    private String screenResolution;

    /**
     * 系统名称
     */
    @Column(name = "os_name")
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
    @Column(name = "network_mac")
    private String networkMac;

    /**
     * 设备信息
     */
    @Column(name = "device_info")
    private String deviceInfo;

    /**
     * 系统 SDK 版本
     */
    @Column(name = "sdk_version")
    private String sdkVersion;

    /**
     * 系统引导
     */
    @Column(name = "system_boot")
    private String systemBoot;

    /**
     * 序列号
     */
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * 硬件名称
     */
    @Column(name = "hardware_name")
    private String hardwareName;

    /**
     * 指令集 2
     */
    @Column(name = "instruction_set2")
    private String instructionSet2;

    /**
     * 主板信息
     */
    @Column(name = "motherboard_info")
    private String motherboardInfo;

    /**
     * Build.date
     */
    @Column(name = "build_date")
    private String buildDate;

    /**
     * 时区设置
     */
    private String timezone;

    /**
     * WiFi - MAC
     */
    @Column(name = "wifi_mac")
    private String wifiMac;

    /**
     * 基站编号
     */
    @Column(name = "cell_id")
    private String cellId;

    /**
     * 运营商类型
     */
    @Column(name = "carrier_type")
    private String carrierType;

    /**
     * 根据 DTO 更新属性
     *
     * @param dto 数据传输对象 DTO
     */
    public void updateFromDto(AssetMobileDto dto) {
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

        // 更新移动设备特有信息
        if (dto.getBrand() != null) {
            this.setBrand(dto.getBrand());
        }
        if (dto.getModel() != null) {
            this.setModel(dto.getModel());
        }
        if (dto.getManufacturer() != null) {
            this.setManufacturer(dto.getManufacturer());
        }
        if (dto.getSystemName() != null) {
            this.setSystemName(dto.getSystemName());
        }
        if (dto.getSystemVersion() != null) {
            this.setSystemVersion(dto.getSystemVersion());
        }
        if (dto.getAndroidId() != null) {
            this.setAndroidId(dto.getAndroidId());
        }
        if (dto.getBuildId() != null) {
            this.setBuildId(dto.getBuildId());
        }
        if (dto.getBluetoothMac() != null) {
            this.setBluetoothMac(dto.getBluetoothMac());
        }
        if (dto.getDisplayInfo() != null) {
            this.setDisplayInfo(dto.getDisplayInfo());
        }
        if (dto.getAppMode() != null) {
            this.setAppMode(dto.getAppMode());
        }
        if (dto.getInstructionSet1() != null) {
            this.setInstructionSet1(dto.getInstructionSet1());
        }
        if (dto.getDeviceFingerprint() != null) {
            this.setDeviceFingerprint(dto.getDeviceFingerprint());
        }
        if (dto.getHostname() != null) {
            this.setHostname(dto.getHostname());
        }
        if (dto.getGyroscopeInfo() != null) {
            this.setGyroscopeInfo(dto.getGyroscopeInfo());
        }
        if (dto.getLocation() != null) {
            this.setLocation(dto.getLocation());
        }
        if (dto.getCellAreaCode() != null) {
            this.setCellAreaCode(dto.getCellAreaCode());
        }
        if (dto.getNearbyCellCount() != null) {
            this.setNearbyCellCount(dto.getNearbyCellCount());
        }
        if (dto.getBootloader() != null) {
            this.setBootloader(dto.getBootloader());
        }
        if (dto.getDeviceForm() != null) {
            this.setDeviceForm(dto.getDeviceForm());
        }
        if (dto.getScreenResolution() != null) {
            this.setScreenResolution(dto.getScreenResolution());
        }
        if (dto.getOsName() != null) {
            this.setOsName(dto.getOsName());
        }
        if (dto.getImei() != null) {
            this.setImei(dto.getImei());
        }
        if (dto.getImsi() != null) {
            this.setImsi(dto.getImsi());
        }
        if (dto.getNetworkMac() != null) {
            this.setNetworkMac(dto.getNetworkMac());
        }
        if (dto.getDeviceInfo() != null) {
            this.setDeviceInfo(dto.getDeviceInfo());
        }
        if (dto.getSdkVersion() != null) {
            this.setSdkVersion(dto.getSdkVersion());
        }
        if (dto.getSystemBoot() != null) {
            this.setSystemBoot(dto.getSystemBoot());
        }
        if (dto.getSerialNumber() != null) {
            this.setSerialNumber(dto.getSerialNumber());
        }
        if (dto.getHardwareName() != null) {
            this.setHardwareName(dto.getHardwareName());
        }
        if (dto.getInstructionSet2() != null) {
            this.setInstructionSet2(dto.getInstructionSet2());
        }
        if (dto.getMotherboardInfo() != null) {
            this.setMotherboardInfo(dto.getMotherboardInfo());
        }
        if (dto.getBuildDate() != null) {
            this.setBuildDate(dto.getBuildDate());
        }
        if (dto.getTimezone() != null) {
            this.setTimezone(dto.getTimezone());
        }
        if (dto.getWifiMac() != null) {
            this.setWifiMac(dto.getWifiMac());
        }
        if (dto.getCellId() != null) {
            this.setCellId(dto.getCellId());
        }
        if (dto.getCarrierType() != null) {
            this.setCarrierType(dto.getCarrierType());
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

        // 如果insertTime为空，则设置为当前时间
        if (this.getInsertTime() == null) {
            this.setInsertTime(new Date());
        }
        // 更新修改时间
        this.setUpdateTime(new Date());
    }

} 