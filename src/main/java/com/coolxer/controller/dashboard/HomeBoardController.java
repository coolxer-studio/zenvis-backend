package com.coolxer.controller.dashboard;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.dto.BaseQueryDto;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.dashboard.vo.*;
import com.coolxer.service.dashboard.MsgChartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * chart图表接口
 */
@Tag(name = "chart图表接口")
@Slf4j
@RestController
@RequestMapping("/api/v1/dashboard/home")
public class HomeBoardController extends BaseController {

    @Autowired
    private MsgChartService msgChartService;

    /**
     * 速率表
     *
     * @return 结果
     */
    @PostMapping(value = "/speed-stat")
    @Operation(summary = "速率表", description = "速率表")
    public ResponseWrap<SpeedVo> speedStat() {
        //返回数据
        return ResponseWrap.success(msgChartService.findSpeed());
    }

    /**
     * 数据总计
     *
     * @return 结果
     */
    @PostMapping(value = "/summary")
    @Operation(summary = "数据总计", description = "数据总计")
    public ResponseWrap<SummaryVo> summary(@RequestBody BaseQueryDto baseQueryDto) {
        if (Objects.isNull(baseQueryDto.getStartTime())) {
            ResponseWrap.fail(ResultCodeEnum.START_TIME_NOT_NULL);
        }
        if (Objects.isNull(baseQueryDto.getEndTime())) {
            ResponseWrap.fail(ResultCodeEnum.END_TIME_NOT_NULL);
        }
        //返回数据
        return ResponseWrap.success(msgChartService.summary(baseQueryDto));
    }

    /**
     * 状态
     *
     * @return 结果
     */
    @PostMapping(value = "/status")
    @Operation(summary = "状态结果", description = "状态结果")
    public ResponseWrap<StatusVo> status() {
        //返回数据
        return ResponseWrap.success(msgChartService.status());
    }

    /**
     * 效率
     *
     * @return 结果
     */
    @PostMapping(value = "/efficiency")
    @Operation(summary = "效率", description = "效率")
    public ResponseWrap<EfficiencyVo> efficiency() {
        //返回数据
        return ResponseWrap.success(msgChartService.efficiency());
    }


    /**
     * 实时信息
     *
     * @return 结果
     */
    @PostMapping(value = "/real-info")
    @Operation(summary = "实时信息", description = "实时信息")
    public ResponseWrap<RealInfoVo> realInfo() {
        //返回数据
        return ResponseWrap.success(msgChartService.realInfo());
    }


    /**
     * 省-市地域分布
     *
     * @return 结果
     */
    @PostMapping(value = "/province-city-stat")
    @Operation(summary = "省-市地域分布", description = "省-市地域分布")
    public ResponseWrap<StackedBarChartVo> provinceCityStat(@RequestBody BaseQueryDto baseQueryDto) {

        if (Objects.isNull(baseQueryDto.getStartTime())) {
            ResponseWrap.fail(ResultCodeEnum.START_TIME_NOT_NULL);
        }
        if (Objects.isNull(baseQueryDto.getEndTime())) {
            ResponseWrap.fail(ResultCodeEnum.END_TIME_NOT_NULL);
        }

        //返回数据
        return ResponseWrap.success(msgChartService.findProvinceCityGroup(baseQueryDto));
    }

    /**
     * 厂商-操作系统分布
     *
     * @return 结果
     */
    @PostMapping(value = "/manufacture-system-stat")
    @Operation(summary = "厂商-操作系统分布", description = "厂商-操作系统分布")
    public ResponseWrap<StackedBarChartVo> manufactureSystemStat(@RequestBody BaseQueryDto baseQueryDto) {

        if (Objects.isNull(baseQueryDto.getStartTime())) {
            ResponseWrap.fail(ResultCodeEnum.START_TIME_NOT_NULL);
        }
        if (Objects.isNull(baseQueryDto.getEndTime())) {
            ResponseWrap.fail(ResultCodeEnum.END_TIME_NOT_NULL);
        }

        //返回数据
        return ResponseWrap.success(msgChartService.findManufactureSystemGroup(baseQueryDto));
    }

    /**
     * 数据分布
     *
     * @return 结果
     */
    @PostMapping(value = "/msg-trend")
    @Operation(summary = "数据分布", description = "数据分布")
    public ResponseWrap<StackedLineChartVo> msgTrend(@RequestBody BaseQueryDto baseQueryDto) {

        if (Objects.isNull(baseQueryDto.getStartTime())) {
            ResponseWrap.fail(ResultCodeEnum.START_TIME_NOT_NULL);
        }
        if (Objects.isNull(baseQueryDto.getEndTime())) {
            ResponseWrap.fail(ResultCodeEnum.END_TIME_NOT_NULL);
        }

        //返回数据
        return ResponseWrap.success(msgChartService.findMsgTrend(baseQueryDto));
    }


}
