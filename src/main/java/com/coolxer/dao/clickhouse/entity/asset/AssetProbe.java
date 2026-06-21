package com.coolxer.dao.clickhouse.entity.asset;

import com.coolxer.commons.enums.business.asset.*;
import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import com.coolxer.dao.clickhouse.converter.StringListConverter;
import com.coolxer.model.business.asset.dto.AssetProbeDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 数据探针SDK资产表实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_ASSET_PROBE)
public class AssetProbe {

    /**
     * 资产ID，资产的唯一编号信息
     */
    @Id
    private String id;

    /**
     * 资产来源
     */
    @Enumerated(EnumType.STRING)
    private AssetSource source;

    /**
     * 资产类型
     */
    @Enumerated(EnumType.STRING)
    private AssetType type;

    /**
     * 资产属主（企业、终端客户）
     */
    @Enumerated(EnumType.STRING)
    private AssetOwner owner;

    /**
     * 资产状态，在网、停用、下线、删除
     */
    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    /**
     * 资产标签
     */
    @Convert(converter = StringListConverter.class)
    private List<String> label;

    /**
     * 资产是否开放
     */
    private Boolean access;

    /**
     * 资产重要等级
     */
    @Enumerated(EnumType.STRING)
    private AssetLevel level;

    /**
     * 风险等级
     */
    @Enumerated(EnumType.STRING)
    private AssetRiskLevel risk;

    /**
     * 风险信息，漏洞详情等，json格式
     */
    @Column(name = "risk_info")
    private String riskInfo;

    /**
     * 资产信息，不同的资产信息不一样，json格式
     */
    private String info;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "insert_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertTime;

    // 数据探针SDK特有字段
    /**
     * 探针名称
     */
    @Column(name = "probe_name")
    private String probeName;

    /**
     * 探针版本
     */
    @Column(name = "probe_version")
    private String probeVersion;

    /**
     * 探针类型
     */
    @Column(name = "probe_type")
    private String probeType;

    /**
     * 开发语言
     */
    private String language;

    /**
     * 框架
     */
    private String framework;

    /**
     * 兼容版本
     */
    @Column(name = "compatible_versions")
    private String compatibleVersions;

    /**
     * 数据采集类型
     */
    @Column(name = "data_collection_types")
    @Convert(converter = StringListConverter.class)
    private List<String> dataCollectionTypes;

    /**
     * 加密方式
     */
    @Column(name = "encryption_method")
    private String encryptionMethod;

    /**
     * 认证方式
     */
    @Column(name = "authentication_method")
    private String authenticationMethod;

    /**
     * 数据传输协议
     */
    @Column(name = "data_transmission_protocol")
    private String dataTransmissionProtocol;

    /**
     * 文件MD5值
     */
    @Column(name = "file_md5")
    private String fileMd5;

    /**
     * 签名方式
     */
    @Column(name = "signature_method")
    private String signatureMethod;

    /**
     * 证书MD5值
     */
    @Column(name = "certificate_md5")
    private String certificateMd5;

    /**
     * 证书详情，JSON格式
     */
    @Column(name = "certificate_details")
    private String certificateDetails;

    /**
     * 从DTO更新实体属性
     *
     * @param dto 数据探针SDK资产DTO
     */
    public void updateFromDto(AssetProbeDto dto) {
        if (dto == null) {
            return;
        }

        if (dto.getSource() != null) {
            this.source = dto.getSource();
        }
        if (dto.getType() != null) {
            this.type = dto.getType();
        }
        if (dto.getOwner() != null) {
            this.owner = dto.getOwner();
        }
        if (dto.getStatus() != null) {
            this.status = dto.getStatus();
        }

        // 处理标签
        if (dto.getLabel() != null && !dto.getLabel().isEmpty()) {
            this.label = Arrays.asList(dto.getLabel().split(","));
        }

        if (dto.getAccess() != null) {
            this.access = dto.getAccess();
        }
        if (dto.getLevel() != null) {
            this.level = dto.getLevel();
        }

        // 更新探针特有属性
        if (dto.getProbeName() != null) {
            this.probeName = dto.getProbeName();
        }
        if (dto.getProbeVersion() != null) {
            this.probeVersion = dto.getProbeVersion();
        }
        if (dto.getProbeType() != null) {
            this.probeType = dto.getProbeType();
        }
        if (dto.getLanguage() != null) {
            this.language = dto.getLanguage();
        }
        if (dto.getFramework() != null) {
            this.framework = dto.getFramework();
        }
        if (dto.getCompatibleVersions() != null) {
            this.compatibleVersions = dto.getCompatibleVersions();
        }
        if (dto.getDataCollectionTypes() != null && !dto.getDataCollectionTypes().isEmpty()) {
            this.dataCollectionTypes = Arrays.asList(dto.getDataCollectionTypes().split(","));
        }
        if (dto.getEncryptionMethod() != null) {
            this.encryptionMethod = dto.getEncryptionMethod();
        }
        if (dto.getAuthenticationMethod() != null) {
            this.authenticationMethod = dto.getAuthenticationMethod();
        }
        if (dto.getDataTransmissionProtocol() != null) {
            this.dataTransmissionProtocol = dto.getDataTransmissionProtocol();
        }
        if (dto.getFileMd5() != null) {
            this.fileMd5 = dto.getFileMd5();
        }
        if (dto.getSignatureMethod() != null) {
            this.signatureMethod = dto.getSignatureMethod();
        }
        if (dto.getCertificateMd5() != null) {
            this.certificateMd5 = dto.getCertificateMd5();
        }
        if (dto.getCertificateDetails() != null) {
            this.certificateDetails = dto.getCertificateDetails();
        }

        this.updateTime = new Date();
    }
} 