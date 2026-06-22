package com.coolxer.model.retrieval.rule;

import lombok.Data;

import java.util.List;

@Data
public class RetrievalRule {

    private Integer id;

    private String name;

    private String description;

    private List<RetrievalCriteria> retrievalCriteria;

    private List<DisplayAttribute> displayAttributes;

    private RetrievalPageable retrievalPageable;

    private RetrievalSql retrievalSql;

}
