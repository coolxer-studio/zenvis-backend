package com.coolxer.service.business.risk;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.risk.dto.BaselineRiskDto;
import com.coolxer.model.business.risk.dto.BaselineRiskSearchDto;
import com.coolxer.model.business.risk.vo.BaselineRiskVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaselineRiskService {

    boolean add(BaselineRiskDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, BaselineRiskDto dto);

    BaselineRiskVo getOne(String id);

    PageRowsVo<BaselineRiskVo> getPageList(BaselineRiskSearchDto searchDto);

    List<String> getDistinctLabels();

    List<String> getSimilarIds(String term);

    List<String> getSimilarConfigurationNames(String term);

    long countTotal();

    long countIncrease();

} 