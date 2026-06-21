package com.coolxer.model.business.asset.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AssetCountVo implements Serializable {
    private long hostTotal;
    private long hostIncrease;
    private long mobileTotal;
    private long mobileIncrease;
    private long pcTotal;
    private long pcIncrease;
    private long iotTotal;
    private long iotIncrease;
    private long probeTotal;
    private long probeIncrease;
    private long appTotal;
    private long appIncrease;
    private long serviceTotal;
    private long serviceIncrease;
    private long apiTotal;
    private long apiIncrease;
    private long logTotal;
    private long logIncrease;
    private long fileTotal;
    private long fileIncrease;
} 