package com.coolxer.dao.clickhouse.entity.asset;

import com.coolxer.commons.enums.business.asset.*;
import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import com.coolxer.dao.clickhouse.converter.StringListConverter;
import com.coolxer.model.business.asset.dto.AssetApiDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * RESTful API接口资产表实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_ASSET_API)
public class AssetApi {

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

    // API接口特有字段
    /**
     * API名称
     */
    @Column(name = "api_name")
    private String apiName;

    /**
     * API版本
     */
    @Column(name = "api_version")
    private String apiVersion;

    /**
     * API路径
     */
    @Column(name = "api_path")
    private String apiPath;

    /**
     * HTTP方法
     */
    @Column(name = "http_method")
    private String httpMethod;

    /**
     * 内容类型
     */
    @Column(name = "content_type")
    private String contentType;

    /**
     * 请求参数
     */
    @Column(name = "request_parameters")
    private String requestParameters;

    /**
     * 响应格式
     */
    @Column(name = "response_format")
    private String responseFormat;

    /**
     * 认证类型
     */
    @Column(name = "authentication_type")
    private String authenticationType;

    /**
     * 速率限制
     */
    @Column(name = "rate_limit")
    private Integer rateLimit;

    /**
     * 文档URL
     */
    @Column(name = "documentation_url")
    private String documentationUrl;

    /**
     * 所属服务ID
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 是否已弃用
     */
    @Column(name = "is_deprecated")
    private Boolean isDeprecated;

    /**
     * 从DTO转换为实体
     */
    public static AssetApi fromDto(AssetApiDto dto) {
        if (dto == null) {
            return null;
        }

        AssetApi entity = new AssetApi();
        entity.setId(dto.getId());
        entity.setSource(dto.getSource());
        entity.setType(dto.getType());
        entity.setOwner(dto.getOwner());
        entity.setStatus(dto.getStatus());
        entity.setAccess(dto.getAccess());
        entity.setLevel(dto.getLevel());
        entity.setRisk(dto.getRisk());
        entity.setRiskInfo(dto.getRiskInfo());
        entity.setInfo(dto.getInfo());

        // 处理标签
        if (dto.getLabel() != null && !dto.getLabel().isEmpty()) {
            entity.setLabel(Arrays.asList(dto.getLabel().split(",")));
        } else {
            entity.setLabel(new ArrayList<>());
        }

        // API接口特有字段
        entity.setApiName(dto.getApiName());
        entity.setApiVersion(dto.getApiVersion());
        entity.setApiPath(dto.getApiPath());
        entity.setHttpMethod(dto.getHttpMethod());
        entity.setContentType(dto.getContentType());
        entity.setRequestParameters(dto.getRequestParameters());
        entity.setResponseFormat(dto.getResponseFormat());
        entity.setAuthenticationType(dto.getAuthenticationType());
        entity.setRateLimit(dto.getRateLimit());
        entity.setDocumentationUrl(dto.getDocumentationUrl());
        entity.setServiceId(dto.getServiceId());
        entity.setIsDeprecated(dto.getIsDeprecated());

        entity.setUpdateTime(new Date());
        entity.setInsertTime(new Date());

        return entity;
    }
} 