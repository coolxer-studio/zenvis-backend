package com.coolxer.dao.clickhouse.entity.asset;

import com.coolxer.commons.enums.business.asset.*;
import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import com.coolxer.dao.clickhouse.converter.StringListConverter;
import com.coolxer.model.business.asset.dto.AssetIotDto;
import com.coolxer.model.business.asset.dto.CityInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * IOT资产表
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_ASSET_IOT)
public class AssetIot {

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

    // 特有信息
    /**
     * 设备名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 设备类型
     */
    @Column(name = "device_type")
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
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * 固件版本
     */
    @Column(name = "firmware_version")
    private String firmwareVersion;

    /**
     * 固件更新时间
     */
    @Column(name = "firmware_update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firmwareUpdateTime;

    /**
     * 电源类型
     */
    @Column(name = "power_type")
    private String powerType;

    /**
     * 电池电量
     */
    @Column(name = "battery_level")
    private Integer batteryLevel;

    /**
     * 传感器信息，json格式
     */
    @Column(name = "sensor_info")
    private String sensorInfo;

    /**
     * 通信协议
     */
    @Column(name = "communication_protocol")
    private String communicationProtocol;

    /**
     * 根据 DTO 更新属性
     *
     * @param dto 资产主机 DTO
     */
    public void updateFromDto(AssetIotDto dto) {
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

        // 更新 IoT 设备特有信息
        if (dto.getDeviceName() != null) {
            this.setDeviceName(dto.getDeviceName());
        }
        if (dto.getDeviceType() != null) {
            this.setDeviceType(dto.getDeviceType());
        }
        if (dto.getManufacturer() != null) {
            this.setManufacturer(dto.getManufacturer());
        }
        if (dto.getModel() != null) {
            this.setModel(dto.getModel());
        }
        if (dto.getSerialNumber() != null) {
            this.setSerialNumber(dto.getSerialNumber());
        }
        if (dto.getFirmwareVersion() != null) {
            this.setFirmwareVersion(dto.getFirmwareVersion());
        }
        if (dto.getFirmwareUpdateTime() != null) {
            this.setFirmwareUpdateTime(dto.getFirmwareUpdateTime());
        }
        if (dto.getPowerType() != null) {
            this.setPowerType(dto.getPowerType());
        }
        if (dto.getBatteryLevel() != null) {
            this.setBatteryLevel(dto.getBatteryLevel());
        }
        if (dto.getSensorInfo() != null) {
            this.setSensorInfo(dto.getSensorInfo());
        }
        if (dto.getCommunicationProtocol() != null) {
            this.setCommunicationProtocol(dto.getCommunicationProtocol());
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
