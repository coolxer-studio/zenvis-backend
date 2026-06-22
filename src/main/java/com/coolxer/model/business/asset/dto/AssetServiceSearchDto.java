package com.coolxer.model.business.asset.dto;

import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统服务资产搜索数据传输对象
 */
@Data
@NoArgsConstructor
public class AssetServiceSearchDto extends SortPageDto {

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

    // 系统服务特有搜索字段
    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务版本
     */
    private String serviceVersion;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 运行环境
     */
    private String runtimeEnvironment;

    /**
     * 部署类型
     */
    private String deploymentType;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 进程名称
     */
    private String processName;

    /**
     * 活跃时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String insertTime;
} 