package com.coolxer.model.business.asset.vo;

import com.coolxer.dao.clickhouse.entity.asset.AssetProbe;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据探针SDK资产视图对象
 */
@Data
public class AssetProbeVo implements Serializable {

    private String id;
    private String source;
    private String type;
    private String owner;
    private String status;
    private String label;
    private Boolean access;
    private String level;
    private String risk;
    private String riskInfo;
    private String info;

    // 数据探针SDK特有字段
    private String probeName;
    private String probeVersion;
    private String probeType;
    private String language;
    private String framework;
    private String compatibleVersions;
    private String dataCollectionTypes;
    private String encryptionMethod;
    private String authenticationMethod;
    private String dataTransmissionProtocol;
    private String fileMd5;
    private String signatureMethod;
    private String certificateMd5;
    private String certificateDetails;

    private String netType;
    private String lanIp;
    private String wanIp;
    private Date updateTime;
    private Date insertTime;

    /**
     * 默认构造函数
     */
    public AssetProbeVo() {
    }

    /**
     * 根据实体构造视图对象
     *
     * @param probe 数据探针SDK资产实体
     */
    public AssetProbeVo(AssetProbe probe) {
        if (probe != null) {
            this.id = probe.getId();
            this.source = probe.getSource() != null ? probe.getSource().getDescription() : null;
            this.type = probe.getType() != null ? probe.getType().getDescription() : null;
            this.owner = probe.getOwner() != null ? probe.getOwner().getDescription() : null;
            this.status = probe.getStatus() != null ? probe.getStatus().getDescription() : null;

            // 标签处理
            if (probe.getLabel() != null && !probe.getLabel().isEmpty()) {
                this.label = String.join(",", probe.getLabel());
            }

            this.access = probe.getAccess();
            this.level = probe.getLevel() != null ? probe.getLevel().getDescription() : null;
            this.risk = probe.getRisk() != null ? probe.getRisk().getDescription() : null;
            this.riskInfo = probe.getRiskInfo();
            this.info = probe.getInfo();

            // 数据探针SDK特有字段
            this.probeName = probe.getProbeName();
            this.probeVersion = probe.getProbeVersion();
            this.probeType = probe.getProbeType();
            this.language = probe.getLanguage();
            this.framework = probe.getFramework();
            this.compatibleVersions = probe.getCompatibleVersions();

            // 数据采集类型处理
            if (probe.getDataCollectionTypes() != null && !probe.getDataCollectionTypes().isEmpty()) {
                this.dataCollectionTypes = String.join(",", probe.getDataCollectionTypes());
            }

            this.encryptionMethod = probe.getEncryptionMethod();
            this.authenticationMethod = probe.getAuthenticationMethod();
            this.dataTransmissionProtocol = probe.getDataTransmissionProtocol();

            this.fileMd5 = probe.getFileMd5();
            this.signatureMethod = probe.getSignatureMethod();
            this.certificateMd5 = probe.getCertificateMd5();
            this.certificateDetails = probe.getCertificateDetails();

            this.updateTime = probe.getUpdateTime();
            this.insertTime = probe.getInsertTime();
        }
    }
} 