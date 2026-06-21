package com.coolxer.model.business.asset.vo;

import com.coolxer.dao.clickhouse.entity.asset.AssetApi;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * RESTful API接口资产视图对象
 */
@Data
public class AssetApiVo implements Serializable {

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

    // API接口特有属性
    private String apiName;
    private String apiVersion;
    private String apiPath;
    private String httpMethod;
    private String contentType;
    private String requestParameters;
    private String responseFormat;
    private String authenticationType;
    private Integer rateLimit;
    private String documentationUrl;
    private String serviceId;
    private Boolean isDeprecated;
    private Date updateTime;
    private Date insertTime;

    /**
     * 从实体转换为视图对象
     */
    public static AssetApiVo fromEntity(AssetApi entity) {
        if (entity == null) {
            return null;
        }

        AssetApiVo vo = new AssetApiVo();
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

        // API接口特有属性
        vo.setApiName(entity.getApiName());
        vo.setApiVersion(entity.getApiVersion());
        vo.setApiPath(entity.getApiPath());
        vo.setHttpMethod(entity.getHttpMethod());
        vo.setContentType(entity.getContentType());
        vo.setRequestParameters(entity.getRequestParameters());
        vo.setResponseFormat(entity.getResponseFormat());
        vo.setAuthenticationType(entity.getAuthenticationType());
        vo.setRateLimit(entity.getRateLimit());
        vo.setDocumentationUrl(entity.getDocumentationUrl());
        vo.setServiceId(entity.getServiceId());
        vo.setIsDeprecated(entity.getIsDeprecated());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setInsertTime(entity.getInsertTime());

        return vo;
    }
}