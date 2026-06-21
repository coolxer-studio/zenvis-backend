package com.coolxer.service.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.ExtendEventDto;
import com.coolxer.model.business.operation.vo.ExtendEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExtendEventService {
    boolean add(ExtendEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, ExtendEventDto dto);

    ExtendEventVo getOne(String id);

    PageRowsVo<ExtendEventVo> getPageList(ExtendEventDto searchCondition);

    List<String> getSimilarIds(String term);

    long countTotal();

    long countIncrease();
} 