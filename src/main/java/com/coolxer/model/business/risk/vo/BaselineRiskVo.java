package com.coolxer.model.business.risk.vo;

import com.coolxer.dao.clickhouse.entity.risk.BaselineRisk;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaselineRiskVo implements Serializable {
    private String id;
    private String userId;
    private String startId;
    private String assetId;
    private String configurationName;
    private String expectedValue;
    private String currentValue;
    private String verificationMethod;
    private String verificationResult;
    private String label;
    private String riskLevel;
    private String netType;
    private String lanIp;
    private String wanIp;
    private Date updateTime;
    private Date insertTime;

    public BaselineRiskVo(BaselineRisk risk) {
        if (risk == null) {
            return;
        }
        this.id = risk.getId();
        this.userId = risk.getUserId();
        this.startId = risk.getStartId();
        this.assetId = risk.getAssetId();
        this.configurationName = risk.getConfigurationName();
        this.expectedValue = risk.getExpectedValue();
        this.currentValue = risk.getCurrentValue();
        this.verificationMethod = risk.getVerificationMethod();
        this.verificationResult = risk.getVerificationResult();
        if (risk.getLabel() != null) {
            this.label = String.join(",", risk.getLabel());
        }
        this.riskLevel = risk.getRiskLevel();
        this.netType = risk.getNetType();
        this.lanIp = risk.getLanIp();
        this.wanIp = risk.getWanIp();
        this.updateTime = risk.getUpdateTime();
        this.insertTime = risk.getInsertTime();
    }
} 