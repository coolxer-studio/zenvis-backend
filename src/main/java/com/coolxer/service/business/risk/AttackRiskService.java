package com.coolxer.service.business.risk;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.risk.dto.AttackRiskDto;
import com.coolxer.model.business.risk.dto.AttackRiskSearchDto;
import com.coolxer.model.business.risk.vo.AttackRiskVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttackRiskService {

    boolean add(AttackRiskDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, AttackRiskDto dto);

    AttackRiskVo getOne(String id);

    PageRowsVo<AttackRiskVo> getPageList(AttackRiskSearchDto searchDto);

    List<String> getDistinctLabels();

    List<String> getSimilarIds(String term);

    List<String> getSimilarTypes(String term);

    long countTotal();

    long countIncrease();

} 