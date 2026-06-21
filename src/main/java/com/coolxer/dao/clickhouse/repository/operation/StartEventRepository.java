package com.coolxer.dao.clickhouse.repository.operation;

import com.coolxer.dao.clickhouse.entity.operation.StartEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 启动事件数据访问接口
 * 提供启动事件的增删改查等基础操作
 */
public interface StartEventRepository extends JpaRepository<StartEvent, String> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM operation_start_event ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM operation_start_event")
    Page<StartEvent> findByPage(Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM operation_start_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:deviceId IS NULL OR :deviceId = '' OR device_id = :deviceId) AND " +
                    "(:appId IS NULL OR :appId = '' OR app_id = :appId) AND " +
                    "(:appName IS NULL OR :appName = '' OR app_name = :appName) AND " +
                    "(:packageName IS NULL OR :packageName = '' OR package_name = :packageName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM operation_start_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:deviceId IS NULL OR :deviceId = '' OR device_id = :deviceId) AND " +
                    "(:appId IS NULL OR :appId = '' OR app_id = :appId) AND " +
                    "(:appName IS NULL OR :appName = '' OR app_name = :appName) AND " +
                    "(:packageName IS NULL OR :packageName = '' OR package_name = :packageName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<StartEvent> findByPageAsc(Pageable pageable,
                                   @Param("id") String id,
                                   @Param("userId") String userId,
                                   @Param("deviceId") String deviceId,
                                   @Param("appId") String appId,
                                   @Param("appName") String appName,
                                   @Param("packageName") String packageName,
                                   @Param("netType") String netType,
                                   @Param("lanIp") String lanIp,
                                   @Param("wanIp") String wanIp,
                                   @Param("updateTimeStart") String updateTimeStart,
                                   @Param("updateTimeEnd") String updateTimeEnd,
                                   @Param("insertTimeStart") String insertTimeStart,
                                   @Param("insertTimeEnd") String insertTimeEnd);

    @Query(nativeQuery = true,
            value = "SELECT * FROM operation_start_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:deviceId IS NULL OR :deviceId = '' OR device_id = :deviceId) AND " +
                    "(:appId IS NULL OR :appId = '' OR app_id = :appId) AND " +
                    "(:appName IS NULL OR :appName = '' OR app_name = :appName) AND " +
                    "(:packageName IS NULL OR :packageName = '' OR package_name = :packageName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM operation_start_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:deviceId IS NULL OR :deviceId = '' OR device_id = :deviceId) AND " +
                    "(:appId IS NULL OR :appId = '' OR app_id = :appId) AND " +
                    "(:appName IS NULL OR :appName = '' OR app_name = :appName) AND " +
                    "(:packageName IS NULL OR :packageName = '' OR package_name = :packageName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<StartEvent> findByPageDesc(Pageable pageable,
                                    @Param("id") String id,
                                    @Param("userId") String userId,
                                    @Param("deviceId") String deviceId,
                                    @Param("appId") String appId,
                                    @Param("appName") String appName,
                                    @Param("packageName") String packageName,
                                    @Param("netType") String netType,
                                    @Param("lanIp") String lanIp,
                                    @Param("wanIp") String wanIp,
                                    @Param("updateTimeStart") String updateTimeStart,
                                    @Param("updateTimeEnd") String updateTimeEnd,
                                    @Param("insertTimeStart") String insertTimeStart,
                                    @Param("insertTimeEnd") String insertTimeEnd);

    default long countTotal() {
        return count();
    }

    @Query(value = "SELECT count(*) FROM operation_start_event WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM operation_start_event WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT id FROM operation_start_event WHERE id IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR id LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeIds(@Param("searchTerm") String searchTerm);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT app_name FROM operation_start_event WHERE app_name IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR app_name LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeAppNames(@Param("searchTerm") String searchTerm);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT package_name FROM operation_start_event WHERE package_name IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR package_name LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikePackageNames(@Param("searchTerm") String searchTerm);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT device_model FROM operation_start_event WHERE device_model IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR device_model LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeDeviceModels(@Param("searchTerm") String searchTerm);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT device_os FROM operation_start_event WHERE device_os IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR device_os LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeDeviceOses(@Param("searchTerm") String searchTerm);
}