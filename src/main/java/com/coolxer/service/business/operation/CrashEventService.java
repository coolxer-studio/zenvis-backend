package com.coolxer.service.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.CrashEventDto;
import com.coolxer.model.business.operation.vo.CrashEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrashEventService {
    boolean add(CrashEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, CrashEventDto dto);

    CrashEventVo getOne(String id);

    PageRowsVo<CrashEventVo> getPageList(CrashEventDto searchCondition);

    long countTotal();

    long countIncrease();

    List<String> getSimilarIds(String term);

    List<String> getSimilarMessages(String term);
}