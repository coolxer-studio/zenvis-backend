package com.coolxer.service.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.ClickEventDto;
import com.coolxer.model.business.operation.vo.ClickEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClickEventService {
    boolean add(ClickEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, ClickEventDto dto);

    ClickEventVo getOne(String id);

    PageRowsVo<ClickEventVo> getPageList(ClickEventDto searchCondition);

    long countTotal();

    long countIncrease();

    List<String> getSimilarIds(String term);

    List<String> getSimilarComponentNames(String term);

    List<String> getSimilarPagePaths(String term);

    List<String> getSimilarPageNames(String term);
} 