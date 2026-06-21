package com.coolxer.dao.clickhouse.entity.operation;

import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 应用启动事件实体类
 * 记录应用程序启动相关的信息
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_OPERATION_START_EVENT)
public class StartEvent {

    /**
     * 事件唯一编号信息
     */
    @Id
    private String id;

    /**
     * 用户ID，用户的唯一标识
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 设备ID，设备的唯一标识
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 设备型号
     */
    @Column(name = "device_model")
    private String deviceModel;

    /**
     * 设备系统
     */
    @Column(name = "device_os")
    private String deviceOs;

    /**
     * 应用ID
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 应用名称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 应用包名
     */
    @Column(name = "package_name")
    private String packageName;

    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

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

    /**
     * 事件发生时间
     */
    @Column(name = "event_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;

}
