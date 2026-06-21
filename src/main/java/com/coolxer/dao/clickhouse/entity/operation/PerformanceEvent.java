package com.coolxer.dao.clickhouse.entity.operation;

import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 资源利用指标事件实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_OPERATION_PERFORMANCE_EVENT)
public class PerformanceEvent {

    /**
     * 事件唯一标识
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
     * CPU相关指标
     */
    /**
     * CPU总体利用率（百分比）
     */
    @Column(name = "cpu_usage")
    private Double cpuUsage;

    /**
     * CPU用户态利用率（百分比）
     */
    @Column(name = "cpu_user")
    private Double cpuUser;

    /**
     * CPU系统态利用率（百分比）
     */
    @Column(name = "cpu_system")
    private Double cpuSystem;

    /**
     * CPU空闲率（百分比）
     */
    @Column(name = "cpu_idle")
    private Double cpuIdle;

    /**
     * 内存相关指标
     */
    /**
     * 物理内存总量(KB)
     */
    @Column(name = "memory_total")
    private Long memoryTotal;

    /**
     * 物理内存使用量(KB)
     */
    @Column(name = "memory_used")
    private Long memoryUsed;

    /**
     * 物理内存利用率（百分比）
     */
    @Column(name = "memory_usage")
    private Double memoryUsage;

    /**
     * 虚拟内存总量(KB)
     */
    @Column(name = "virtual_memory_total")
    private Long virtualMemoryTotal;

    /**
     * 虚拟内存使用量(KB)
     */
    @Column(name = "virtual_memory_used")
    private Long virtualMemoryUsed;

    /**
     * 交换空间总量(KB)
     */
    @Column(name = "swap_total")
    private Long swapTotal;

    /**
     * 交换空间使用量(KB)
     */
    @Column(name = "swap_used")
    private Long swapUsed;

    /**
     * 磁盘I/O相关指标
     */
    /**
     * 磁盘读取速度(KB/s)
     */
    @Column(name = "disk_read_speed")
    private Double diskReadSpeed;

    /**
     * 磁盘写入速度(KB/s)
     */
    @Column(name = "disk_write_speed")
    private Double diskWriteSpeed;

    /**
     * 磁盘总吞吐量(KB/s)
     */
    @Column(name = "disk_throughput")
    private Double diskThroughput;

    /**
     * 磁盘IOPS(每秒I/O操作数)
     */
    @Column(name = "disk_iops")
    private Integer diskIops;

    /**
     * 网络带宽相关指标
     */
    /**
     * 网络发送速率(KB/s)
     */
    @Column(name = "network_send_rate")
    private Double networkSendRate;

    /**
     * 网络接收速率(KB/s)
     */
    @Column(name = "network_receive_rate")
    private Double networkReceiveRate;

    /**
     * 网络丢包率（百分比）
     */
    @Column(name = "network_packet_loss")
    private Double networkPacketLoss;

    /**
     * 网络延迟(ms)
     */
    @Column(name = "network_latency")
    private Double networkLatency;

    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

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

    /**
     * 事件发生时间
     */
    @Column(name = "event_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;

}
