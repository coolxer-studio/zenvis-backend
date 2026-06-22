package com.coolxer.service.business.risk;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.risk.dto.RiskEventDto;
import com.coolxer.model.business.risk.dto.RiskEventSearchDto;
import com.coolxer.model.business.risk.vo.RiskEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RiskEventService {

    boolean add(RiskEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, RiskEventDto dto);

    RiskEventVo getOne(String id);

    PageRowsVo<RiskEventVo> getPageList(RiskEventSearchDto searchDto);

    List<String> getDistinctLabels();

    List<String> getSimilarIds(String term);

    List<String> getSimilarTypes(String term);

    long countTotal();

    long countIncrease();

} 