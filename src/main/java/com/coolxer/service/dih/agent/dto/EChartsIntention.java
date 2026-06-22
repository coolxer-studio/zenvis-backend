package com.coolxer.service.dih.agent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class EChartsIntention {

    public EChartsIntention(Boolean needECharts) {
        this.needECharts = needECharts;
    }

    @JsonProperty("need_chart")
    private Boolean needECharts;
    @JsonProperty("chart_type")
    private String chartType;
}
