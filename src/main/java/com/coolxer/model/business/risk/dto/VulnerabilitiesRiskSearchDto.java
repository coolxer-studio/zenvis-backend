package com.coolxer.model.business.risk.dto;

import com.coolxer.commons.enums.business.risk.RiskLevel;
import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 漏洞风险搜索数据传输对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class VulnerabilitiesRiskSearchDto extends SortPageDto {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 启动ID
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
     * 漏洞编号
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
     * 风险等级
     */
    private RiskLevel riskLevel;

    /**
     * CVSS评分最小值
     */
    private Double cvssScoreMin;

    /**
     * CVSS评分最大值
     */
    private Double cvssScoreMax;

    /**
     * 影响组件
     */
    private String affectedComponent;

    /**
     * 影响版本
     */
    private String affectedVersion;

    /**
     * 漏洞状态
     */
    private String vulnerabilityStatus;

    /**
     * 发现时间开始
     */
    private Date discoveryTimeStart;

    /**
     * 发现时间结束
     */
    private Date discoveryTimeEnd;

    /**
     * 修复时间开始
     */
    private Date fixTimeStart;

    /**
     * 修复时间结束
     */
    private Date fixTimeEnd;

    /**
     * 扫描工具
     */
    private String scanTool;

    /**
     * 是否已验证
     */
    private Boolean isVerified;

    /**
     * 验证时间开始
     */
    private Date verificationTimeStart;

    /**
     * 验证时间结束
     */
    private Date verificationTimeEnd;

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
     * 创建时间开始
     */
    private Date insertTimeStart;

    /**
     * 创建时间结束
     */
    private Date insertTimeEnd;

    /**
     * 更新时间开始
     */
    private Date updateTimeStart;

    /**
     * 更新时间结束
     */
    private Date updateTimeEnd;

} 