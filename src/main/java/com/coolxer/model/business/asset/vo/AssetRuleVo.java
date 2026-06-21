package com.coolxer.model.business.asset.vo;

import com.coolxer.commons.enums.business.asset.Asset;
import com.coolxer.commons.enums.business.asset.AssetRuleAction;
import com.coolxer.commons.enums.business.asset.AssetRuleStatus;
import com.coolxer.dao.mysql.entity.AssetRule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;

/**
 * 资产规则VO
 */
@Data
public class AssetRuleVo implements Serializable {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 规则ID
     */
    private Long id;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 规则描述
     */
    private String description;

    /**
     * 资产类型
     */
    private Asset asset;

    /**
     * 资产类型描述
     */
    private String assetDesc;

    /**
     * 判断条件，json对象
     */
    private Object conditions;

    /**
     * 执行动作
     */
    private AssetRuleAction action;

    /**
     * 执行动作描述
     */
    private String actionDesc;

    /**
     * 状态
     */
    private AssetRuleStatus status;

    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     * 执行结果
     */
    private String result;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    public AssetRuleVo(AssetRule assetRule) {
        if (assetRule == null) {
            return;
        }
        this.id = assetRule.getId() != null ? assetRule.getId().longValue() : null;
        this.name = assetRule.getName() != null ? assetRule.getName() : null;
        this.description = assetRule.getDescription() != null ? assetRule.getDescription() : null;
        this.asset = assetRule.getAsset() != null ? Asset.valueOf(assetRule.getAsset()) : null;
        this.assetDesc = this.asset != null ? this.asset.getDescription() : null;

        // 将 JSON 字符串转换为对象
        try {
            this.conditions = assetRule.getConditions() != null ?
                    objectMapper.readValue(assetRule.getConditions(), Object.class) : null;
        } catch (JsonProcessingException e) {
            this.conditions = assetRule.getConditions();
        }
        this.action = assetRule.getAction() != null ? AssetRuleAction.valueOf(assetRule.getAction()) : null;
        this.actionDesc = this.action != null ? this.action.getDescription() : null;
        this.status = assetRule.getStatus() != null ? AssetRuleStatus.valueOf(assetRule.getStatus()) : null;
        this.statusDesc = this.status != null ? this.status.getDescription() : null;
        this.result = assetRule.getResult();
        this.createTime = assetRule.getCreateTime() != null ? assetRule.getCreateTime().toString() : null;
        this.updateTime = assetRule.getUpdateTime() != null ? assetRule.getUpdateTime().toString() : null;
    }
} 