package com.coolxer.model.dashboard.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * chart对象
 */
@Data
@Builder
@Accessors(chain = true)
public class StackedLineChartVo implements Serializable {

    private List<String> xAxis;
    private Set<String> yAxisName;
    private List<List<Long>> yAxisData;


}
