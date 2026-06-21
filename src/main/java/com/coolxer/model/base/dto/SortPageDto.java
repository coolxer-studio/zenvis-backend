package com.coolxer.model.base.dto;

import lombok.Data;

/**
 * 排序分页响应
 */
@Data
public class SortPageDto extends PageDto {

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排序方式
     */
    private String orderDir;
}
