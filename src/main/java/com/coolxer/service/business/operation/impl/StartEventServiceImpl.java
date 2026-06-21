package com.coolxer.service.business.operation.impl;

import com.coolxer.dao.clickhouse.entity.operation.StartEvent;
import com.coolxer.dao.clickhouse.repository.operation.StartEventRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.StartEventDto;
import com.coolxer.model.business.operation.vo.StartEventVo;
import com.coolxer.service.business.operation.StartEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StartEventServiceImpl implements StartEventService {
    @Autowired
    private StartEventRepository repository;

    private StartEvent toEntity(StartEventDto dto) {
        StartEvent entity = new StartEvent();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private StartEventVo toVo(StartEvent entity) {
        StartEventVo vo = new StartEventVo();
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
    public boolean add(StartEventDto dto) {
        try {
            StartEvent entity = toEntity(dto);
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
    public boolean update(String id, StartEventDto dto) {
        try {
            Optional<StartEvent> optional = repository.findById(id);
            if (optional.isPresent()) {
                StartEvent entity = optional.get();
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
    public StartEventVo getOne(String id) {
        return repository.findById(id).map(this::toVo).orElse(null);
    }

    @Override
    public PageRowsVo<StartEventVo> getPageList(StartEventDto searchCondition) {
        Pageable pageable = PageRequest.of(searchCondition.getPage() - 1, searchCondition.getPerPage());
        Page<StartEvent> result = "desc".equalsIgnoreCase(searchCondition.getOrderBy())
                ? repository.findByPageDesc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getDeviceId(), searchCondition.getAppId(), searchCondition.getAppName(), searchCondition.getPackageName(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null)
                : repository.findByPageAsc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getDeviceId(), searchCondition.getAppId(), searchCondition.getAppName(), searchCondition.getPackageName(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null);
        List<StartEventVo> voList = new ArrayList<>();
        for (StartEvent entity : result.getContent()) {
            voList.add(toVo(entity));
        }
        return new PageRowsVo<>(voList, result.getTotalElements());
    }

    @Override
    public List<String> getSimilarIds(String term) {
        return repository.findLikeIds(term);
    }

    @Override
    public List<String> getSimilarAppNames(String term) {
        return repository.findLikeAppNames(term);
    }

    @Override
    public List<String> getSimilarPackageNames(String term) {
        return repository.findLikePackageNames(term);
    }

    @Override
    public List<String> getSimilarDeviceModels(String term) {
        return repository.findLikeDeviceModels(term);
    }

    @Override
    public List<String> getSimilarDeviceOses(String term) {
        return repository.findLikeDeviceOses(term);
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