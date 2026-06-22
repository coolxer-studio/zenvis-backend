package com.coolxer.dao.clickhouse.entity.risk;

import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import com.coolxer.dao.clickhouse.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 数据安全风险实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_DATA_RISK)
public class DataRisk {

    /**
     * 唯一标识
     */
    @Id
    private String id;

    /**
     * 用户ID，用户的唯一标识
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 启动ID，每次启动的唯一标识
     */
    @Column(name = "start_id")
    private String startId;

    /**
     * 资产ID
     */
    @Column(name = "asset_id")
    private String assetId;

    /**
     * 威胁类型
     */
    @Column(name = "type")
    private String type;


    /**
     * 威胁详情
     */
    @Column(name = "detail")
    private String detail;


    /**
     * 验证方法
     */
    @Column(name = "verification_method")
    private String verificationMethod;


    /**
     * 验证结果
     */
    @Column(name = "verification_result")
    private String verificationResult;


    /**
     * 标签
     */
    @Convert(converter = StringListConverter.class)
    private List<String> label;

    /**
     * 风险等级
     */
    @Column(name = "risk_level")
    private String riskLevel;


    /**
     * 网络类型
     */
    @Column(name = "net_type")
    private String netType;

    /**
     * 局域网IP
     */
    @Column(name = "lan_ip")
    private String lanIp;

    /**
     * 因特网IP
     */
    @Column(name = "wan_ip")
    private String wanIp;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "insert_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertTime;

}
