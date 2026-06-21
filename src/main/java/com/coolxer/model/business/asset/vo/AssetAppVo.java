package com.coolxer.model.business.asset.vo;

import com.coolxer.dao.clickhouse.entity.asset.AssetApp;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * APP应用程序资产视图对象
 */
@Data
public class AssetAppVo implements Serializable {

    private String id;
    private String source;
    private String type;
    private String owner;
    private String status;
    private String location;
    private String label;
    private Boolean access;
    private String level;
    private String risk;
    private String riskInfo;
    private String info;

    // APP应用程序特有属性
    private String appName;
    private String appVersion;
    private String appType;
    private String platform;
    private String packageName;
    private String developer;
    private Date publishTime;
    private String updateChannel;
    private String minOsVersion;
    private String targetOsVersion;
    private String permissions;
    private String dependencies;
    private String fileMd5;
    private String signatureMethod;
    private String certificateMd5;
    private String certificateDetails;
    private Date updateTime;
    private Date insertTime;

    /**
     * 从实体转换为视图对象
     */
    public static AssetAppVo fromEntity(AssetApp entity) {
        if (entity == null) {
            return null;
        }

        AssetAppVo vo = new AssetAppVo();
        vo.setId(entity.getId());
        vo.setSource(entity.getSource() != null ? entity.getSource().getDescription() : null);
        vo.setType(entity.getType() != null ? entity.getType().getDescription() : null);
        vo.setOwner(entity.getOwner() != null ? entity.getOwner().getDescription() : null);
        vo.setStatus(entity.getStatus() != null ? entity.getStatus().getDescription() : null);

        // 处理标签
        if (entity.getLabel() != null && !entity.getLabel().isEmpty()) {
            vo.setLabel(entity.getLabel().stream().collect(Collectors.joining(",")));
        }

        vo.setAccess(entity.getAccess());
        vo.setLevel(entity.getLevel() != null ? entity.getLevel().getDescription() : null);
        vo.setRisk(entity.getRisk() != null ? entity.getRisk().getDescription() : null);
        vo.setRiskInfo(entity.getRiskInfo());
        vo.setInfo(entity.getInfo());

        // APP应用程序特有属性
        vo.setAppName(entity.getAppName());
        vo.setAppVersion(entity.getAppVersion());
        vo.setAppType(entity.getAppType());
        vo.setPlatform(entity.getPlatform());
        vo.setPackageName(entity.getPackageName());
        vo.setDeveloper(entity.getDeveloper());
        vo.setPublishTime(entity.getPublishTime());
        vo.setUpdateChannel(entity.getUpdateChannel());
        vo.setMinOsVersion(entity.getMinOsVersion());
        vo.setTargetOsVersion(entity.getTargetOsVersion());

        // 处理权限
        if (entity.getPermissions() != null && !entity.getPermissions().isEmpty()) {
            vo.setPermissions(entity.getPermissions().stream().collect(Collectors.joining(",")));
        }

        // 处理依赖项
        if (entity.getDependencies() != null && !entity.getDependencies().isEmpty()) {
            vo.setDependencies(entity.getDependencies().stream().collect(Collectors.joining(",")));
        }

        vo.setFileMd5(entity.getFileMd5());
        vo.setSignatureMethod(entity.getSignatureMethod());
        vo.setCertificateMd5(entity.getCertificateMd5());
        vo.setCertificateDetails(entity.getCertificateDetails());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setInsertTime(entity.getInsertTime());

        return vo;
    }
} 