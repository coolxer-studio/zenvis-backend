package com.coolxer.service.business.operation.impl;

import com.coolxer.dao.clickhouse.entity.operation.AnrEvent;
import com.coolxer.dao.clickhouse.repository.operation.AnrEventRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.AnrEventDto;
import com.coolxer.model.business.operation.vo.AnrEventVo;
import com.coolxer.service.business.operation.AnrEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnrEventServiceImpl implements AnrEventService {
    @Autowired
    private AnrEventRepository repository;

    private AnrEvent toEntity(AnrEventDto dto) {
        AnrEvent entity = new AnrEvent();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private AnrEventVo toVo(AnrEvent entity) {
        AnrEventVo vo = new AnrEventVo();
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
    public boolean add(AnrEventDto dto) {
        try {
            AnrEvent entity = toEntity(dto);
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
    public boolean update(String id, AnrEventDto dto) {
        try {
            Optional<AnrEvent> optional = repository.findById(id);
            if (optional.isPresent()) {
                AnrEvent entity = optional.get();
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
    public AnrEventVo getOne(String id) {
        return repository.findById(id).map(this::toVo).orElse(null);
    }

    @Override
    public PageRowsVo<AnrEventVo> getPageList(AnrEventDto searchCondition) {
        Pageable pageable = PageRequest.of(searchCondition.getPage() - 1, searchCondition.getPerPage());
        Page<AnrEvent> result = "desc".equalsIgnoreCase(searchCondition.getOrderBy())
                ? repository.findByPageDesc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getPagePath(), searchCondition.getPageName(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null)
                : repository.findByPageAsc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getPagePath(), searchCondition.getPageName(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null);
        List<AnrEventVo> voList = new ArrayList<>();
        for (AnrEvent entity : result.getContent()) {
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
    public List<String> getSimilarPageNames(String term) {
        return repository.findLikePageNames(term);
    }

    @Override
    public List<String> getSimilarPagePaths(String term) {
        return repository.findLikePagePaths(term);
    }

} 