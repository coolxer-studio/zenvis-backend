package com.coolxer.model.business.asset.vo;

import com.coolxer.dao.clickhouse.entity.asset.AssetService;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 系统服务资产视图对象
 */
@Data
public class AssetServiceVo implements Serializable {

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

    // 系统服务特有属性
    private String serviceName;
    private String serviceVersion;
    private String serviceType;
    private String runtimeEnvironment;
    private String deploymentType;
    private String startCommand;
    private Integer port;
    private String processName;
    private String processId;
    private String startupParameters;
    private String dependencies;
    private String resourceUsage;
    private Date updateTime;
    private Date insertTime;

    /**
     * 从实体转换为视图对象
     */
    public static AssetServiceVo fromEntity(AssetService entity) {
        if (entity == null) {
            return null;
        }

        AssetServiceVo vo = new AssetServiceVo();
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

        // 系统服务特有属性
        vo.setServiceName(entity.getServiceName());
        vo.setServiceVersion(entity.getServiceVersion());
        vo.setServiceType(entity.getServiceType());
        vo.setRuntimeEnvironment(entity.getRuntimeEnvironment());
        vo.setDeploymentType(entity.getDeploymentType());
        vo.setStartCommand(entity.getStartCommand());
        vo.setPort(entity.getPort());
        vo.setProcessName(entity.getProcessName());
        vo.setProcessId(entity.getProcessId());
        vo.setStartupParameters(entity.getStartupParameters());

        // 处理依赖项
        if (entity.getDependencies() != null && !entity.getDependencies().isEmpty()) {
            vo.setDependencies(entity.getDependencies().stream().collect(Collectors.joining(",")));
        }

        vo.setResourceUsage(entity.getResourceUsage());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setInsertTime(entity.getInsertTime());

        return vo;
    }
} 