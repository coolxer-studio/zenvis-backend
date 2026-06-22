package com.coolxer.service.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.AnrEventDto;
import com.coolxer.model.business.operation.vo.AnrEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnrEventService {
    boolean add(AnrEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, AnrEventDto dto);

    AnrEventVo getOne(String id);

    PageRowsVo<AnrEventVo> getPageList(AnrEventDto searchCondition);

    long countTotal();

    long countIncrease();

    List<String> getSimilarIds(String term);

    List<String> getSimilarPagePaths(String term);

    List<String> getSimilarPageNames(String term);
}