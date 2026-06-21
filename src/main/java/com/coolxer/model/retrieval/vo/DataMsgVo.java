package com.coolxer.model.retrieval.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * msg单条消息的实体
 */
@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DataMsgVo implements Serializable {

    private BigDecimal startId;

    private String appName;

    private String appVersion;

    private String platform;

    private String lanIp;

    private String wanIp;

    private String factType;

    private String agendaTags;

    private String punishTypes;

    private String location;

    private String clientTime;

    private String serverTime;

    private String data;
}
