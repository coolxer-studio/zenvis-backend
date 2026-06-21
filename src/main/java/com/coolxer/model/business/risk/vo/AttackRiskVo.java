package com.coolxer.model.business.risk.vo;

import com.coolxer.dao.clickhouse.entity.risk.AttackRisk;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AttackRiskVo implements Serializable {
    private String id;
    private String userId;
    private String startId;
    private String assetId;
    private String type;
    private String detail;
    private String verificationMethod;
    private String verificationResult;
    private String label;
    private String riskLevel;
    private String netType;
    private String lanIp;
    private String wanIp;
    private Date updateTime;
    private Date insertTime;

    public AttackRiskVo(AttackRisk risk) {
        if (risk == null) {
            return;
        }
        this.id = risk.getId();
        this.userId = risk.getUserId();
        this.startId = risk.getStartId();
        this.assetId = risk.getAssetId();
        this.type = risk.getType();
        this.detail = risk.getDetail();
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