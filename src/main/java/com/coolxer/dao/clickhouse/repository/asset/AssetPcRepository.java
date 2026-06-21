package com.coolxer.dao.clickhouse.repository.asset;

import com.coolxer.dao.clickhouse.entity.asset.AssetPc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 资产PC设备数据访问接口
 * 提供资产PC设备的增删改查等基础操作
 */
public interface AssetPcRepository extends JpaRepository<AssetPc, String> {

    /**
     * 分页查询资产PC设备列表（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_pc ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_pc")
    Page<AssetPc> findByPage(Pageable pageable);

    /**
     * 分页查询资产PC设备列表（按更新时间升序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_pc WHERE " +
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
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:architecture IS NULL OR :architecture = '' OR architecture = :architecture) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_name = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:cpuModel IS NULL OR :cpuModel = '' OR cpu_model = :cpuModel) AND " +
                    "(:cpuCores IS NULL OR cpu_cores = :cpuCores) AND " +
                    "(:memorySize IS NULL OR memory_size = :memorySize) AND " +
                    "(:diskSize IS NULL OR disk_size = :diskSize) AND " +
                    "(:gpuModel IS NULL OR :gpuModel = '' OR gpu_model = :gpuModel) AND " +
                    "(:gpuBrand IS NULL OR :gpuBrand = '' OR gpu_brand = :gpuBrand) AND " +
                    "(:gpuMemorySize IS NULL OR gpu_memory_size = :gpuMemorySize) AND " +
                    "(:gpuMemoryType IS NULL OR :gpuMemoryType = '' OR gpu_memory_type = :gpuMemoryType) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_pc WHERE " +
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
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:architecture IS NULL OR :architecture = '' OR architecture = :architecture) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_name = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:cpuModel IS NULL OR :cpuModel = '' OR cpu_model = :cpuModel) AND " +
                    "(:cpuCores IS NULL OR cpu_cores = :cpuCores) AND " +
                    "(:memorySize IS NULL OR memory_size = :memorySize) AND " +
                    "(:diskSize IS NULL OR disk_size = :diskSize) AND " +
                    "(:gpuModel IS NULL OR :gpuModel = '' OR gpu_model = :gpuModel) AND " +
                    "(:gpuBrand IS NULL OR :gpuBrand = '' OR gpu_brand = :gpuBrand) AND " +
                    "(:gpuMemorySize IS NULL OR gpu_memory_size = :gpuMemorySize) AND " +
                    "(:gpuMemoryType IS NULL OR :gpuMemoryType = '' OR gpu_memory_type = :gpuMemoryType) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<AssetPc> findByPageAsc(Pageable pageable,
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
                                @Param("manufacturer") String manufacturer,
                                @Param("model") String model,
                                @Param("architecture") String architecture,
                                @Param("systemName") String systemName,
                                @Param("systemVersion") String systemVersion,
                                @Param("cpuModel") String cpuModel,
                                @Param("cpuCores") Integer cpuCores,
                                @Param("memorySize") Integer memorySize,
                                @Param("diskSize") Integer diskSize,
                                @Param("gpuModel") String gpuModel,
                                @Param("gpuBrand") String gpuBrand,
                                @Param("gpuMemorySize") Integer gpuMemorySize,
                                @Param("gpuMemoryType") String gpuMemoryType,
                                @Param("netType") String netType,
                                @Param("lanIp") String lanIp,
                                @Param("wanIp") String wanIp,
                                @Param("updateTimeStart") String updateTimeStart,
                                @Param("updateTimeEnd") String updateTimeEnd,
                                @Param("insertTimeStart") String insertTimeStart,
                                @Param("insertTimeEnd") String insertTimeEnd);

    /**
     * 分页查询资产PC设备列表（按更新时间降序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_pc WHERE " +
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
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:architecture IS NULL OR :architecture = '' OR architecture = :architecture) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_name = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:cpuModel IS NULL OR :cpuModel = '' OR cpu_model = :cpuModel) AND " +
                    "(:cpuCores IS NULL OR cpu_cores = :cpuCores) AND " +
                    "(:memorySize IS NULL OR memory_size = :memorySize) AND " +
                    "(:diskSize IS NULL OR disk_size = :diskSize) AND " +
                    "(:gpuModel IS NULL OR :gpuModel = '' OR gpu_model = :gpuModel) AND " +
                    "(:gpuBrand IS NULL OR :gpuBrand = '' OR gpu_brand = :gpuBrand) AND " +
                    "(:gpuMemorySize IS NULL OR gpu_memory_size = :gpuMemorySize) AND " +
                    "(:gpuMemoryType IS NULL OR :gpuMemoryType = '' OR gpu_memory_type = :gpuMemoryType) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_pc WHERE " +
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
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:architecture IS NULL OR :architecture = '' OR architecture = :architecture) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_name = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:cpuModel IS NULL OR :cpuModel = '' OR cpu_model = :cpuModel) AND " +
                    "(:cpuCores IS NULL OR cpu_cores = :cpuCores) AND " +
                    "(:memorySize IS NULL OR memory_size = :memorySize) AND " +
                    "(:diskSize IS NULL OR disk_size = :diskSize) AND " +
                    "(:gpuModel IS NULL OR :gpuModel = '' OR gpu_model = :gpuModel) AND " +
                    "(:gpuBrand IS NULL OR :gpuBrand = '' OR gpu_brand = :gpuBrand) AND " +
                    "(:gpuMemorySize IS NULL OR gpu_memory_size = :gpuMemorySize) AND " +
                    "(:gpuMemoryType IS NULL OR :gpuMemoryType = '' OR gpu_memory_type = :gpuMemoryType) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<AssetPc> findByPageDesc(Pageable pageable,
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
                                 @Param("manufacturer") String manufacturer,
                                 @Param("model") String model,
                                 @Param("architecture") String architecture,
                                 @Param("systemName") String systemName,
                                 @Param("systemVersion") String systemVersion,
                                 @Param("cpuModel") String cpuModel,
                                 @Param("cpuCores") Integer cpuCores,
                                 @Param("memorySize") Integer memorySize,
                                 @Param("diskSize") Integer diskSize,
                                 @Param("gpuModel") String gpuModel,
                                 @Param("gpuBrand") String gpuBrand,
                                 @Param("gpuMemorySize") Integer gpuMemorySize,
                                 @Param("gpuMemoryType") String gpuMemoryType,
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
            value = "SELECT DISTINCT arrayJoin(label) as distinct_label FROM asset_pc WHERE label IS NOT NULL AND label != [] order by distinct_label")
    List<String> findDistinctLabels();

    /**
     * 根据ID模糊查询资产PC设备
     */
    @Query(nativeQuery = true,
            value = "SELECT id FROM asset_pc WHERE id IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR id LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeIds(@Param("searchTerm") String searchTerm);

    /**
     * 根据制造商名称模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT manufacturer FROM asset_pc WHERE manufacturer IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR manufacturer LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeManufacturers(@Param("searchTerm") String searchTerm);

    /**
     * 根据型号模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT model FROM asset_pc WHERE model IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR model LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeModels(@Param("searchTerm") String searchTerm);

    /**
     * 根据架构模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT architecture FROM asset_pc WHERE architecture IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR architecture LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeArchitectures(@Param("searchTerm") String searchTerm);

    /**
     * 根据系统名称模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT system_name FROM asset_pc WHERE system_name IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR system_name LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeSystemNames(@Param("searchTerm") String searchTerm);

    /**
     * 根据系统版本模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT system_version FROM asset_pc WHERE system_version IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR system_version LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeSystemVersions(@Param("searchTerm") String searchTerm);

    /**
     * 根据CPU型号模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT cpu_model FROM asset_pc WHERE cpu_model IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR cpu_model LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeCpuModels(@Param("searchTerm") String searchTerm);

    /**
     * 根据显卡型号模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT gpu_model FROM asset_pc WHERE gpu_model IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR gpu_model LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeGpuModels(@Param("searchTerm") String searchTerm);

    default long countTotal() {
        return count();
    }

    @Query(value = "SELECT count(*) FROM asset_pc WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_pc WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true, value = "select level as group_key, count(*) as count from asset_pc GROUP BY level")
    List<Map<String, Object>> countByLevel();

    @Query(nativeQuery = true, value = "select risk as group_key, count(*) as count from asset_pc GROUP BY risk")
    List<Map<String, Object>> countByRisk();
} 