package com.coolxer.model.dashboard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 状态对象
 */
@Data
@Builder
@Accessors(chain = true)
public class StatusVo implements Serializable {

    private String status;

    private Notice serviceStatus;
    private Notice msgStatus;
    private Notice toDo;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static
    class Notice {
        private int count;
        private String info;
    }


}
