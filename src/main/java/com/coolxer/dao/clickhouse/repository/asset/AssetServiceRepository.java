package com.coolxer.dao.clickhouse.repository.asset;

import com.coolxer.dao.clickhouse.entity.asset.AssetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统服务资产数据访问接口
 * 提供系统服务资产的增删改查等基础操作
 */
public interface AssetServiceRepository extends JpaRepository<AssetService, String> {

    /**
     * 分页查询系统服务资产列表（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_service ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_service")
    Page<AssetService> findByPage(Pageable pageable);

    /**
     * 分页查询系统服务资产列表（按更新时间降序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_service ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_service")
    Page<AssetService> findByPageDesc(Pageable pageable);

    /**
     * 分页查询系统服务资产列表（按更新时间升序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_service WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:serviceName IS NULL OR :serviceName = '' OR service_name LIKE concat('%', :serviceName, '%')) AND " +
                    "(:serviceVersion IS NULL OR :serviceVersion = '' OR service_version LIKE concat('%', :serviceVersion, '%')) AND " +
                    "(:serviceType IS NULL OR :serviceType = '' OR service_type LIKE concat('%', :serviceType, '%')) AND " +
                    "(:runtimeEnvironment IS NULL OR :runtimeEnvironment = '' OR runtime_environment LIKE concat('%', :runtimeEnvironment, '%')) AND " +
                    "(:deploymentType IS NULL OR :deploymentType = '' OR deployment_type LIKE concat('%', :deploymentType, '%')) AND " +
                    "(:port IS NULL OR port = :port) AND " +
                    "(:processName IS NULL OR :processName = '' OR process_name LIKE concat('%', :processName, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_service WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:serviceName IS NULL OR :serviceName = '' OR service_name LIKE concat('%', :serviceName, '%')) AND " +
                    "(:serviceVersion IS NULL OR :serviceVersion = '' OR service_version LIKE concat('%', :serviceVersion, '%')) AND " +
                    "(:serviceType IS NULL OR :serviceType = '' OR service_type LIKE concat('%', :serviceType, '%')) AND " +
                    "(:runtimeEnvironment IS NULL OR :runtimeEnvironment = '' OR runtime_environment LIKE concat('%', :runtimeEnvironment, '%')) AND " +
                    "(:deploymentType IS NULL OR :deploymentType = '' OR deployment_type LIKE concat('%', :deploymentType, '%')) AND " +
                    "(:port IS NULL OR port = :port) AND " +
                    "(:processName IS NULL OR :processName = '' OR process_name LIKE concat('%', :processName, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<AssetService> findByConditionsAsc(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("serviceName") String serviceName,
            @Param("serviceVersion") String serviceVersion,
            @Param("serviceType") String serviceType,
            @Param("runtimeEnvironment") String runtimeEnvironment,
            @Param("deploymentType") String deploymentType,
            @Param("port") Integer port,
            @Param("processName") String processName,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 分页查询系统服务资产列表（按更新时间降序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_service WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:serviceName IS NULL OR :serviceName = '' OR service_name LIKE concat('%', :serviceName, '%')) AND " +
                    "(:serviceVersion IS NULL OR :serviceVersion = '' OR service_version LIKE concat('%', :serviceVersion, '%')) AND " +
                    "(:serviceType IS NULL OR :serviceType = '' OR service_type LIKE concat('%', :serviceType, '%')) AND " +
                    "(:runtimeEnvironment IS NULL OR :runtimeEnvironment = '' OR runtime_environment LIKE concat('%', :runtimeEnvironment, '%')) AND " +
                    "(:deploymentType IS NULL OR :deploymentType = '' OR deployment_type LIKE concat('%', :deploymentType, '%')) AND " +
                    "(:port IS NULL OR port = :port) AND " +
                    "(:processName IS NULL OR :processName = '' OR process_name LIKE concat('%', :processName, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_service WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:serviceName IS NULL OR :serviceName = '' OR service_name LIKE concat('%', :serviceName, '%')) AND " +
                    "(:serviceVersion IS NULL OR :serviceVersion = '' OR service_version LIKE concat('%', :serviceVersion, '%')) AND " +
                    "(:serviceType IS NULL OR :serviceType = '' OR service_type LIKE concat('%', :serviceType, '%')) AND " +
                    "(:runtimeEnvironment IS NULL OR :runtimeEnvironment = '' OR runtime_environment LIKE concat('%', :runtimeEnvironment, '%')) AND " +
                    "(:deploymentType IS NULL OR :deploymentType = '' OR deployment_type LIKE concat('%', :deploymentType, '%')) AND " +
                    "(:port IS NULL OR port = :port) AND " +
                    "(:processName IS NULL OR :processName = '' OR process_name LIKE concat('%', :processName, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<AssetService> findByConditionsDesc(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("serviceName") String serviceName,
            @Param("serviceVersion") String serviceVersion,
            @Param("serviceType") String serviceType,
            @Param("runtimeEnvironment") String runtimeEnvironment,
            @Param("deploymentType") String deploymentType,
            @Param("port") Integer port,
            @Param("processName") String processName,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 获取所有不重复的标签列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT arrayJoin(label) FROM asset_service")
    List<String> findDistinctLabels();

    /**
     * 获取相似的资产ID列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT id FROM asset_service WHERE id LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeIds(@Param("term") String term);

    /**
     * 获取相似的服务名称列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT service_name FROM asset_service WHERE service_name LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeServiceNames(@Param("term") String term);

    /**
     * 获取相似的服务版本列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT service_version FROM asset_service WHERE service_version LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeServiceVersions(@Param("term") String term);

    /**
     * 获取相似的服务类型列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT service_type FROM asset_service WHERE service_type LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeServiceTypes(@Param("term") String term);

    /**
     * 获取相似的运行环境列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT runtime_environment FROM asset_service WHERE runtime_environment LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeRuntimeEnvironments(@Param("term") String term);

    /**
     * 获取相似的部署类型列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT deployment_type FROM asset_service WHERE deployment_type LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeDeploymentTypes(@Param("term") String term);

    /**
     * 获取相似的进程名称列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT process_name FROM asset_service WHERE process_name LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeProcessNames(@Param("term") String term);

    default long countTotal() {
        return count();
    }

    @Query(value = "SELECT count(*) FROM asset_service WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_service WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true, value = "select level as group_key, count(*) as count from asset_service GROUP BY level")
    List<Map<String, Object>> countByLevel();

    @Query(nativeQuery = true, value = "select risk as group_key, count(*) as count from asset_service GROUP BY risk")
    List<Map<String, Object>> countByRisk();
} 