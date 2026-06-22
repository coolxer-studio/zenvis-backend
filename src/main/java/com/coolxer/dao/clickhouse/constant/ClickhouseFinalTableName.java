package com.coolxer.dao.clickhouse.constant;

/**
 * 表名定义
 */
public class ClickhouseFinalTableName {

    private ClickhouseFinalTableName() {
    }

    /**
     * 原始数据表
     */
    public static final String T_MSG = "msg";

    /**
     * 去重设备表
     */
    public static final String T_MSG_DEVICE = "msg_device";

    /**
     * 启动信息表
     */
    public static final String T_MSG_START = "msg_start";

    /**
     * 服务器资产表
     */
    public static final String T_ASSET_HOST = "asset_host";

    /**
     * 移动设备资产表
     */
    public static final String T_ASSET_MOBILE = "asset_mobile";

    /**
     * PC设备资产表
     */
    public static final String T_ASSET_PC = "asset_pc";

    /**
     * IOT资产表
     */
    public static final String T_ASSET_IOT = "asset_iot";

    /**
     * 数据探针SDK资产表
     */
    public static final String T_ASSET_PROBE = "asset_probe";

    /**
     * APP应用程序资产表
     */
    public static final String T_ASSET_APP = "asset_app";

    /**
     * 系统服务资产表
     */
    public static final String T_ASSET_SERVICE = "asset_service";

    /**
     * RESTful API接口资产表
     */
    public static final String T_ASSET_API = "asset_api";

    /**
     * 日志资产表
     */
    public static final String T_ASSET_LOG = "asset_log";

    /**
     * 文件资产表
     */
    public static final String T_ASSET_FILE = "asset_file";

    /**
     * 应用启动事件表
     */
    public static final String T_OPERATION_START_EVENT = "operation_start_event";

    /**
     * 性能事件表
     */
    public static final String T_OPERATION_PERFORMANCE_EVENT = "operation_performance_event";

    /**
     * 位置事件表
     */
    public static final String T_OPERATION_LOCATION_EVENT = "operation_location_event";

    /**
     * ANR事件表
     */
    public static final String T_OPERATION_ANR_EVENT = "operation_anr_event";

    /**
     * 崩溃事件表
     */
    public static final String T_OPERATION_CRASH_EVENT = "operation_crash_event";

    /**
     * 扩展事件表
     */
    public static final String T_OPERATION_EXTEND_EVENT = "operation_extend_event";

    /**
     * 网络事件表
     */
    public static final String T_OPERATION_NETWORK_EVENT = "operation_network_event";

    /**
     * API调用事件表
     */
    public static final String T_OPERATION_API_CALL_EVENT = "operation_api_call_event";

    /**
     * 点击事件表
     */
    public static final String T_OPERATION_CLICK_EVENT = "operation_click_event";

    /**
     * 页面事件表
     */
    public static final String T_OPERATION_PAGE_EVENT = "operation_page_event";

    /**
     * 攻击风险表
     */
    public static final String T_ATTACK_RISK = "risk_attack";

    /**
     * 弱口令风险表
     */
    public static final String T_WEAK_RISK = "risk_weak";

    /**
     * 基线风险表
     */
    public static final String T_BASELINE_RISK = "risk_baseline";

    /**
     * 漏洞风险表
     */
    public static final String T_VULNERABILITY_RISK = "risk_vulnerability";

    /**
     * 数据风险表
     */
    public static final String T_DATA_RISK = "risk_data";

    /**
     * 风险事件表
     */
    public static final String T_RISK_EVENT = "risk_event";

}
