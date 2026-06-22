package com.coolxer.service.business.operation.impl;

import com.coolxer.dao.clickhouse.entity.operation.NetworkEvent;
import com.coolxer.dao.clickhouse.repository.operation.NetworkEventRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.NetworkEventDto;
import com.coolxer.model.business.operation.vo.NetworkEventVo;
import com.coolxer.service.business.operation.NetworkEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NetworkEventServiceImpl implements NetworkEventService {
    @Autowired
    private NetworkEventRepository repository;

    private NetworkEvent toEntity(NetworkEventDto dto) {
        NetworkEvent entity = new NetworkEvent();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private NetworkEventVo toVo(NetworkEvent entity) {
        NetworkEventVo vo = new NetworkEventVo();
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
    public boolean add(NetworkEventDto dto) {
        try {
            NetworkEvent entity = toEntity(dto);
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
    public boolean update(String id, NetworkEventDto dto) {
        try {
            Optional<NetworkEvent> optional = repository.findById(id);
            if (optional.isPresent()) {
                NetworkEvent entity = optional.get();
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
    public NetworkEventVo getOne(String id) {
        return repository.findById(id).map(this::toVo).orElse(null);
    }

    @Override
    public PageRowsVo<NetworkEventVo> getPageList(NetworkEventDto searchCondition) {
        Pageable pageable = PageRequest.of(searchCondition.getPage() - 1, searchCondition.getPerPage());
        Page<NetworkEvent> result = "desc".equalsIgnoreCase(searchCondition.getOrderBy())
                ? repository.findByPageDesc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getSourceIp(), searchCondition.getTargetIp(), searchCondition.getProtocol(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null)
                : repository.findByPageAsc(pageable, searchCondition.getId(), searchCondition.getUserId(), searchCondition.getStartId(), searchCondition.getSourceIp(), searchCondition.getTargetIp(), searchCondition.getProtocol(), searchCondition.getNetType(), searchCondition.getLanIp(), searchCondition.getWanIp(), null, null, null, null);
        List<NetworkEventVo> voList = new ArrayList<>();
        for (NetworkEvent entity : result.getContent()) {
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
    public List<String> getSimilarSourceIps(String term) {
        return repository.findLikeSourceIps(term);
    }

    @Override
    public List<String> getSimilarTargetIps(String term) {
        return repository.findLikeTargetIps(term);
    }
}