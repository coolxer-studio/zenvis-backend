package com.coolxer.service.business.risk.impl;

import com.coolxer.dao.clickhouse.entity.risk.AttackRisk;
import com.coolxer.dao.clickhouse.repository.risk.AttackRiskRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.risk.dto.AttackRiskDto;
import com.coolxer.model.business.risk.dto.AttackRiskSearchDto;
import com.coolxer.model.business.risk.vo.AttackRiskVo;
import com.coolxer.service.business.risk.AttackRiskService;
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
public class AttackRiskServiceImpl implements AttackRiskService {

    @Autowired
    private AttackRiskRepository attackRiskRepository;

    @Override
    public boolean add(AttackRiskDto dto) {
        AttackRisk risk = new AttackRisk();
        BeanUtils.copyProperties(dto, risk);
        risk.setInsertTime(new Date());
        risk.setUpdateTime(new Date());
        attackRiskRepository.save(risk);
        return true;
    }

    @Override
    public boolean delete(String id) {
        attackRiskRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll(List<String> ids) {
        ids.forEach(this::delete);
        return true;
    }

    @Override
    public boolean update(String id, AttackRiskDto dto) {
        Optional<AttackRisk> optional = attackRiskRepository.findById(id);
        if (optional.isPresent()) {
            AttackRisk risk = optional.get();
            BeanUtils.copyProperties(dto, risk);
            risk.setUpdateTime(new Date());
            attackRiskRepository.save(risk);
            return true;
        }
        return false;
    }

    @Override
    public AttackRiskVo getOne(String id) {
        return attackRiskRepository.findById(id).map(AttackRiskVo::new).orElse(null);
    }

    @Override
    public PageRowsVo<AttackRiskVo> getPageList(AttackRiskSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage() - 1, searchDto.getPerPage());
        Page<AttackRisk> page;
        if (searchDto.getOrderBy() != null && searchDto.getOrderBy().equals("ascend")) {
            page = attackRiskRepository.findByPageAsc(pageable, searchDto.getId(), searchDto.getUserId(),
                    searchDto.getStartId(), searchDto.getAssetId(), searchDto.getType(),
                    searchDto.getRiskLevel(), searchDto.getNetType(), searchDto.getLanIp(), searchDto.getWanIp(),
                    null, null, null, null);
        } else {
            page = attackRiskRepository.findByPageDesc(pageable, searchDto.getId(), searchDto.getUserId(),
                    searchDto.getStartId(), searchDto.getAssetId(), searchDto.getType(),
                    searchDto.getRiskLevel(), searchDto.getNetType(), searchDto.getLanIp(), searchDto.getWanIp(),
                    null, null, null, null);
        }
        List<AttackRiskVo> list = page.getContent().stream().map(AttackRiskVo::new).collect(Collectors.toList());
        return new PageRowsVo<>(list, page.getTotalElements());
    }

    @Override
    public List<String> getDistinctLabels() {
        return attackRiskRepository.findDistinctLabels();
    }

    @Override
    public List<String> getSimilarIds(String term) {
        return attackRiskRepository.findLikeIds(term);
    }

    @Override
    public List<String> getSimilarTypes(String term) {
        return null;
    }


    @Override
    public long countTotal() {
        return attackRiskRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return attackRiskRepository.countTodayIncrease();
    }

} 