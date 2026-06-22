package com.coolxer.service.dashboard;

import com.coolxer.model.base.dto.BaseQueryDto;
import com.coolxer.model.dashboard.vo.*;

/**
 * 首页图表服务
 */
public interface MsgChartService {
    /**
     * 厂商-系统分布
     *
     * @param baseQueryDto
     * @return
     */
    StackedBarChartVo findManufactureSystemGroup(BaseQueryDto baseQueryDto);

    /**
     * 省-市分布
     *
     * @param baseQueryDto
     * @return
     */
    StackedBarChartVo findProvinceCityGroup(BaseQueryDto baseQueryDto);

    /**
     * 数据趋势分析
     *
     * @param baseQueryDto
     * @return
     */

    StackedLineChartVo findMsgTrend(BaseQueryDto baseQueryDto);

    /**
     * 汇总信息
     *
     * @return
     */
    SummaryVo summary(BaseQueryDto baseQueryDto);

    /**
     * 状态
     *
     * @return
     */
    StatusVo status();

    /**
     * 效率
     *
     * @return
     */
    EfficiencyVo efficiency();

    /**
     * 实时信息
     *
     * @return
     */
    RealInfoVo realInfo();


    /**
     * 速度
     *
     * @return
     */
    SpeedVo findSpeed();
}
