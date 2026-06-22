package com.coolxer.model.business.operation.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PerformanceEventVo implements Serializable {
    private String id;
    private String userId;
    private String startId;
    private Double cpuUsage;
    private Double cpuUser;
    private Double cpuSystem;
    private Double cpuIdle;
    private Long memoryTotal;
    private Long memoryUsed;
    private Double memoryUsage;
    private Long virtualMemoryTotal;
    private Long virtualMemoryUsed;
    private Long swapTotal;
    private Long swapUsed;
    private Double diskReadSpeed;
    private Double diskWriteSpeed;
    private Double diskThroughput;
    private Integer diskIops;
    private Double networkSendRate;
    private Double networkReceiveRate;
    private Double networkPacketLoss;
    private Double networkLatency;
    private double longitude;
    private double latitude;
    private String country;
    private String province;
    private String city;
    private String county;
    private String netType;
    private String lanIp;
    private String wanIp;
    private Date updateTime;
    private Date insertTime;
    private Date eventTime;

    private String location;
} 