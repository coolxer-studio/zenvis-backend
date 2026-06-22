package com.coolxer.model.dashboard.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 效率对象
 */
@Data
@Builder
@Accessors(chain = true)
public class RealInfoVo implements Serializable {

    private List<String> infoList;

}
