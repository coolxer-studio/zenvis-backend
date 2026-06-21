package com.coolxer.service.business.risk;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.risk.dto.WeakRiskDto;
import com.coolxer.model.business.risk.dto.WeakRiskSearchDto;
import com.coolxer.model.business.risk.vo.WeakRiskVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeakRiskService {

    boolean add(WeakRiskDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, WeakRiskDto dto);

    WeakRiskVo getOne(String id);

    PageRowsVo<WeakRiskVo> getPageList(WeakRiskSearchDto searchDto);

    List<String> getDistinctLabels();

    List<String> getSimilarIds(String term);

    List<String> getSimilarUsernames(String term);

    List<String> getSimilarPasswordTypes(String term);

    long countTotal();

    long countIncrease();

} 