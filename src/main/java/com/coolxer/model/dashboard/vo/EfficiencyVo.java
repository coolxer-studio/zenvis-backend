package com.coolxer.model.dashboard.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 效率对象
 */
@Data
@Builder
@Accessors(chain = true)
public class EfficiencyVo implements Serializable {

    private int ratio;
    private String receiveDelay;
    private String receiveDelayAverage;
    private String processDelay;
    private String processDelayAverage;
    private String msgCountForMinute;
    private String msgCountForMinuteAverage;


}
