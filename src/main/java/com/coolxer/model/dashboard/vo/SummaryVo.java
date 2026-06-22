package com.coolxer.model.dashboard.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 汇总对象
 */
@Data
@Builder
@Accessors(chain = true)
public class SummaryVo implements Serializable {

    private long msgTotal;
    private long deviceTotal;
    private long startTotal;


}
