package com.coolxer.dao.clickhouse.repository.asset;

import com.coolxer.dao.clickhouse.entity.asset.AssetApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * APP应用程序资产数据访问接口
 * 提供APP应用程序资产的增删改查等基础操作
 */
public interface AssetAppRepository extends JpaRepository<AssetApp, String> {

    /**
     * 分页查询APP应用程序资产列表（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_app ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_app")
    Page<AssetApp> findByPage(Pageable pageable);

    /**
     * 分页查询APP应用程序资产列表（按更新时间降序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_app ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_app")
    Page<AssetApp> findByPageDesc(Pageable pageable);

    /**
     * 分页查询APP应用程序资产列表（按更新时间升序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_app WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:appName IS NULL OR :appName = '' OR app_name LIKE concat('%', :appName, '%')) AND " +
                    "(:appVersion IS NULL OR :appVersion = '' OR app_version LIKE concat('%', :appVersion, '%')) AND " +
                    "(:appType IS NULL OR :appType = '' OR app_type LIKE concat('%', :appType, '%')) AND " +
                    "(:platform IS NULL OR :platform = '' OR platform LIKE concat('%', :platform, '%')) AND " +
                    "(:packageName IS NULL OR :packageName = '' OR package_name LIKE concat('%', :packageName, '%')) AND " +
                    "(:developer IS NULL OR :developer = '' OR developer LIKE concat('%', :developer, '%')) AND " +
                    "(:publishTimeStart IS NULL OR :publishTimeStart = '' OR publish_time >= parseDateTimeBestEffort(:publishTimeStart)) AND " +
                    "(:publishTimeEnd IS NULL OR :publishTimeEnd = '' OR publish_time <= parseDateTimeBestEffort(:publishTimeEnd)) AND " +
                    "(:updateChannel IS NULL OR :updateChannel = '' OR update_channel LIKE concat('%', :updateChannel, '%')) AND " +
                    "(:minOsVersion IS NULL OR :minOsVersion = '' OR min_os_version LIKE concat('%', :minOsVersion, '%')) AND " +
                    "(:targetOsVersion IS NULL OR :targetOsVersion = '' OR target_os_version LIKE concat('%', :targetOsVersion, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_app WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:appName IS NULL OR :appName = '' OR app_name LIKE concat('%', :appName, '%')) AND " +
                    "(:appVersion IS NULL OR :appVersion = '' OR app_version LIKE concat('%', :appVersion, '%')) AND " +
                    "(:appType IS NULL OR :appType = '' OR app_type LIKE concat('%', :appType, '%')) AND " +
                    "(:platform IS NULL OR :platform = '' OR platform LIKE concat('%', :platform, '%')) AND " +
                    "(:packageName IS NULL OR :packageName = '' OR package_name LIKE concat('%', :packageName, '%')) AND " +
                    "(:developer IS NULL OR :developer = '' OR developer LIKE concat('%', :developer, '%')) AND " +
                    "(:publishTimeStart IS NULL OR :publishTimeStart = '' OR publish_time >= parseDateTimeBestEffort(:publishTimeStart)) AND " +
                    "(:publishTimeEnd IS NULL OR :publishTimeEnd = '' OR publish_time <= parseDateTimeBestEffort(:publishTimeEnd)) AND " +
                    "(:updateChannel IS NULL OR :updateChannel = '' OR update_channel LIKE concat('%', :updateChannel, '%')) AND " +
                    "(:minOsVersion IS NULL OR :minOsVersion = '' OR min_os_version LIKE concat('%', :minOsVersion, '%')) AND " +
                    "(:targetOsVersion IS NULL OR :targetOsVersion = '' OR target_os_version LIKE concat('%', :targetOsVersion, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd))")
    Page<AssetApp> findByConditionsASC(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("appName") String appName,
            @Param("appVersion") String appVersion,
            @Param("appType") String appType,
            @Param("platform") String platform,
            @Param("packageName") String packageName,
            @Param("developer") String developer,
            @Param("publishTimeStart") String publishTimeStart,
            @Param("publishTimeEnd") String publishTimeEnd,
            @Param("updateChannel") String updateChannel,
            @Param("minOsVersion") String minOsVersion,
            @Param("targetOsVersion") String targetOsVersion,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 分页查询APP应用程序资产列表（按更新时间降序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_app WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:appName IS NULL OR :appName = '' OR app_name LIKE concat('%', :appName, '%')) AND " +
                    "(:appVersion IS NULL OR :appVersion = '' OR app_version LIKE concat('%', :appVersion, '%')) AND " +
                    "(:appType IS NULL OR :appType = '' OR app_type LIKE concat('%', :appType, '%')) AND " +
                    "(:platform IS NULL OR :platform = '' OR platform LIKE concat('%', :platform, '%')) AND " +
                    "(:packageName IS NULL OR :packageName = '' OR package_name LIKE concat('%', :packageName, '%')) AND " +
                    "(:developer IS NULL OR :developer = '' OR developer LIKE concat('%', :developer, '%')) AND " +
                    "(:publishTimeStart IS NULL OR :publishTimeStart = '' OR publish_time >= parseDateTimeBestEffort(:publishTimeStart)) AND " +
                    "(:publishTimeEnd IS NULL OR :publishTimeEnd = '' OR publish_time <= parseDateTimeBestEffort(:publishTimeEnd)) AND " +
                    "(:updateChannel IS NULL OR :updateChannel = '' OR update_channel LIKE concat('%', :updateChannel, '%')) AND " +
                    "(:minOsVersion IS NULL OR :minOsVersion = '' OR min_os_version LIKE concat('%', :minOsVersion, '%')) AND " +
                    "(:targetOsVersion IS NULL OR :targetOsVersion = '' OR target_os_version LIKE concat('%', :targetOsVersion, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_app WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:appName IS NULL OR :appName = '' OR app_name LIKE concat('%', :appName, '%')) AND " +
                    "(:appVersion IS NULL OR :appVersion = '' OR app_version LIKE concat('%', :appVersion, '%')) AND " +
                    "(:appType IS NULL OR :appType = '' OR app_type LIKE concat('%', :appType, '%')) AND " +
                    "(:platform IS NULL OR :platform = '' OR platform LIKE concat('%', :platform, '%')) AND " +
                    "(:packageName IS NULL OR :packageName = '' OR package_name LIKE concat('%', :packageName, '%')) AND " +
                    "(:developer IS NULL OR :developer = '' OR developer LIKE concat('%', :developer, '%')) AND " +
                    "(:publishTimeStart IS NULL OR :publishTimeStart = '' OR publish_time >= parseDateTimeBestEffort(:publishTimeStart)) AND " +
                    "(:publishTimeEnd IS NULL OR :publishTimeEnd = '' OR publish_time <= parseDateTimeBestEffort(:publishTimeEnd)) AND " +
                    "(:updateChannel IS NULL OR :updateChannel = '' OR update_channel LIKE concat('%', :updateChannel, '%')) AND " +
                    "(:minOsVersion IS NULL OR :minOsVersion = '' OR min_os_version LIKE concat('%', :minOsVersion, '%')) AND " +
                    "(:targetOsVersion IS NULL OR :targetOsVersion = '' OR target_os_version LIKE concat('%', :targetOsVersion, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd))")
    Page<AssetApp> findByConditionsDesc(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("appName") String appName,
            @Param("appVersion") String appVersion,
            @Param("appType") String appType,
            @Param("platform") String platform,
            @Param("packageName") String packageName,
            @Param("developer") String developer,
            @Param("publishTimeStart") String publishTimeStart,
            @Param("publishTimeEnd") String publishTimeEnd,
            @Param("updateChannel") String updateChannel,
            @Param("minOsVersion") String minOsVersion,
            @Param("targetOsVersion") String targetOsVersion,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 获取所有不重复的标签列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT arrayJoin(label) FROM asset_app")
    List<String> findDistinctLabels();

    /**
     * 获取相似的资产ID列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT id FROM asset_app WHERE id LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeIds(@Param("term") String term);

    /**
     * 获取相似的应用名称列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT app_name FROM asset_app WHERE app_name LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeAppNames(@Param("term") String term);

    /**
     * 获取相似的应用版本列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT app_version FROM asset_app WHERE app_version LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeAppVersions(@Param("term") String term);

    /**
     * 获取相似的应用类型列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT app_type FROM asset_app WHERE app_type LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeAppTypes(@Param("term") String term);

    /**
     * 获取相似的平台列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT platform FROM asset_app WHERE platform LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikePlatforms(@Param("term") String term);

    /**
     * 获取相似的包名列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT package_name FROM asset_app WHERE package_name LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikePackageNames(@Param("term") String term);

    /**
     * 获取相似的开发者列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT developer FROM asset_app WHERE developer LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeDevelopers(@Param("term") String term);

    default long countTotal() {
        return count();
    }

    @Query(value = "SELECT count(*) FROM asset_app WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_app WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true, value = "select level as group_key, count(*) as count from asset_app GROUP BY level")
    List<Map<String, Object>> countByLevel();

    @Query(nativeQuery = true, value = "select risk as group_key, count(*) as count from asset_app GROUP BY risk")
    List<Map<String, Object>> countByRisk();
} 