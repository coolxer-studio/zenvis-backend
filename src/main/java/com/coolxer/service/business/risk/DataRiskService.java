package com.coolxer.service.business.risk;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.risk.dto.DataRiskDto;
import com.coolxer.model.business.risk.dto.DataRiskSearchDto;
import com.coolxer.model.business.risk.vo.DataRiskVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataRiskService {

    boolean add(DataRiskDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, DataRiskDto dto);

    DataRiskVo getOne(String id);

    PageRowsVo<DataRiskVo> getPageList(DataRiskSearchDto searchDto);

    List<String> getDistinctLabels();

    List<String> getSimilarIds(String term);

    List<String> getSimilarTypes(String term);

    long countTotal();

    long countIncrease();

} 