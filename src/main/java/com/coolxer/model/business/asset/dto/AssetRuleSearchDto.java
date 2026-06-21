package com.coolxer.model.business.asset.dto;

import com.coolxer.commons.enums.business.asset.Asset;
import com.coolxer.commons.enums.business.asset.AssetRuleAction;
import com.coolxer.commons.enums.business.asset.AssetRuleStatus;
import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资产规则传输对象
 */
@Data
@NoArgsConstructor
public class AssetRuleSearchDto extends SortPageDto {

    /**
     * 资产类型
     *
     * @see Asset
     */
    private Asset asset;

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
}
