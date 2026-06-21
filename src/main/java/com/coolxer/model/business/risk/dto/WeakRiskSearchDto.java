package com.coolxer.model.business.risk.dto;

import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeakRiskSearchDto extends SortPageDto {
    private String id;
    private String userId;
    private String startId;
    private String assetId;
    private String username;
    private String passwordType;
    private String riskLevel;
    private String netType;
    private String lanIp;
    private String wanIp;
    private String updateTime;
    private String insertTime;
} 