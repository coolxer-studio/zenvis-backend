package com.coolxer.service.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.ApiCallEventDto;
import com.coolxer.model.business.operation.vo.ApiCallEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiCallEventService {
    boolean add(ApiCallEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, ApiCallEventDto dto);

    ApiCallEventVo getOne(String id);

    PageRowsVo<ApiCallEventVo> getPageList(ApiCallEventDto searchCondition);

    long countTotal();

    long countIncrease();

    List<String> getSimilarIds(String term);

    List<String> getSimilarCallers(String term);

    List<String> getSimilarCallees(String term);

    List<String> getSimilarFunctionNames(String term);
}