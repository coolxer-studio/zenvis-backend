package com.coolxer.model.business.asset.dto;

import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RESTful API接口资产搜索数据传输对象
 */
@Data
@NoArgsConstructor
public class AssetApiSearchDto extends SortPageDto {

    /**
     * 资产ID，资产的唯一编号信息
     */
    private String id;

    /**
     * 资产来源
     */
    private String source;

    /**
     * 资产类型
     */
    private String type;

    /**
     * 资产属主
     */
    private String owner;

    /**
     * 资产状态
     */
    private String status;

    /**
     * 资产标签，多个标签用逗号分隔
     */
    private String label;

    /**
     * 资产是否开放
     */
    private Boolean access;

    /**
     * 资产重要等级
     */
    private String level;

    /**
     * 风险等级
     */
    private String risk;

    // API接口特有搜索字段
    /**
     * API名称
     */
    private String apiName;

    /**
     * API版本
     */
    private String apiVersion;

    /**
     * API路径
     */
    private String apiPath;

    /**
     * HTTP方法
     */
    private String httpMethod;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 认证类型
     */
    private String authenticationType;

    /**
     * 所属服务ID
     */
    private String serviceId;

    /**
     * 是否已弃用
     */
    private Boolean isDeprecated;
    /**
     * 活跃时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String insertTime;
} 