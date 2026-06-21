package com.coolxer.model.base.dto;

import lombok.Data;

/**
 * 分页响应
 */
@Data
public class PageDto {

    /**
     * 每页显示条数，默认 10
     */
    private int perPage = 10;

    /**
     * 当前页
     */
    private int page = 1;
}
