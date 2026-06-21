package com.coolxer.service.business.operation.impl;

import com.coolxer.dao.clickhouse.entity.operation.PerformanceEvent;
import com.coolxer.dao.clickhouse.repository.operation.PerformanceEventRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.PerformanceEventDto;
import com.coolxer.model.business.operation.vo.PerformanceEventVo;
import com.coolxer.service.business.operation.PerformanceEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PerformanceEventServiceImpl implements PerformanceEventService {
    @Autowired
    private PerformanceEventRepository repository;

    private PerformanceEvent toEntity(PerformanceEventDto dto) {
        PerformanceEvent entity = new PerformanceEvent();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private PerformanceEventVo toVo(PerformanceEvent entity) {
        PerformanceEventVo vo = new PerformanceEventVo();
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
    public boolean add(PerformanceEventDto dto) {
        try {
            PerformanceEvent entity = toEntity(dto);
            if (entity.getId() == null || entity.getId().isEmpty()) {
                entity.setId(UUID.randomUUID().toString());
            }
            entity.setUpdateTime(new Date());
            entity.setInsertTime(new Date());
            repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAll(List<String> ids) {
        try {
            repository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(String id, PerformanceEventDto dto) {
        try {
            Optional<PerformanceEvent> optional = repository.findById(id);
            if (optional.isPresent()) {
                PerformanceEvent entity = optional.get();
                BeanUtils.copyProperties(dto, entity);
                repository.save(entity);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public PerformanceEventVo getOne(String id) {
        return repository.findById(id).map(this::toVo).orElse(null);
    }

    @Override
    public PageRowsVo<PerformanceEventVo> getPageList(PerformanceEventDto searchCondition) {
        Pageable pageable = PageRequest.of(searchCondition.getPage() - 1, searchCondition.getPerPage());
        Page<PerformanceEvent> result = "desc".equalsIgnoreCase(searchCondition.getOrderBy())
                ? repository.findByPageDesc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null)
                : repository.findByPageAsc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null);
        List<PerformanceEventVo> voList = new ArrayList<>();
        for (PerformanceEvent entity : result.getContent()) {
            voList.add(toVo(entity));
        }
        return new PageRowsVo<>(voList, result.getTotalElements());
    }

    @Override
    public List<String> getSimilarIds(String term) {
        return repository.findLikeIds(term);
    }

    @Override
    public long countTotal() {
        return repository.count();
    }

    @Override
    public long countIncrease() {
        return repository.countTodayIncrease();
    }
} 