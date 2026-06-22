package com.coolxer.service.business.risk.impl;

import com.coolxer.dao.clickhouse.entity.risk.RiskEvent;
import com.coolxer.dao.clickhouse.repository.risk.RiskEventRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.risk.dto.RiskEventDto;
import com.coolxer.model.business.risk.dto.RiskEventSearchDto;
import com.coolxer.model.business.risk.vo.RiskEventVo;
import com.coolxer.service.business.risk.RiskEventService;
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
public class RiskEventServiceImpl implements RiskEventService {

    @Autowired
    private RiskEventRepository riskEventRepository;

    @Override
    public boolean add(RiskEventDto dto) {
        RiskEvent risk = new RiskEvent();
        BeanUtils.copyProperties(dto, risk);
        risk.setInsertTime(new Date());
        risk.setUpdateTime(new Date());
        riskEventRepository.save(risk);
        return true;
    }

    @Override
    public boolean delete(String id) {
        riskEventRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll(List<String> ids) {
        ids.forEach(this::delete);
        return true;
    }

    @Override
    public boolean update(String id, RiskEventDto dto) {
        Optional<RiskEvent> optional = riskEventRepository.findById(id);
        if (optional.isPresent()) {
            RiskEvent risk = optional.get();
            BeanUtils.copyProperties(dto, risk);
            risk.setUpdateTime(new Date());
            riskEventRepository.save(risk);
            return true;
        }
        return false;
    }

    @Override
    public RiskEventVo getOne(String id) {
        return riskEventRepository.findById(id).map(RiskEventVo::new).orElse(null);
    }

    @Override
    public PageRowsVo<RiskEventVo> getPageList(RiskEventSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage() - 1, searchDto.getPerPage());
        Page<RiskEvent> page;
        if (searchDto.getOrderBy() != null && searchDto.getOrderBy().equals("asc")) {
            page = riskEventRepository.findByPageAsc(pageable, searchDto.getId(), searchDto.getUserId(),
                    searchDto.getStartId(), searchDto.getAssetId(), searchDto.getType(),
                    searchDto.getRiskLevel(), searchDto.getNetType(), searchDto.getLanIp(), searchDto.getWanIp(),
                    null, null, null, null);
        } else {
            page = riskEventRepository.findByPageDesc(pageable, searchDto.getId(), searchDto.getUserId(),
                    searchDto.getStartId(), searchDto.getAssetId(), searchDto.getType(),
                    searchDto.getRiskLevel(), searchDto.getNetType(), searchDto.getLanIp(), searchDto.getWanIp(),
                    null, null, null, null);
        }
        List<RiskEventVo> list = page.getContent().stream().map(RiskEventVo::new).collect(Collectors.toList());
        return new PageRowsVo<>(list, page.getTotalElements());
    }

    @Override
    public List<String> getDistinctLabels() {
        return riskEventRepository.findDistinctLabels();
    }

    @Override
    public List<String> getSimilarIds(String term) {
        return riskEventRepository.findLikeIds(term);
    }

    @Override
    public List<String> getSimilarTypes(String term) {
        return riskEventRepository.findLikeTypes(term);
    }

    @Override
    public long countTotal() {
        return riskEventRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return riskEventRepository.countTodayIncrease();
    }

} 