package com.coolxer.service.business.operation.impl;

import com.coolxer.dao.clickhouse.entity.operation.ClickEvent;
import com.coolxer.dao.clickhouse.repository.operation.ClickEventRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.ClickEventDto;
import com.coolxer.model.business.operation.vo.ClickEventVo;
import com.coolxer.service.business.operation.ClickEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClickEventServiceImpl implements ClickEventService {
    @Autowired
    private ClickEventRepository clickEventRepository;

    private ClickEvent toEntity(ClickEventDto dto) {
        ClickEvent entity = new ClickEvent();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private ClickEventVo toVo(ClickEvent entity) {
        ClickEventVo vo = new ClickEventVo();
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
    public boolean add(ClickEventDto dto) {
        try {
            ClickEvent entity = toEntity(dto);
            if (entity.getId() == null || entity.getId().isEmpty()) {
                entity.setId(UUID.randomUUID().toString());
            }
            entity.setUpdateTime(new Date());
            entity.setInsertTime(new Date());
            clickEventRepository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            clickEventRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAll(List<String> ids) {
        try {
            clickEventRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(String id, ClickEventDto dto) {
        try {
            Optional<ClickEvent> optional = clickEventRepository.findById(id);
            if (optional.isPresent()) {
                ClickEvent entity = optional.get();
                BeanUtils.copyProperties(dto, entity);
                clickEventRepository.save(entity);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ClickEventVo getOne(String id) {
        return clickEventRepository.findById(id).map(this::toVo).orElse(null);
    }

    @Override
    public PageRowsVo<ClickEventVo> getPageList(ClickEventDto searchCondition) {
        Pageable pageable = PageRequest.of(searchCondition.getPage() - 1, searchCondition.getPerPage());
        Page<ClickEvent> result = "desc".equalsIgnoreCase(searchCondition.getOrderBy())
                ? clickEventRepository.findByPageDesc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getComponentType(), searchCondition.getComponentName(), searchCondition.getPagePath(), searchCondition.getPageName(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null)
                : clickEventRepository.findByPageAsc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getComponentType(), searchCondition.getComponentName(), searchCondition.getPagePath(), searchCondition.getPageName(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null);
        List<ClickEventVo> voList = new ArrayList<>();
        for (ClickEvent entity : result.getContent()) {
            voList.add(toVo(entity));
        }
        return new PageRowsVo<>(voList, result.getTotalElements());
    }

    @Override
    public long countTotal() {
        return clickEventRepository.count();
    }

    @Override
    public long countIncrease() {
        return clickEventRepository.countTodayIncrease();
    }

    @Override
    public List<String> getSimilarIds(String term) {
        return clickEventRepository.findLikeIds(term);
    }

    @Override
    public List<String> getSimilarComponentNames(String term) {
        return clickEventRepository.findLikeComponentNames(term);
    }

    @Override
    public List<String> getSimilarPagePaths(String term) {
        return clickEventRepository.findLikePagePaths(term);
    }

    @Override
    public List<String> getSimilarPageNames(String term) {
        return clickEventRepository.findLikePageNames(term);
    }
}