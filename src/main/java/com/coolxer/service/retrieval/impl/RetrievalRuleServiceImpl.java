package com.coolxer.service.retrieval.impl;

import com.coolxer.dao.mysql.entity.RetrievalRule;
import com.coolxer.dao.mysql.repository.RetrievalRuleRepository;
import com.coolxer.model.retrieval.dto.RequestCriteriaDto;
import com.coolxer.model.retrieval.dto.RequestDisplayDto;
import com.coolxer.model.retrieval.dto.RetrievalRequestDto;
import com.coolxer.model.retrieval.meta.DataAttribute;
import com.coolxer.model.retrieval.meta.DataEntity;
import com.coolxer.model.retrieval.meta.DataOperator;
import com.coolxer.model.retrieval.rule.DisplayAttribute;
import com.coolxer.model.retrieval.rule.RetrievalCriteria;
import com.coolxer.model.retrieval.rule.RetrievalPageable;
import com.coolxer.model.retrieval.rule.RetrievalSql;
import com.coolxer.model.retrieval.vo.RetrievalRuleVo;
import com.coolxer.service.retrieval.MetaDataService;
import com.coolxer.service.retrieval.RetrievalRuleService;
import com.coolxer.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class RetrievalRuleServiceImpl implements RetrievalRuleService {

    @Autowired
    RetrievalRuleRepository retrievalRuleRepository;

    @Autowired
    MetaDataService metaDataService;

    @Override
    public com.coolxer.model.retrieval.rule.RetrievalRule getRuleById(Integer id) {
        RetrievalRule retrievalRuleEntity = retrievalRuleRepository.findById(id).orElse(null);
        com.coolxer.model.retrieval.rule.RetrievalRule retrievalRule = JacksonUtil.toObject(retrievalRuleEntity.getRuleString(), com.coolxer.model.retrieval.rule.RetrievalRule.class);
        return retrievalRule;
    }

    @Override
    public List<RetrievalRuleVo> getAllRule() {
        List<RetrievalRule> retrievalRuleList = retrievalRuleRepository.findAll();
        List<RetrievalRuleVo> retrievalRuleVoList = retrievalRuleList.stream().map(this::toRetrievalRuleVo).toList();
        return retrievalRuleVoList;
    }

    private RetrievalRuleVo toRetrievalRuleVo(RetrievalRule retrievalRule) {
        RetrievalRuleVo retrievalRuleVo = new RetrievalRuleVo();
        retrievalRuleVo.setName(retrievalRule.getName());
        retrievalRuleVo.setDescription(retrievalRule.getDescription());
        retrievalRuleVo.setId(retrievalRule.getId());
        retrievalRuleVo.setCreateTime(retrievalRule.getCreateTime());
        retrievalRuleVo.setUpdateTime(retrievalRule.getUpdateTime());
        return retrievalRuleVo;
    }

    @Override
    public void saveRule(com.coolxer.model.retrieval.rule.RetrievalRule retrievalRule) {
        RetrievalRule retrievalRuleEntity = new RetrievalRule();
        retrievalRuleEntity.setId(retrievalRule.getId());
        retrievalRuleEntity.setName(retrievalRule.getName());
        retrievalRuleEntity.setDescription(retrievalRule.getDescription());
        retrievalRuleEntity.setRuleString(JacksonUtil.toJson(retrievalRule));
        retrievalRuleRepository.save(retrievalRuleEntity);
    }

    @Override
    public void deleteRule(Integer id) {
        RetrievalRule retrievalRule = retrievalRuleRepository.findById(id).orElse(null);
        if (Objects.nonNull(retrievalRule)) {
            retrievalRuleRepository.delete(retrievalRule);
        } else {
            log.error("no exist rule id {}", id);
        }
    }

    @Override
    public com.coolxer.model.retrieval.rule.RetrievalRule generateRetrievalRule(RetrievalRequestDto retrievalRequestDTO) {
        com.coolxer.model.retrieval.rule.RetrievalRule retrievalRule = new com.coolxer.model.retrieval.rule.RetrievalRule();

        if (StringUtils.isNotBlank(retrievalRequestDTO.getSql())) {
            RetrievalSql retrievalSql = generateRetrievalSql(retrievalRequestDTO);
            retrievalRule.setRetrievalSql(retrievalSql);
        } else {
            List<RetrievalCriteria> retrievalCriteriaList = generateRetrievalCriteriaList(retrievalRequestDTO);
            retrievalRule.setRetrievalCriteria(retrievalCriteriaList);
        }

        List<DisplayAttribute> displayAttributeList = generateDisplayColumnList(retrievalRequestDTO);
        retrievalRule.setDisplayAttributes(displayAttributeList);

        if (StringUtils.isNotBlank(retrievalRequestDTO.getRuleName())) {
            retrievalRule.setName(retrievalRequestDTO.getRuleName());
            retrievalRule.setDescription(retrievalRequestDTO.getRuleDescription());
        }
        if (Objects.nonNull(retrievalRequestDTO.getId())) {
            retrievalRule.setId(retrievalRequestDTO.getId());
        }

        RetrievalPageable pageable = generateRetrievalPageable(retrievalRequestDTO);
        retrievalRule.setRetrievalPageable(pageable);

        return retrievalRule;
    }

    private RetrievalSql generateRetrievalSql(RetrievalRequestDto retrievalRequestDto) {
        RetrievalSql retrievalSql = new RetrievalSql();
        DataEntity dataEntity = metaDataService.getDataEntityByName(retrievalRequestDto.getEntity());
        retrievalSql.setEntity(dataEntity);
        retrievalSql.setSql(retrievalRequestDto.getSql());
        return retrievalSql;
    }

    private List<RetrievalCriteria> generateRetrievalCriteriaList(RetrievalRequestDto retrievalRequestDTO) {
        List<RequestCriteriaDto> criteriaDtoList = retrievalRequestDTO.getCriteriaList();
        return CollectionUtils.isEmpty(criteriaDtoList) ? new ArrayList<>() :
                criteriaDtoList.stream().map(criteriaDto -> toRetrievalCriteria(retrievalRequestDTO.getEntity(), criteriaDto)).toList();
    }

    private RetrievalCriteria toRetrievalCriteria(String entityName, RequestCriteriaDto criteriaDto) {
        DataAttribute attribute = metaDataService.getDataAttributeByName(entityName, criteriaDto.getAttribute());
        DataEntity entity = metaDataService.getDataEntityByName(attribute.getEntity());
        DataOperator operator = metaDataService.getDataOperatorByName(criteriaDto.getOperator());
        RetrievalCriteria retrievalCriteria = new RetrievalCriteria();
        retrievalCriteria.setAttribute(attribute);
        retrievalCriteria.setEntity(entity);
        retrievalCriteria.setOperator(operator);
        retrievalCriteria.setValueList(criteriaDto.getValueList());
        return retrievalCriteria;
    }

    private List<DisplayAttribute> generateDisplayColumnList(RetrievalRequestDto retrievalRequestDTO) {
        List<RequestDisplayDto> displayDtoList = retrievalRequestDTO.getDisplayList();
        return CollectionUtils.isEmpty(displayDtoList) ? new ArrayList<>() :
                displayDtoList.stream()
                        .map(this::toDisplayAttribute)
                        .filter(Objects::nonNull)
                        .toList();
    }

    private DisplayAttribute toDisplayAttribute(RequestDisplayDto displayDto) {
        DataEntity entity = metaDataService.getDataEntityByName(displayDto.getEntity());
        if (Objects.isNull(entity)) {
            log.error("no entity");
            return null;
        }
        List<DataAttribute> attributeList = displayDto.getAttributeList().stream()
                .map(attribute -> metaDataService.getDataAttributeByName(displayDto.getEntity(), attribute))
                .toList();
        DisplayAttribute displayAttribute = new DisplayAttribute();
        displayAttribute.setEntity(entity);
        displayAttribute.setAttributeList(attributeList);
        return displayAttribute;
    }

    private RetrievalPageable generateRetrievalPageable(RetrievalRequestDto retrievalRequestDto) {
        DataAttribute dataAttribute = metaDataService.getDataAttributeByName(retrievalRequestDto.getEntity(), retrievalRequestDto.getSortBy());
        String sortByColumnName;
        if (dataAttribute == null) {
            // 未指定排序字段，使用默认值
            DataEntity entity = metaDataService.getDataEntityByName(retrievalRequestDto.getEntity());
            sortByColumnName = entity.getSortColumn();
        } else {
            sortByColumnName = dataAttribute.getColumnName();
        }
        return new RetrievalPageable(retrievalRequestDto.getPage(),
                retrievalRequestDto.getSize(), sortByColumnName, retrievalRequestDto.getOrder());
    }

}
