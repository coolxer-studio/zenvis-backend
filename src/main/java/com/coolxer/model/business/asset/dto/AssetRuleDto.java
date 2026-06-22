package com.coolxer.model.business.asset.dto;

import com.coolxer.commons.enums.business.asset.Asset;
import com.coolxer.commons.enums.business.asset.AssetRuleAction;
import com.coolxer.commons.enums.business.asset.AssetRuleStatus;
import com.coolxer.configuration.JsonToStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 资产规则DTO
 */
@Data
public class AssetRuleDto {

    /**
     * 规则ID
     */
    private Long id;

    /**
     * 规则名称
     */
    @NotBlank(message = "规则名称不能为空")
    private String name;

    /**
     * 规则描述
     */
    private String description;

    /**
     * 资产类型
     *
     * @see Asset
     */
    private Asset asset;

    /**
     * 判断条件，json格式
     */
    @JsonDeserialize(using = JsonToStringDeserializer.class)
    private String conditions;

    /**
     * 执行动作
     *
     * @see AssetRuleAction
     */
    private AssetRuleAction action;

    /**
     * 状态
     *
     * @see AssetRuleStatus
     */
    private AssetRuleStatus status;

    /**
     * 执行结果
     */
    private String result;

} 