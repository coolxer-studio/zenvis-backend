package com.coolxer.service.business.operation.impl;

import com.coolxer.dao.clickhouse.entity.operation.LocationEvent;
import com.coolxer.dao.clickhouse.repository.operation.LocationEventRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.LocationEventDto;
import com.coolxer.model.business.operation.vo.LocationEventVo;
import com.coolxer.service.business.operation.LocationEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationEventServiceImpl implements LocationEventService {
    @Autowired
    private LocationEventRepository repository;

    private LocationEvent toEntity(LocationEventDto dto) {
        LocationEvent entity = new LocationEvent();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private LocationEventVo toVo(LocationEvent entity) {
        LocationEventVo vo = new LocationEventVo();
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
    public boolean add(LocationEventDto dto) {
        try {
            LocationEvent entity = toEntity(dto);
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
    public boolean update(String id, LocationEventDto dto) {
        try {
            Optional<LocationEvent> optional = repository.findById(id);
            if (optional.isPresent()) {
                LocationEvent entity = optional.get();
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
    public LocationEventVo getOne(String id) {
        return repository.findById(id).map(this::toVo).orElse(null);
    }

    @Override
    public PageRowsVo<LocationEventVo> getPageList(LocationEventDto searchCondition) {
        Pageable pageable = PageRequest.of(searchCondition.getPage() - 1, searchCondition.getPerPage());
        Page<LocationEvent> result = "desc".equalsIgnoreCase(searchCondition.getOrderBy())
                ? repository.findByPageDesc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null)
                : repository.findByPageAsc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null);
        List<LocationEventVo> voList = new ArrayList<>();
        for (LocationEvent entity : result.getContent()) {
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