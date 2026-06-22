package com.coolxer.model.business.operation.dto;

import com.coolxer.configuration.JsonToStringDeserializer;
import com.coolxer.model.base.dto.SortPageDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

/**
 * 运营看板DTO
 */
@Data
public class OperationBoardDto extends SortPageDto {

    /**
     * ID
     */
    private Integer id;

    private Long lastBoard;

    private String policy;

    private String event;
    private String metrics;

    /**
     * 条件，json格式
     */
    @JsonDeserialize(using = JsonToStringDeserializer.class)
    private String conditions;

    private String panelTitle;
    private String panelView;


} 