package com.coolxer.dao.clickhouse.repository.asset;

import com.coolxer.dao.clickhouse.entity.asset.AssetMobile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 资产移动设备数据访问接口
 * 提供资产移动设备的增删改查等基础操作
 */
public interface AssetMobileRepository extends JpaRepository<AssetMobile, String> {

    /**
     * 分页查询资产移动设备列表（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_mobile ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_mobile")
    Page<AssetMobile> findByPage(Pageable pageable);

    /**
     * 分页查询资产移动设备列表（按更新时间升序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_mobile WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:areaCode IS NULL OR :areaCode = '' OR area_code = :areaCode) AND " +
                    "(:label IS NULL OR :label = '' OR has(label, :label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:brand IS NULL OR :brand = '' OR brand = :brand) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_version = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:osName IS NULL OR :osName = '' OR os_name = :osName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_mobile WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:areaCode IS NULL OR :areaCode = '' OR area_code = :areaCode) AND " +
                    "(:label IS NULL OR :label = '' OR has(label, :label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:brand IS NULL OR :brand = '' OR brand = :brand) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_version = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:osName IS NULL OR :osName = '' OR os_name = :osName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<AssetMobile> findByPageAsc(Pageable pageable,
                                    @Param("id") String id,
                                    @Param("source") String source,
                                    @Param("type") String type,
                                    @Param("owner") String owner,
                                    @Param("status") String status,
                                    @Param("areaCode") String areaCode,
                                    @Param("label") String label,
                                    @Param("access") Boolean access,
                                    @Param("level") String level,
                                    @Param("risk") String risk,
                                    @Param("brand") String brand,
                                    @Param("model") String model,
                                    @Param("manufacturer") String manufacturer,
                                    @Param("systemName") String systemName,
                                    @Param("systemVersion") String systemVersion,
                                    @Param("osName") String osName,
                                    @Param("netType") String netType,
                                    @Param("lanIp") String lanIp,
                                    @Param("wanIp") String wanIp,
                                    @Param("updateTimeStart") String updateTimeStart,
                                    @Param("updateTimeEnd") String updateTimeEnd,
                                    @Param("insertTimeStart") String insertTimeStart,
                                    @Param("insertTimeEnd") String insertTimeEnd);

    /**
     * 分页查询资产移动设备列表（按更新时间降序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_mobile WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:areaCode IS NULL OR :areaCode = '' OR area_code = :areaCode) AND " +
                    "(:label IS NULL OR :label = '' OR has(label, :label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:brand IS NULL OR :brand = '' OR brand = :brand) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_version = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:osName IS NULL OR :osName = '' OR os_name = :osName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_mobile WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:areaCode IS NULL OR :areaCode = '' OR area_code = :areaCode) AND " +
                    "(:label IS NULL OR :label = '' OR has(label, :label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:brand IS NULL OR :brand = '' OR brand = :brand) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_version = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:osName IS NULL OR :osName = '' OR os_name = :osName) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<AssetMobile> findByPageDesc(Pageable pageable,
                                     @Param("id") String id,
                                     @Param("source") String source,
                                     @Param("type") String type,
                                     @Param("owner") String owner,
                                     @Param("status") String status,
                                     @Param("areaCode") String areaCode,
                                     @Param("label") String label,
                                     @Param("access") Boolean access,
                                     @Param("level") String level,
                                     @Param("risk") String risk,
                                     @Param("brand") String brand,
                                     @Param("model") String model,
                                     @Param("manufacturer") String manufacturer,
                                     @Param("systemName") String systemName,
                                     @Param("systemVersion") String systemVersion,
                                     @Param("osName") String osName,
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
            value = "SELECT DISTINCT arrayJoin(label) as distinct_label FROM asset_mobile WHERE label IS NOT NULL AND label != [] order by distinct_label")
    List<String> findDistinctLabels();

    /**
     * 根据ID模糊查询资产移动设备
     */
    @Query(nativeQuery = true,
            value = "SELECT id FROM asset_mobile WHERE id IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR id LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeIds(@Param("searchTerm") String searchTerm);

    /**
     * 根据品牌模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT brand FROM asset_mobile WHERE brand IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR brand LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeBrands(@Param("searchTerm") String searchTerm);

    /**
     * 根据型号代码模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT model FROM asset_mobile WHERE model IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR model LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeModels(@Param("searchTerm") String searchTerm);

    /**
     * 根据制造商名称模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT manufacturer FROM asset_mobile WHERE manufacturer IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR manufacturer LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeManufacturers(@Param("searchTerm") String searchTerm);


    /**
     * 根据系统版本模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT system_name FROM asset_mobile WHERE system_name IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR system_name LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeSystemNames(@Param("searchTerm") String searchTerm);


    /**
     * 根据系统版本模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT system_version FROM asset_mobile WHERE system_version IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR system_version LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeSystemVersions(@Param("searchTerm") String searchTerm);

    /**
     * 根据系统名称模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT os_name FROM asset_mobile WHERE os_name IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR os_name LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeOsNames(@Param("searchTerm") String searchTerm);

    default long countTotal() {
        return count();
    }

    @Query(value = "SELECT count(*) FROM asset_mobile WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_mobile WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true, value = "select level as group_key, count(*) as count from asset_mobile GROUP BY level")
    List<Map<String, Object>> countByLevel();

    @Query(nativeQuery = true, value = "select risk as group_key, count(*) as count from asset_mobile GROUP BY risk")
    List<Map<String, Object>> countByRisk();
} 