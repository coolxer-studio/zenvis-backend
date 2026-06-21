package com.coolxer.model.business.asset.dto;

import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * APP应用程序资产搜索数据传输对象
 */
@Data
@NoArgsConstructor
public class AssetAppSearchDto extends SortPageDto {

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

    // APP应用程序特有搜索字段
    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用版本
     */
    private String appVersion;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 平台
     */
    private String platform;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 开发者
     */
    private String developer;

    /**
     * 发布时间范围开始
     */
    private Date publishTimeStart;

    /**
     * 发布时间范围结束
     */
    private Date publishTimeEnd;

    /**
     * 更新渠道
     */
    private String updateChannel;

    /**
     * 最低系统版本
     */
    private String minOsVersion;

    /**
     * 目标系统版本
     */
    private String targetOsVersion;

    /**
     * 活跃时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String insertTime;
} 