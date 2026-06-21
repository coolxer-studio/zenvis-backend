package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetRuleDto;
import com.coolxer.model.business.asset.dto.AssetRuleSearchDto;
import com.coolxer.model.business.asset.vo.AssetRuleVo;

import java.util.List;

public interface AssetRuleService {

    /**
     * 创建资产规则
     */
    Boolean add(AssetRuleDto assetRuleDto);

    /**
     * 更新资产规则
     */
    Boolean updateRule(Long id, AssetRuleDto assetRuleDto);

    /**
     * 删除资产规则
     */
    void deleteRule(Long id);

    /**
     * 批量删除资产规则
     */
    void deleteRules(List<Long> ids);

    /**
     * 获取资产规则详情
     */
    AssetRuleVo getRule(Long id);

    /**
     * 激活规则
     */
    Boolean activateRule(Long id);

    /**
     * 停用规则
     */
    Boolean deactivateRule(Long id);

    PageRowsVo<AssetRuleVo> getPageList(AssetRuleSearchDto assetRuleSearchDto);

}