package com.coolxer.service.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.LocationEventDto;
import com.coolxer.model.business.operation.vo.LocationEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationEventService {
    boolean add(LocationEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, LocationEventDto dto);

    LocationEventVo getOne(String id);

    PageRowsVo<LocationEventVo> getPageList(LocationEventDto searchCondition);

    List<String> getSimilarIds(String term);

    long countTotal();

    long countIncrease();
} 