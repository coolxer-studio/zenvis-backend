package com.coolxer.dao.clickhouse.entity.asset;

import com.coolxer.commons.enums.business.asset.*;
import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import com.coolxer.dao.clickhouse.converter.StringListConverter;
import com.coolxer.model.business.asset.dto.AssetAppDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * APP应用程序资产表实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_ASSET_APP)
public class AssetApp {

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

    // APP应用程序特有字段
    /**
     * 应用名称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 应用版本
     */
    @Column(name = "app_version")
    private String appVersion;

    /**
     * 应用类型
     */
    @Column(name = "app_type")
    private String appType;

    /**
     * 平台
     */
    private String platform;

    /**
     * 包名
     */
    @Column(name = "package_name")
    private String packageName;

    /**
     * 开发者
     */
    private String developer;

    /**
     * 发布时间
     */
    @Column(name = "publish_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime;

    /**
     * 更新渠道
     */
    @Column(name = "update_channel")
    private String updateChannel;

    /**
     * 最低系统版本
     */
    @Column(name = "min_os_version")
    private String minOsVersion;

    /**
     * 目标系统版本
     */
    @Column(name = "target_os_version")
    private String targetOsVersion;

    /**
     * 权限列表
     */
    @Convert(converter = StringListConverter.class)
    private List<String> permissions;

    /**
     * 依赖列表
     */
    @Convert(converter = StringListConverter.class)
    private List<String> dependencies;

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
     * @param dto APP应用程序资产DTO
     */
    public void updateFromDto(AssetAppDto dto) {
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

        // 更新APP应用程序特有属性
        if (dto.getAppName() != null) {
            this.appName = dto.getAppName();
        }
        if (dto.getAppVersion() != null) {
            this.appVersion = dto.getAppVersion();
        }
        if (dto.getAppType() != null) {
            this.appType = dto.getAppType();
        }
        if (dto.getPlatform() != null) {
            this.platform = dto.getPlatform();
        }
        if (dto.getPackageName() != null) {
            this.packageName = dto.getPackageName();
        }
        if (dto.getDeveloper() != null) {
            this.developer = dto.getDeveloper();
        }
        if (dto.getPublishTime() != null) {
            this.publishTime = dto.getPublishTime();
        }
        if (dto.getUpdateChannel() != null) {
            this.updateChannel = dto.getUpdateChannel();
        }
        if (dto.getMinOsVersion() != null) {
            this.minOsVersion = dto.getMinOsVersion();
        }
        if (dto.getTargetOsVersion() != null) {
            this.targetOsVersion = dto.getTargetOsVersion();
        }
        if (dto.getPermissions() != null && !dto.getPermissions().isEmpty()) {
            this.permissions = Arrays.asList(dto.getPermissions().split(","));
        }
        if (dto.getDependencies() != null && !dto.getDependencies().isEmpty()) {
            this.dependencies = Arrays.asList(dto.getDependencies().split(","));
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