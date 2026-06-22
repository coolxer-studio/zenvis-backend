package com.coolxer.dao.clickhouse.entity.asset;

import com.coolxer.commons.enums.business.asset.*;
import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import com.coolxer.dao.clickhouse.converter.StringListConverter;
import com.coolxer.model.business.asset.dto.AssetServiceDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 系统服务资产表实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_ASSET_SERVICE)
public class AssetService {

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

    // 系统服务特有字段
    /**
     * 服务名称
     */
    @Column(name = "service_name")
    private String serviceName;

    /**
     * 服务版本
     */
    @Column(name = "service_version")
    private String serviceVersion;

    /**
     * 服务类型
     */
    @Column(name = "service_type")
    private String serviceType;

    /**
     * 运行环境
     */
    @Column(name = "runtime_environment")
    private String runtimeEnvironment;

    /**
     * 部署类型
     */
    @Column(name = "deployment_type")
    private String deploymentType;

    /**
     * 启动命令
     */
    @Column(name = "start_command")
    private String startCommand;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 进程名称
     */
    @Column(name = "process_name")
    private String processName;

    /**
     * 进程ID
     */
    @Column(name = "process_id")
    private String processId;

    /**
     * 启动参数
     */
    @Column(name = "startup_parameters")
    private String startupParameters;

    /**
     * 依赖项
     */
    @Convert(converter = StringListConverter.class)
    private List<String> dependencies;

    /**
     * 资源使用情况
     */
    @Column(name = "resource_usage")
    private String resourceUsage;

    /**
     * 从DTO转换为实体
     */
    public static AssetService fromDto(AssetServiceDto dto) {
        if (dto == null) {
            return null;
        }

        AssetService entity = new AssetService();
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

        // 处理依赖项
        if (dto.getDependencies() != null && !dto.getDependencies().isEmpty()) {
            entity.setDependencies(Arrays.asList(dto.getDependencies().split(",")));
        } else {
            entity.setDependencies(new ArrayList<>());
        }

        // 系统服务特有字段
        entity.setServiceName(dto.getServiceName());
        entity.setServiceVersion(dto.getServiceVersion());
        entity.setServiceType(dto.getServiceType());
        entity.setRuntimeEnvironment(dto.getRuntimeEnvironment());
        entity.setDeploymentType(dto.getDeploymentType());
        entity.setStartCommand(dto.getStartCommand());
        entity.setPort(dto.getPort());
        entity.setProcessName(dto.getProcessName());
        entity.setProcessId(dto.getProcessId());
        entity.setStartupParameters(dto.getStartupParameters());
        entity.setResourceUsage(dto.getResourceUsage());

        entity.setUpdateTime(new Date());
        entity.setInsertTime(new Date());

        return entity;
    }
} 