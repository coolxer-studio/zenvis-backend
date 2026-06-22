package com.coolxer.service.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.PageEventDto;
import com.coolxer.model.business.operation.vo.PageEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageEventService {
    boolean add(PageEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, PageEventDto dto);

    PageEventVo getOne(String id);

    PageRowsVo<PageEventVo> getPageList(PageEventDto searchCondition);

    long countTotal();

    long countIncrease();

    List<String> getSimilarIds(String term);

    List<String> getSimilarPagePaths(String term);

    List<String> getSimilarPageNames(String term);

    List<String> getSimilarReferrers(String term);
}