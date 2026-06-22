package com.coolxer.service.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.StartEventDto;
import com.coolxer.model.business.operation.vo.StartEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StartEventService {
    boolean add(StartEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, StartEventDto dto);

    StartEventVo getOne(String id);

    PageRowsVo<StartEventVo> getPageList(StartEventDto searchCondition);

    List<String> getSimilarIds(String term);

    List<String> getSimilarAppNames(String term);

    List<String> getSimilarPackageNames(String term);

    List<String> getSimilarDeviceModels(String term);

    List<String> getSimilarDeviceOses(String term);

    long countTotal();

    long countIncrease();
} 