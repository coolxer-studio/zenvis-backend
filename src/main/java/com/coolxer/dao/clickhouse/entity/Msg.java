package com.coolxer.dao.clickhouse.entity;

import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


/**
 * clickhouse：msg 业务表
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_MSG)
public class Msg {

    @Id
    private Long id;

    @Column(name = "user_id")
    private String userId;

    private String guid;

    @Column(name = "start_id")
    private Long startId;

    @Column(name = "sdk_version")
    private String sdkVersion;

    @Column(name = "app_id")
    private Integer appId;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "app_package")
    private String appPackage;

    @Column(name = "app_version")
    private String appVersion;

    private String platform;
    private String manufacturer;
    private String model;

    @Column(name = "system_name")
    private String systemName;

    @Column(name = "system_version")
    private String systemVersion;

    @Column(name = "net_type")
    private String netType;

    @Column(name = "lan_ip")
    private String lanIp;

    @Column(name = "wan_ip")
    private String wanIp;

    private Double latitude;
    private Double longitude;
    private String country;
    private String province;
    private String city;
    private String county;
    private String thoroughfare;

    @Column(name = "client_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clientTime;

    @Column(name = "server_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date serverTime;

    private String rule;
    @Column(name = "fact_type")
    private String factType;

    @Column(columnDefinition = "JSON")
    private String fact;

    @ElementCollection
    @CollectionTable(name = "agenda_tags", joinColumns = @JoinColumn(name = "message_id"))
    @Column(name = "tag")
    private List<String> agendaTags;

    @ElementCollection
    @CollectionTable(name = "agendas", joinColumns = @JoinColumn(name = "message_id"))
    @Column(name = "agenda")
    private List<String> agendas;

    @ElementCollection
    @CollectionTable(name = "punish_types", joinColumns = @JoinColumn(name = "message_id"))
    @Column(name = "type")
    private List<Integer> punishTypes;

    @ElementCollection
    @CollectionTable(name = "punishes", joinColumns = @JoinColumn(name = "message_id"))
    @Column(name = "punish")
    private List<String> punishes;

    @Column(name = "insert_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertTime;


}
