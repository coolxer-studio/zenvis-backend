package com.coolxer.dao.clickhouse.repository.asset;

import com.coolxer.dao.clickhouse.entity.asset.AssetLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 日志资产数据访问接口
 * 提供日志资产的增删改查等基础操作
 */
public interface AssetLogRepository extends JpaRepository<AssetLog, String> {

    /**
     * 分页查询日志资产列表（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_log ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_log")
    Page<AssetLog> findByPage(Pageable pageable);

    /**
     * 分页查询日志资产列表（按更新时间降序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_log ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_log")
    Page<AssetLog> findByPageDesc(Pageable pageable);

    /**
     * 分页查询日志资产列表（按更新时间升序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_log WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:logName IS NULL OR :logName = '' OR log_name LIKE concat('%', :logName, '%')) AND " +
                    "(:logPath IS NULL OR :logPath = '' OR log_path LIKE concat('%', :logPath, '%')) AND " +
                    "(:logType IS NULL OR :logType = '' OR log_type LIKE concat('%', :logType, '%')) AND " +
                    "(:logFormat IS NULL OR :logFormat = '' OR log_format LIKE concat('%', :logFormat, '%')) AND " +
                    "(:logTimeStart IS NULL OR :logTimeStart = '' OR log_time >= parseDateTimeBestEffort(:logTimeStart)) AND " +
                    "(:logTimeEnd IS NULL OR :logTimeEnd = '' OR log_time <= parseDateTimeBestEffort(:logTimeEnd)) AND " +
                    "(:logLevel IS NULL OR :logLevel = '' OR log_level LIKE concat('%', :logLevel, '%')) AND " +
                    "(:process IS NULL OR :process = '' OR process LIKE concat('%', :process, '%')) AND " +
                    "(:logMessage IS NULL OR :logMessage = '' OR log_message LIKE concat('%', :logMessage, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_log WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:logName IS NULL OR :logName = '' OR log_name LIKE concat('%', :logName, '%')) AND " +
                    "(:logPath IS NULL OR :logPath = '' OR log_path LIKE concat('%', :logPath, '%')) AND " +
                    "(:logType IS NULL OR :logType = '' OR log_type LIKE concat('%', :logType, '%')) AND " +
                    "(:logFormat IS NULL OR :logFormat = '' OR log_format LIKE concat('%', :logFormat, '%')) AND " +
                    "(:logTimeStart IS NULL OR :logTimeStart = '' OR log_time >= parseDateTimeBestEffort(:logTimeStart)) AND " +
                    "(:logTimeEnd IS NULL OR :logTimeEnd = '' OR log_time <= parseDateTimeBestEffort(:logTimeEnd)) AND " +
                    "(:logLevel IS NULL OR :logLevel = '' OR log_level LIKE concat('%', :logLevel, '%')) AND " +
                    "(:process IS NULL OR :process = '' OR process LIKE concat('%', :process, '%')) AND " +
                    "(:logMessage IS NULL OR :logMessage = '' OR log_message LIKE concat('%', :logMessage, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd))")
    Page<AssetLog> findByConditionsASC(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("logName") String logName,
            @Param("logPath") String logPath,
            @Param("logType") String logType,
            @Param("logFormat") String logFormat,
            @Param("logTimeStart") String logTimeStart,
            @Param("logTimeEnd") String logTimeEnd,
            @Param("logLevel") String logLevel,
            @Param("process") String process,
            @Param("logMessage") String logMessage,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 分页查询日志资产列表（按更新时间降序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_log WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:logName IS NULL OR :logName = '' OR log_name LIKE concat('%', :logName, '%')) AND " +
                    "(:logPath IS NULL OR :logPath = '' OR log_path LIKE concat('%', :logPath, '%')) AND " +
                    "(:logType IS NULL OR :logType = '' OR log_type LIKE concat('%', :logType, '%')) AND " +
                    "(:logFormat IS NULL OR :logFormat = '' OR log_format LIKE concat('%', :logFormat, '%')) AND " +
                    "(:logTimeStart IS NULL OR :logTimeStart = '' OR log_time >= parseDateTimeBestEffort(:logTimeStart)) AND " +
                    "(:logTimeEnd IS NULL OR :logTimeEnd = '' OR log_time <= parseDateTimeBestEffort(:logTimeEnd)) AND " +
                    "(:logLevel IS NULL OR :logLevel = '' OR log_level LIKE concat('%', :logLevel, '%')) AND " +
                    "(:process IS NULL OR :process = '' OR process LIKE concat('%', :process, '%')) AND " +
                    "(:logMessage IS NULL OR :logMessage = '' OR log_message LIKE concat('%', :logMessage, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_log WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:logName IS NULL OR :logName = '' OR log_name LIKE concat('%', :logName, '%')) AND " +
                    "(:logPath IS NULL OR :logPath = '' OR log_path LIKE concat('%', :logPath, '%')) AND " +
                    "(:logType IS NULL OR :logType = '' OR log_type LIKE concat('%', :logType, '%')) AND " +
                    "(:logFormat IS NULL OR :logFormat = '' OR log_format LIKE concat('%', :logFormat, '%')) AND " +
                    "(:logTimeStart IS NULL OR :logTimeStart = '' OR log_time >= parseDateTimeBestEffort(:logTimeStart)) AND " +
                    "(:logTimeEnd IS NULL OR :logTimeEnd = '' OR log_time <= parseDateTimeBestEffort(:logTimeEnd)) AND " +
                    "(:logLevel IS NULL OR :logLevel = '' OR log_level LIKE concat('%', :logLevel, '%')) AND " +
                    "(:process IS NULL OR :process = '' OR process LIKE concat('%', :process, '%')) AND " +
                    "(:logMessage IS NULL OR :logMessage = '' OR log_message LIKE concat('%', :logMessage, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd))")
    Page<AssetLog> findByConditionsDesc(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("logName") String logName,
            @Param("logPath") String logPath,
            @Param("logType") String logType,
            @Param("logFormat") String logFormat,
            @Param("logTimeStart") String logTimeStart,
            @Param("logTimeEnd") String logTimeEnd,
            @Param("logLevel") String logLevel,
            @Param("process") String process,
            @Param("logMessage") String logMessage,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 获取所有不重复的标签列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT arrayJoin(label) FROM asset_log")
    List<String> findDistinctLabels();

    /**
     * 获取相似的资产ID列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT id FROM asset_log WHERE id LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeIds(@Param("term") String term);

    /**
     * 获取相似的日志名称列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT log_name FROM asset_log WHERE log_name LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeLogNames(@Param("term") String term);

    /**
     * 获取相似的日志路径列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT log_path FROM asset_log WHERE log_path LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeLogPaths(@Param("term") String term);

    /**
     * 获取相似的日志类型列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT log_type FROM asset_log WHERE log_type LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeLogTypes(@Param("term") String term);

    /**
     * 获取相似的日志格式列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT log_format FROM asset_log WHERE log_format LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeLogFormats(@Param("term") String term);

    /**
     * 获取相似的日志级别列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT log_level FROM asset_log WHERE log_level LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeLogLevels(@Param("term") String term);

    /**
     * 获取相似的进程信息列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT process FROM asset_log WHERE process LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeProcesses(@Param("term") String term);

    default long countTotal() {
        return count();
    }

    @Query(value = "SELECT count(*) FROM asset_log WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_log WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true, value = "select level as group_key, count(*) as count from asset_log GROUP BY level")
    List<Map<String, Object>> countByLevel();

    @Query(nativeQuery = true, value = "select risk as group_key, count(*) as count from asset_log GROUP BY risk")
    List<Map<String, Object>> countByRisk();
} 