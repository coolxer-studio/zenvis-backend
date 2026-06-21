package com.coolxer.service.business.operation.impl;

import com.coolxer.dao.clickhouse.entity.operation.CrashEvent;
import com.coolxer.dao.clickhouse.repository.operation.CrashEventRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.CrashEventDto;
import com.coolxer.model.business.operation.vo.CrashEventVo;
import com.coolxer.service.business.operation.CrashEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CrashEventServiceImpl implements CrashEventService {
    @Autowired
    private CrashEventRepository crashEventRepository;

    private CrashEvent toEntity(CrashEventDto dto) {
        CrashEvent entity = new CrashEvent();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private CrashEventVo toVo(CrashEvent entity) {
        CrashEventVo vo = new CrashEventVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setLocation(String.join(",",
                entity.getCountry() != null ? entity.getCountry() : "",
                entity.getProvince() != null ? entity.getProvince() : "",
                entity.getCity() != null ? entity.getCity() : "",
                entity.getCounty() != null ? entity.getCounty() : ""
        ));
        return vo;
    }

    @Override
    public boolean add(CrashEventDto dto) {
        try {
            CrashEvent entity = toEntity(dto);
            if (entity.getId() == null || entity.getId().isEmpty()) {
                entity.setId(UUID.randomUUID().toString());
            }
            entity.setUpdateTime(new Date());
            entity.setInsertTime(new Date());
            crashEventRepository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            crashEventRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAll(List<String> ids) {
        try {
            crashEventRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(String id, CrashEventDto dto) {
        try {
            Optional<CrashEvent> optional = crashEventRepository.findById(id);
            if (optional.isPresent()) {
                CrashEvent entity = optional.get();
                BeanUtils.copyProperties(dto, entity);
                crashEventRepository.save(entity);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CrashEventVo getOne(String id) {
        return crashEventRepository.findById(id).map(this::toVo).orElse(null);
    }

    @Override
    public PageRowsVo<CrashEventVo> getPageList(CrashEventDto searchCondition) {
        Pageable pageable = PageRequest.of(searchCondition.getPage() - 1, searchCondition.getPerPage());
        Page<CrashEvent> result = "desc".equalsIgnoreCase(searchCondition.getOrderBy())
                ? crashEventRepository.findByPageDesc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null)
                : crashEventRepository.findByPageAsc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null);
        List<CrashEventVo> voList = new ArrayList<>();
        for (CrashEvent entity : result.getContent()) {
            voList.add(toVo(entity));
        }
        return new PageRowsVo<>(voList, result.getTotalElements());
    }

    @Override
    public long countTotal() {
        return crashEventRepository.count();
    }

    @Override
    public long countIncrease() {
        return crashEventRepository.countTodayIncrease();
    }

    @Override
    public List<String> getSimilarIds(String term) {
        return crashEventRepository.findLikeIds(term);
    }

    @Override
    public List<String> getSimilarMessages(String term) {
        return crashEventRepository.findLikeMessages(term);
    }
}