package com.coolxer.model.dashboard.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 速率对象
 */
@Data
@Builder
@Accessors(chain = true)
public class SpeedVo implements Serializable {

    private double receiveTimeAverage;
    private double receiveTimeCurrent;

    private double processTimeAverage;
    private double processTimeCurrent;

    private double countOfSecondAverage;
    private double countOfSecondCurrent;

}
