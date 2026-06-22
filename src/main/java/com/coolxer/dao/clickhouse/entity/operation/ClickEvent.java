package com.coolxer.dao.clickhouse.entity.operation;

import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 点击事件实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_OPERATION_CLICK_EVENT)
public class ClickEvent {

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
     * 点击坐标（x）
     */
    @Column(name = "click_x")
    private double clickX;

    /**
     * 点击坐标（y）
     */
    @Column(name = "click_y")
    private double clickY;

    /**
     * 点击组件类型
     */
    @Column(name = "component_type")
    private String componentType;

    /**
     * 组件名称
     */
    @Column(name = "component_name")
    private String componentName;

    /**
     * 所属页面路径
     */
    @Column(name = "page_path")
    private String pagePath;

    /**
     * 所属页面名称
     */
    @Column(name = "page_name")
    private String pageName;

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
