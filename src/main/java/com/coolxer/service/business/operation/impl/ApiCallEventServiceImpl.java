package com.coolxer.service.business.operation.impl;

import com.coolxer.dao.clickhouse.entity.operation.ApiCallEvent;
import com.coolxer.dao.clickhouse.repository.operation.ApiCallEventRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.ApiCallEventDto;
import com.coolxer.model.business.operation.vo.ApiCallEventVo;
import com.coolxer.service.business.operation.ApiCallEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApiCallEventServiceImpl implements ApiCallEventService {
    @Autowired
    private ApiCallEventRepository repository;

    private ApiCallEvent toEntity(ApiCallEventDto dto) {
        ApiCallEvent entity = new ApiCallEvent();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private ApiCallEventVo toVo(ApiCallEvent entity) {
        ApiCallEventVo vo = new ApiCallEventVo();
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
    public boolean add(ApiCallEventDto dto) {
        try {
            ApiCallEvent entity = toEntity(dto);
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
    public boolean update(String id, ApiCallEventDto dto) {
        try {
            Optional<ApiCallEvent> optional = repository.findById(id);
            if (optional.isPresent()) {
                ApiCallEvent entity = optional.get();
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
    public ApiCallEventVo getOne(String id) {
        return repository.findById(id).map(this::toVo).orElse(null);
    }

    @Override
    public PageRowsVo<ApiCallEventVo> getPageList(ApiCallEventDto searchCondition) {
        Pageable pageable = PageRequest.of(searchCondition.getPage() - 1, searchCondition.getPerPage());
        Page<ApiCallEvent> result = "desc".equalsIgnoreCase(searchCondition.getOrderBy())
                ? repository.findByPageDesc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getCaller(), searchCondition.getCallee(), searchCondition.getFunctionName(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null)
                : repository.findByPageAsc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getCaller(), searchCondition.getCallee(), searchCondition.getFunctionName(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null);
        List<ApiCallEventVo> voList = new ArrayList<>();
        for (ApiCallEvent entity : result.getContent()) {
            voList.add(toVo(entity));
        }
        return new PageRowsVo<>(voList, result.getTotalElements());
    }

    @Override
    public long countTotal() {
        return repository.count();
    }

    @Override
    public long countIncrease() {
        return repository.countTodayIncrease();
    }

    @Override
    public List<String> getSimilarIds(String term) {
        return repository.findLikeIds(term);
    }

    @Override
    public List<String> getSimilarCallers(String term) {
        return repository.findLikeCallers(term);
    }

    @Override
    public List<String> getSimilarCallees(String term) {
        return repository.findLikeCallees(term);
    }

    @Override
    public List<String> getSimilarFunctionNames(String term) {
        return repository.findLikeFunctionNames(term);
    }
}