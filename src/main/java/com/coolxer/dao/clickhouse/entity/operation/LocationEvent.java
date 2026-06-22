package com.coolxer.dao.clickhouse.entity.operation;

import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 位置事件实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_OPERATION_LOCATION_EVENT)
public class LocationEvent {

    /**
     * 事件唯一标识
     */
    @Id
    private String id;

    /**
     * 用户ID，用户的唯一标识
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 启动ID，每次启动的唯一标识
     */
    @Column(name = "start_id")
    private String startId;

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
