package com.coolxer.model.business.operation.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApiCallEventVo implements Serializable {
    private String id;
    private String userId;
    private String startId;
    private String caller;
    private String callee;
    private String functionName;
    private String functionParams;
    private String functionReturn;
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