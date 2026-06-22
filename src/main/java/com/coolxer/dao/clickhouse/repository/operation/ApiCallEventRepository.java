package com.coolxer.dao.clickhouse.repository.operation;

import com.coolxer.dao.clickhouse.entity.operation.ApiCallEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * API调用事件数据访问接口
 * 提供API调用事件的增删改查等基础操作
 */
public interface ApiCallEventRepository extends JpaRepository<ApiCallEvent, String> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM operation_api_call_event ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM operation_api_call_event")
    Page<ApiCallEvent> findByPage(Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM operation_api_call_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:caller IS NULL OR :caller = '' OR caller = :caller) AND " +
                    "(:callee IS NULL OR :callee = '' OR callee = :callee) AND " +
                    "(:functionName IS NULL OR :functionName = '' OR function_name = :functionName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM operation_api_call_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:caller IS NULL OR :caller = '' OR caller = :caller) AND " +
                    "(:callee IS NULL OR :callee = '' OR callee = :callee) AND " +
                    "(:functionName IS NULL OR :functionName = '' OR function_name = :functionName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<ApiCallEvent> findByPageAsc(Pageable pageable,
                                     @Param("id") String id,
                                     @Param("userId") String userId,
                                     @Param("startId") String startId,
                                     @Param("caller") String caller,
                                     @Param("callee") String callee,
                                     @Param("functionName") String functionName,
                                     @Param("netType") String netType,
                                     @Param("lanIp") String lanIp,
                                     @Param("wanIp") String wanIp,
                                     @Param("updateTimeStart") String updateTimeStart,
                                     @Param("updateTimeEnd") String updateTimeEnd,
                                     @Param("insertTimeStart") String insertTimeStart,
                                     @Param("insertTimeEnd") String insertTimeEnd);

    @Query(nativeQuery = true,
            value = "SELECT * FROM operation_api_call_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:caller IS NULL OR :caller = '' OR caller = :caller) AND " +
                    "(:callee IS NULL OR :callee = '' OR callee = :callee) AND " +
                    "(:functionName IS NULL OR :functionName = '' OR function_name = :functionName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM operation_api_call_event WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:userId IS NULL OR :userId = '' OR user_id = :userId) AND " +
                    "(:startId IS NULL OR :startId = '' OR start_id = :startId) AND " +
                    "(:caller IS NULL OR :caller = '' OR caller = :caller) AND " +
                    "(:callee IS NULL OR :callee = '' OR callee = :callee) AND " +
                    "(:functionName IS NULL OR :functionName = '' OR function_name = :functionName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<ApiCallEvent> findByPageDesc(Pageable pageable,
                                      @Param("id") String id,
                                      @Param("userId") String userId,
                                      @Param("startId") String startId,
                                      @Param("caller") String caller,
                                      @Param("callee") String callee,
                                      @Param("functionName") String functionName,
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

    @Query(value = "SELECT count(*) FROM operation_api_call_event WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM operation_api_call_event WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT id FROM operation_api_call_event WHERE id IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR id LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeIds(@Param("searchTerm") String searchTerm);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT caller FROM operation_api_call_event WHERE caller IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR caller LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeCallers(@Param("searchTerm") String searchTerm);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT callee FROM operation_api_call_event WHERE callee IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR callee LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeCallees(@Param("searchTerm") String searchTerm);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT function_name FROM operation_api_call_event WHERE function_name IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR function_name LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeFunctionNames(@Param("searchTerm") String searchTerm);
}