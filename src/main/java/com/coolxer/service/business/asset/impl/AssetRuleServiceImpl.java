package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRuleStatus;
import com.coolxer.dao.mysql.entity.AssetRule;
import com.coolxer.dao.mysql.repository.AssetRuleRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetRuleDto;
import com.coolxer.model.business.asset.dto.AssetRuleSearchDto;
import com.coolxer.model.business.asset.vo.AssetRuleVo;
import com.coolxer.service.business.asset.AssetRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AssetRuleServiceImpl implements AssetRuleService {

    @Autowired
    private AssetRuleRepository assetRuleRepository;

    @Override
    @Transactional
    public Boolean add(AssetRuleDto assetRuleDto) {

        try {
            AssetRule assetRule = new AssetRule();
            // 设置基础属性
            assetRule.setStatus(AssetRuleStatus.INACTIVE.name());
            // 从DTO更新其他属性
            assetRule.updateFromDto(assetRuleDto);
            // 保存到数据库
            assetRuleRepository.save(assetRule);
            return true;
        } catch (Exception e) {
            log.error("添加资产规则失败", e);
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateRule(Long id, AssetRuleDto assetRuleDto) {
        try {
            Optional<AssetRule> optionalAssetRule = assetRuleRepository.findById(id);
            if (optionalAssetRule.isPresent()) {
                AssetRule assetRule = optionalAssetRule.get();
                assetRule.updateFromDto(assetRuleDto);
                assetRuleRepository.save(assetRule);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新资产规则失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    @Transactional
    public void deleteRule(Long id) {
        assetRuleRepository.deleteById(id);
    }

    @Override
    public AssetRuleVo getRule(Long id) {
        try {
            Optional<AssetRule> optionalAssetRule = assetRuleRepository.findById(id);
            return optionalAssetRule.map(AssetRuleVo::new).orElse(null);
        } catch (Exception e) {
            log.error("获取资产规则失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean activateRule(Long id) {
        return true;
    }

    @Override
    @Transactional
    public Boolean deactivateRule(Long id) {
        return true;
    }

    @Override
    @Transactional
    public void deleteRules(List<Long> ids) {
        assetRuleRepository.deleteAllById(ids);
    }

    @Override
    public PageRowsVo<AssetRuleVo> getPageList(AssetRuleSearchDto assetRuleSearchDto) {
        try {
            Pageable pageable = PageRequest.of(assetRuleSearchDto.getPage() - 1, assetRuleSearchDto.getPerPage());
            Page<AssetRule> byPage;
            // 根据排序方向选择查询方法
            byPage = assetRuleRepository.findByPage(
                    pageable,
                    assetRuleSearchDto.getAsset() == null ? null : assetRuleSearchDto.getAsset().name(),
                    assetRuleSearchDto.getAction() == null ? null : assetRuleSearchDto.getAction().name(),
                    assetRuleSearchDto.getStatus() == null ? null : assetRuleSearchDto.getStatus().name()
            );

            return new PageRowsVo<>(
                    byPage.getContent().stream().map(AssetRuleVo::new).toList(),
                    byPage.getTotalElements()
            );
        } catch (Exception e) {
            log.error("分页查询资产规则失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0L);
        }
    }


} 