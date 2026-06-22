package com.coolxer.service.business.risk.impl;

import com.coolxer.dao.clickhouse.entity.risk.WeakRisk;
import com.coolxer.dao.clickhouse.repository.risk.WeakRiskRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.risk.dto.WeakRiskDto;
import com.coolxer.model.business.risk.dto.WeakRiskSearchDto;
import com.coolxer.model.business.risk.vo.WeakRiskVo;
import com.coolxer.service.business.risk.WeakRiskService;
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
public class WeakRiskServiceImpl implements WeakRiskService {

    @Autowired
    private WeakRiskRepository weakRiskRepository;

    @Override
    public boolean add(WeakRiskDto dto) {
        WeakRisk risk = new WeakRisk();
        BeanUtils.copyProperties(dto, risk);
        risk.setInsertTime(new Date());
        risk.setUpdateTime(new Date());
        weakRiskRepository.save(risk);
        return true;
    }

    @Override
    public boolean delete(String id) {
        weakRiskRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll(List<String> ids) {
        ids.forEach(this::delete);
        return true;
    }

    @Override
    public boolean update(String id, WeakRiskDto dto) {
        Optional<WeakRisk> optional = weakRiskRepository.findById(id);
        if (optional.isPresent()) {
            WeakRisk risk = optional.get();
            BeanUtils.copyProperties(dto, risk);
            risk.setUpdateTime(new Date());
            weakRiskRepository.save(risk);
            return true;
        }
        return false;
    }

    @Override
    public WeakRiskVo getOne(String id) {
        return weakRiskRepository.findById(id).map(WeakRiskVo::new).orElse(null);
    }

    @Override
    public PageRowsVo<WeakRiskVo> getPageList(WeakRiskSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage() - 1, searchDto.getPerPage());
        Page<WeakRisk> page;
        if (searchDto.getOrderBy() != null && searchDto.getOrderBy().equals("ascend")) {
            page = weakRiskRepository.findByPageAsc(pageable, searchDto.getId(), searchDto.getUserId(),
                    searchDto.getStartId(), searchDto.getAssetId(), searchDto.getUsername(),
                    searchDto.getPasswordType(), searchDto.getRiskLevel(), searchDto.getNetType(),
                    searchDto.getLanIp(), searchDto.getWanIp(),
                    null, null, null, null);
        } else {
            page = weakRiskRepository.findByPageDesc(pageable, searchDto.getId(), searchDto.getUserId(),
                    searchDto.getStartId(), searchDto.getAssetId(), searchDto.getUsername(),
                    searchDto.getPasswordType(), searchDto.getRiskLevel(), searchDto.getNetType(),
                    searchDto.getLanIp(), searchDto.getWanIp(),
                    null, null, null, null);
        }
        List<WeakRiskVo> list = page.getContent().stream().map(WeakRiskVo::new).collect(Collectors.toList());
        return new PageRowsVo<>(list, page.getTotalElements());
    }

    @Override
    public List<String> getDistinctLabels() {
        return weakRiskRepository.findDistinctLabels();
    }

    @Override
    public List<String> getSimilarIds(String term) {
        return weakRiskRepository.findLikeIds(term);
    }

    @Override
    public List<String> getSimilarUsernames(String term) {
        return weakRiskRepository.findLikeUsernames(term);
    }

    @Override
    public List<String> getSimilarPasswordTypes(String term) {
        return weakRiskRepository.findLikePasswordTypes(term);
    }

    @Override
    public long countTotal() {
        return weakRiskRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return weakRiskRepository.countTodayIncrease();
    }

} 