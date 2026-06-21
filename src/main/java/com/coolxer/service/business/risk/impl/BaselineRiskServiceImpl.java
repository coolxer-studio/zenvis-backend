package com.coolxer.service.business.risk.impl;

import com.coolxer.dao.clickhouse.entity.risk.BaselineRisk;
import com.coolxer.dao.clickhouse.repository.risk.BaselineRiskRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.risk.dto.BaselineRiskDto;
import com.coolxer.model.business.risk.dto.BaselineRiskSearchDto;
import com.coolxer.model.business.risk.vo.BaselineRiskVo;
import com.coolxer.service.business.risk.BaselineRiskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BaselineRiskServiceImpl implements BaselineRiskService {

    @Autowired
    private BaselineRiskRepository baselineRiskRepository;

    @Override
    public boolean add(BaselineRiskDto dto) {
        BaselineRisk risk = new BaselineRisk();
        BeanUtils.copyProperties(dto, risk);
        risk.setInsertTime(new Date());
        risk.setUpdateTime(new Date());
        baselineRiskRepository.save(risk);
        return true;
    }

    @Override
    public boolean delete(String id) {
        baselineRiskRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll(List<String> ids) {
        ids.forEach(this::delete);
        return true;
    }

    @Override
    public boolean update(String id, BaselineRiskDto dto) {
        Optional<BaselineRisk> optional = baselineRiskRepository.findById(id);
        if (optional.isPresent()) {
            BaselineRisk risk = optional.get();
            BeanUtils.copyProperties(dto, risk);
            risk.setUpdateTime(new Date());
            baselineRiskRepository.save(risk);
            return true;
        }
        return false;
    }

    @Override
    public BaselineRiskVo getOne(String id) {
        return baselineRiskRepository.findById(id).map(BaselineRiskVo::new).orElse(null);
    }

    @Override
    public PageRowsVo<BaselineRiskVo> getPageList(BaselineRiskSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage() - 1, searchDto.getPerPage());
        Page<BaselineRisk> page;
        if (searchDto.getOrderBy() != null && searchDto.getOrderBy().equals("ascend")) {
            page = baselineRiskRepository.findByPageAsc(pageable, searchDto.getId(), searchDto.getUserId(),
                    searchDto.getStartId(), searchDto.getAssetId(), searchDto.getConfigurationName(),
                    searchDto.getRiskLevel(), searchDto.getNetType(), searchDto.getLanIp(), searchDto.getWanIp(),
                    null, null, null, null);
        } else {
            page = baselineRiskRepository.findByPageDesc(pageable, searchDto.getId(), searchDto.getUserId(),
                    searchDto.getStartId(), searchDto.getAssetId(), searchDto.getConfigurationName(),
                    searchDto.getRiskLevel(), searchDto.getNetType(), searchDto.getLanIp(), searchDto.getWanIp(),
                    null, null, null, null);
        }
        List<BaselineRiskVo> list = page.getContent().stream().map(BaselineRiskVo::new).collect(Collectors.toList());
        return new PageRowsVo<>(list, page.getTotalElements());
    }

    @Override
    public List<String> getDistinctLabels() {
        return baselineRiskRepository.findDistinctLabels();
    }

    @Override
    public List<String> getSimilarIds(String term) {
        return baselineRiskRepository.findLikeIds(term);
    }

    @Override
    public List<String> getSimilarConfigurationNames(String term) {
        return baselineRiskRepository.findLikeConfigurationNames(term);
    }

    @Override
    public long countTotal() {
        return baselineRiskRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return baselineRiskRepository.countTodayIncrease();
    }

} 