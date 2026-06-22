package com.coolxer.model.business.risk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WeakRiskDto {
    private String id;
    private String userId;
    private String startId;
    private String assetId;
    private String username;
    private String password;
    private String passwordType;
    private String verificationMethod;
    private String verificationResult;
    private List<String> label;
    private String riskLevel;
    private String netType;
    private String lanIp;
    private String wanIp;
} 