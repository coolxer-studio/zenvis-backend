package com.coolxer.service.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.PerformanceEventDto;
import com.coolxer.model.business.operation.vo.PerformanceEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PerformanceEventService {
    boolean add(PerformanceEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, PerformanceEventDto dto);

    PerformanceEventVo getOne(String id);

    PageRowsVo<PerformanceEventVo> getPageList(PerformanceEventDto searchCondition);

    List<String> getSimilarIds(String term);

    long countTotal();

    long countIncrease();
} 