package com.coolxer.model.business.asset.dto;

import com.coolxer.commons.enums.business.asset.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统服务资产数据传输对象
 */
@Data
@NoArgsConstructor
public class AssetServiceDto {

    /**
     * 资产ID，资产的唯一编号信息
     */
    private String id;

    /**
     * 资产来源
     */
    private AssetSource source;

    /**
     * 资产类型
     */
    private AssetType type;

    /**
     * 资产属主（企业、终端客户）
     */
    private AssetOwner owner;

    /**
     * 资产状态，在网、停用、 下线、删除
     */
    private AssetStatus status;

    /**
     * 行政区域编码
     */
    private String areaCode;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

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
    private AssetLevel level;

    /**
     * 风险等级
     *
     * @see AssetRiskLevel
     */
    private AssetRiskLevel risk;

    /**
     * 风险信息，漏洞详情等，json格式
     */
    private String riskInfo;

    /**
     * 资产信息，不同的资产信息不一样，json格式
     */
    private String info;

    /**
     * 城市信息
     */
    private CityInfo cityInfo;

    // 系统服务特有字段
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
     * 启动命令
     */
    private String startCommand;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 进程名称
     */
    private String processName;

    /**
     * 进程ID
     */
    private String processId;

    /**
     * 启动参数
     */
    private String startupParameters;

    /**
     * 依赖项，多个依赖用逗号分隔
     */
    private String dependencies;

    /**
     * 资源使用情况
     */
    private String resourceUsage;
} 