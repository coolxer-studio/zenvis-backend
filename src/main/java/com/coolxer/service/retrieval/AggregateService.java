package com.coolxer.service.retrieval;

import com.coolxer.model.dashboard.vo.StackedLineChartVo;
import com.coolxer.model.retrieval.vo.AggregateMsgInfoVo;

import java.util.Map;

public interface AggregateService {

    public AggregateMsgInfoVo findAgendaTagsByParams(Map<String, String> params);

    public StackedLineChartVo findMsgTrend(Map<String, String> params);
}
