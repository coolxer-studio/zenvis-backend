package com.coolxer.dao.clickhouse.repository.risk;

import com.coolxer.dao.clickhouse.entity.risk.BaselineRisk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 基线风险数据访问接口
 */
public interface BaselineRiskRepository extends JpaRepository<BaselineRisk, String> {

    /**
     * 分页查询基线风险（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM risk_baseline ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM risk_baseline")
    Page<BaselineRisk> findByPage(Pageable pageable);

    /**
     * 多条件分页查询（升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM risk_baseline WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:assetId IS NULL OR :assetId = '' OR asset_id = :assetId) AND " +
                    "(:configurationName IS NULL OR :configurationName = '' OR configuration_name = :configurationName) AND " +
                    "(:riskLevel IS NULL OR :riskLevel = '' OR risk_level = :riskLevel) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM risk_baseline WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:assetId IS NULL OR :assetId = '' OR asset_id = :assetId) AND " +
                    "(:configurationName IS NULL OR :configurationName = '' OR configuration_name = :configurationName) AND " +
                    "(:riskLevel IS NULL OR :riskLevel = '' OR risk_level = :riskLevel) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<BaselineRisk> findByPageAsc(Pageable pageable,
                                     @Param("id") String id,
                                     @Param("userId") String userId,
                                     @Param("startId") String startId,
                                     @Param("assetId") String assetId,
                                     @Param("configurationName") String configurationName,
                                     @Param("riskLevel") String riskLevel,
                                     @Param("netType") String netType,
                                     @Param("lanIp") String lanIp,
                                     @Param("wanIp") String wanIp,
                                     @Param("updateTimeStart") String updateTimeStart,
                                     @Param("updateTimeEnd") String updateTimeEnd,
                                     @Param("insertTimeStart") String insertTimeStart,
                                     @Param("insertTimeEnd") String insertTimeEnd);

    /**
     * 多条件分页查询（降序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM risk_baseline WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:assetId IS NULL OR :assetId = '' OR asset_id = :assetId) AND " +
                    "(:configurationName IS NULL OR :configurationName = '' OR configuration_name = :configurationName) AND " +
                    "(:riskLevel IS NULL OR :riskLevel = '' OR risk_level = :riskLevel) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM risk_baseline WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:assetId IS NULL OR :assetId = '' OR asset_id = :assetId) AND " +
                    "(:configurationName IS NULL OR :configurationName = '' OR configuration_name = :configurationName) AND " +
                    "(:riskLevel IS NULL OR :riskLevel = '' OR risk_level = :riskLevel) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<BaselineRisk> findByPageDesc(Pageable pageable,
                                      @Param("id") String id,
                                      @Param("userId") String userId,
                                      @Param("startId") String startId,
                                      @Param("assetId") String assetId,
                                      @Param("configurationName") String configurationName,
                                      @Param("riskLevel") String riskLevel,
                                      @Param("netType") String netType,
                                      @Param("lanIp") String lanIp,
                                      @Param("wanIp") String wanIp,
                                      @Param("updateTimeStart") String updateTimeStart,
                                      @Param("updateTimeEnd") String updateTimeEnd,
                                      @Param("insertTimeStart") String insertTimeStart,
                                      @Param("insertTimeEnd") String insertTimeEnd);

    /**
     * 获取所有不重复的标签列表
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT arrayJoin(label) as distinct_label FROM risk_baseline WHERE label IS NOT NULL AND label != [] order by distinct_label")
    List<String> findDistinctLabels();

    /**
     * 根据ID模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT id FROM risk_baseline WHERE id IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR id LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeIds(@Param("searchTerm") String searchTerm);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT configuration_name FROM risk_baseline WHERE configuration_name IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR configuration_name LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeConfigurationNames(@Param("searchTerm") String searchTerm);

    /**
     * 统计总数
     */
    default long countTotal() {
        return count();
    }

    /**
     * 统计今日新增
     */
    @Query(value = "SELECT count(*) FROM risk_baseline WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    /**
     * 按天统计一周内新增
     */
    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM risk_baseline WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    /**
     * 按风险等级统计
     */
    @Query(nativeQuery = true, value = "select risk_level as group_key, count(*) as count from risk_baseline GROUP BY risk_level")
    List<Map<String, Object>> countByRiskLevel();
} 