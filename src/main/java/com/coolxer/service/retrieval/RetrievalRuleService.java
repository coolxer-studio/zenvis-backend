package com.coolxer.service.retrieval;

import com.coolxer.model.retrieval.dto.RetrievalRequestDto;
import com.coolxer.model.retrieval.rule.RetrievalRule;
import com.coolxer.model.retrieval.vo.RetrievalRuleVo;

import java.util.List;

public interface RetrievalRuleService {

    RetrievalRule getRuleById(Integer id);

    List<RetrievalRuleVo> getAllRule();

    void saveRule(RetrievalRule retrievalRule);

    void deleteRule(Integer id);

    RetrievalRule generateRetrievalRule(RetrievalRequestDto retrievalRequestDTO);

}
