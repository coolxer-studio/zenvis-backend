package com.coolxer.model.business.risk.dto;

import com.coolxer.commons.enums.business.risk.RiskLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 漏洞风险数据传输对象
 */
@Data
@NoArgsConstructor
public class VulnerabilitiesRiskDto {

    /**
     * 漏洞风险唯一标识
     */
    private String id;

    /**
     * 用户ID，用户的唯一标识
     */
    private String userId;

    /**
     * 启动ID，每次启动的唯一标识
     */
    private String startId;

    /**
     * 资产ID
     */
    private String assetId;

    /**
     * 漏洞名称
     */
    private String vulnerabilityName;

    /**
     * 漏洞编号（CVE、CNVD等）
     */
    private String vulnerabilityId;

    /**
     * 漏洞类型
     */
    private String vulnerabilityType;

    /**
     * 漏洞分类
     */
    private String vulnerabilityCategory;

    /**
     * 漏洞描述
     */
    private String vulnerabilityDescription;

    /**
     * 风险等级
     */
    private RiskLevel riskLevel;

    /**
     * CVSS评分
     */
    private Double cvssScore;

    /**
     * CVSS向量
     */
    private String cvssVector;

    /**
     * 影响范围
     */
    private String affectedScope;

    /**
     * 影响组件
     */
    private String affectedComponent;

    /**
     * 影响版本
     */
    private String affectedVersion;

    /**
     * 修复版本
     */
    private String fixedVersion;

    /**
     * 修复建议
     */
    private String fixSuggestion;

    /**
     * 漏洞状态（未修复、已修复、忽略等）
     */
    private String vulnerabilityStatus;

    /**
     * 发现时间
     */
    private Date discoveryTime;

    /**
     * 修复时间
     */
    private Date fixTime;

    /**
     * 扫描工具
     */
    private String scanTool;

    /**
     * 扫描结果详情（JSON格式）
     */
    private String scanDetails;

    /**
     * 验证方法
     */
    private String verificationMethod;

    /**
     * 是否已验证
     */
    private Boolean isVerified;

    /**
     * 验证时间
     */
    private Date verificationTime;

    /**
     * 验证结果
     */
    private String verificationResult;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 网络类型
     */
    private String netType;

    /**
     * 局域网IP
     */
    private String lanIp;

    /**
     * 因特网IP
     */
    private String wanIp;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date insertTime;

} 