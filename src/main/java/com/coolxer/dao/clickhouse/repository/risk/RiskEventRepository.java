package com.coolxer.dao.clickhouse.repository.risk;

import com.coolxer.dao.clickhouse.entity.risk.RiskEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 风险事件数据访问接口
 */
public interface RiskEventRepository extends JpaRepository<RiskEvent, String> {

    /**
     * 分页查询风险事件（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM risk_event ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM risk_event")
    Page<RiskEvent> findByPage(Pageable pageable);

    /**
     * 多条件分页查询（升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM risk_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:assetId IS NULL OR :assetId = '' OR asset_id = :assetId) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:riskLevel IS NULL OR :riskLevel = '' OR risk_level = :riskLevel) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM risk_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:assetId IS NULL OR :assetId = '' OR asset_id = :assetId) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:riskLevel IS NULL OR :riskLevel = '' OR risk_level = :riskLevel) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<RiskEvent> findByPageAsc(Pageable pageable,
                                  @Param("id") String id,
                                  @Param("userId") String userId,
                                  @Param("startId") String startId,
                                  @Param("assetId") String assetId,
                                  @Param("type") String type,
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
            value = "SELECT * FROM risk_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:assetId IS NULL OR :assetId = '' OR asset_id = :assetId) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:riskLevel IS NULL OR :riskLevel = '' OR risk_level = :riskLevel) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM risk_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:assetId IS NULL OR :assetId = '' OR asset_id = :assetId) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:riskLevel IS NULL OR :riskLevel = '' OR risk_level = :riskLevel) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<RiskEvent> findByPageDesc(Pageable pageable,
                                   @Param("id") String id,
                                   @Param("userId") String userId,
                                   @Param("startId") String startId,
                                   @Param("assetId") String assetId,
                                   @Param("type") String type,
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
            value = "SELECT DISTINCT arrayJoin(label) as distinct_label FROM risk_event WHERE label IS NOT NULL AND label != [] order by distinct_label")
    List<String> findDistinctLabels();

    /**
     * 根据ID模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT id FROM risk_event WHERE id IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR id LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeIds(@Param("searchTerm") String searchTerm);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT type FROM risk_event WHERE type IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR type LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeTypes(@Param("searchTerm") String searchTerm);

    /**
     * 统计总数
     */
    default long countTotal() {
        return count();
    }

    /**
     * 统计今日新增
     */
    @Query(value = "SELECT count(*) FROM risk_event WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    /**
     * 按天统计一周内新增
     */
    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM risk_event WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    /**
     * 按风险等级统计
     */
    @Query(nativeQuery = true, value = "select risk_level as group_key, count(*) as count from risk_event GROUP BY risk_level")
    List<Map<String, Object>> countByRiskLevel();
} 