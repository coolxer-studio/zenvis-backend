package com.coolxer.model.business.operation.dto;

import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;

import java.util.Date;

@Data
public class CrashEventDto extends SortPageDto {
    private String id;
    private String userId;
    private String startId;
    private String message;
    private String stackTrace;
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
} 