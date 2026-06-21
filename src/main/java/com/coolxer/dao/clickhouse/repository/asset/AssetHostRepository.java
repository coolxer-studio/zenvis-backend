package com.coolxer.dao.clickhouse.repository.asset;

import com.coolxer.dao.clickhouse.entity.asset.AssetHost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 资产主机数据访问接口
 * 提供资产主机的增删改查等基础操作
 */
public interface AssetHostRepository extends JpaRepository<AssetHost, String> {

    /**
     * 分页查询资产主机列表（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_host ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_host")
    Page<AssetHost> findByPage(Pageable pageable);

    /**
     * 分页查询资产主机列表（按更新时间升序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_host WHERE " +
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
                    "(:room IS NULL OR :room = '' OR room = :room) AND " +
                    "(:cabinetNo IS NULL OR :cabinetNo = '' OR cabinet_no = :cabinetNo) AND " +
                    "(:positionNo IS NULL OR :positionNo = '' OR position_no = :positionNo) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:architecture IS NULL OR :architecture = '' OR architecture = :architecture) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_name = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:cpuModel IS NULL OR :cpuModel = '' OR cpu_model = :cpuModel) AND " +
                    "(:cpuCores IS NULL OR cpu_cores = :cpuCores) AND " +
                    "(:memorySize IS NULL OR memory_size = :memorySize) AND " +
                    "(:diskSize IS NULL OR disk_size = :diskSize) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_host WHERE " +
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
                    "(:room IS NULL OR :room = '' OR room = :room) AND " +
                    "(:cabinetNo IS NULL OR :cabinetNo = '' OR cabinet_no = :cabinetNo) AND " +
                    "(:positionNo IS NULL OR :positionNo = '' OR position_no = :positionNo) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:architecture IS NULL OR :architecture = '' OR architecture = :architecture) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_name = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:cpuModel IS NULL OR :cpuModel = '' OR cpu_model = :cpuModel) AND " +
                    "(:cpuCores IS NULL OR cpu_cores = :cpuCores) AND " +
                    "(:memorySize IS NULL OR memory_size = :memorySize) AND " +
                    "(:diskSize IS NULL OR disk_size = :diskSize) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<AssetHost> findByPageAsc(Pageable pageable,
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
                                  @Param("room") String room,
                                  @Param("cabinetNo") String cabinetNo,
                                  @Param("positionNo") String positionNo,
                                  @Param("manufacturer") String manufacturer,
                                  @Param("model") String model,
                                  @Param("architecture") String architecture,
                                  @Param("systemName") String systemName,
                                  @Param("systemVersion") String systemVersion,
                                  @Param("cpuModel") String cpuModel,
                                  @Param("cpuCores") Integer cpuCores,
                                  @Param("memorySize") Integer memorySize,
                                  @Param("diskSize") Integer diskSize,
                                  @Param("netType") String netType,
                                  @Param("lanIp") String lanIp,
                                  @Param("wanIp") String wanIp,
                                  @Param("updateTimeStart") String updateTimeStart,
                                  @Param("updateTimeEnd") String updateTimeEnd,
                                  @Param("insertTimeStart") String insertTimeStart,
                                  @Param("insertTimeEnd") String insertTimeEnd);

    /**
     * 分页查询资产主机列表（按更新时间降序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_host WHERE " +
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
                    "(:room IS NULL OR :room = '' OR room = :room) AND " +
                    "(:cabinetNo IS NULL OR :cabinetNo = '' OR cabinet_no = :cabinetNo) AND " +
                    "(:positionNo IS NULL OR :positionNo = '' OR position_no = :positionNo) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:architecture IS NULL OR :architecture = '' OR architecture = :architecture) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_name = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:cpuModel IS NULL OR :cpuModel = '' OR cpu_model = :cpuModel) AND " +
                    "(:cpuCores IS NULL OR cpu_cores = :cpuCores) AND " +
                    "(:memorySize IS NULL OR memory_size = :memorySize) AND " +
                    "(:diskSize IS NULL OR disk_size = :diskSize) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_host WHERE " +
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
                    "(:room IS NULL OR :room = '' OR room = :room) AND " +
                    "(:cabinetNo IS NULL OR :cabinetNo = '' OR cabinet_no = :cabinetNo) AND " +
                    "(:positionNo IS NULL OR :positionNo = '' OR position_no = :positionNo) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:architecture IS NULL OR :architecture = '' OR architecture = :architecture) AND " +
                    "(:systemName IS NULL OR :systemName = '' OR system_name = :systemName) AND " +
                    "(:systemVersion IS NULL OR :systemVersion = '' OR system_version = :systemVersion) AND " +
                    "(:cpuModel IS NULL OR :cpuModel = '' OR cpu_model = :cpuModel) AND " +
                    "(:cpuCores IS NULL OR cpu_cores = :cpuCores) AND " +
                    "(:memorySize IS NULL OR memory_size = :memorySize) AND " +
                    "(:diskSize IS NULL OR disk_size = :diskSize) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<AssetHost> findByPageDesc(Pageable pageable,
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
                                   @Param("room") String room,
                                   @Param("cabinetNo") String cabinetNo,
                                   @Param("positionNo") String positionNo,
                                   @Param("manufacturer") String manufacturer,
                                   @Param("model") String model,
                                   @Param("architecture") String architecture,
                                   @Param("systemName") String systemName,
                                   @Param("systemVersion") String systemVersion,
                                   @Param("cpuModel") String cpuModel,
                                   @Param("cpuCores") Integer cpuCores,
                                   @Param("memorySize") Integer memorySize,
                                   @Param("diskSize") Integer diskSize,
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
            value = "SELECT DISTINCT arrayJoin(label) as distinct_label FROM asset_host WHERE label IS NOT NULL AND label != [] order by distinct_label")
    List<String> findDistinctLabels();

    /**
     * 根据ID模糊查询资产主机
     */
    @Query(nativeQuery = true,
            value = "SELECT id FROM asset_host WHERE id IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR id LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeIds(@Param("searchTerm") String searchTerm);

    /**
     * 根据制造商名称模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT manufacturer FROM asset_host WHERE manufacturer IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR manufacturer LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeManufacturers(@Param("searchTerm") String searchTerm);

    /**
     * 根据型号模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT model FROM asset_host WHERE model IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR model LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeModels(@Param("searchTerm") String searchTerm);

    /**
     * 根据架构模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT architecture FROM asset_host WHERE architecture IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR architecture LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeArchitectures(@Param("searchTerm") String searchTerm);

    /**
     * 根据系统名称模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT system_name FROM asset_host WHERE system_name IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR system_name LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeSystemNames(@Param("searchTerm") String searchTerm);

    /**
     * 根据系统版本模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT system_version FROM asset_host WHERE system_version IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR system_version LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeSystemVersions(@Param("searchTerm") String searchTerm);

    /**
     * 根据CPU型号模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT cpu_model FROM asset_host WHERE cpu_model IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR cpu_model LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeCpuModels(@Param("searchTerm") String searchTerm);

    // 统计总数
    default long countTotal() {
        return count();
    }

    // 统计今日新增
    @Query(value = "SELECT count(*) FROM asset_host WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_host WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true, value = "select level as group_key, count(*) as count from asset_host GROUP BY level")
    List<Map<String, Object>> countByLevel();

    @Query(nativeQuery = true, value = "select risk as group_key, count(*) as count from asset_host GROUP BY risk")
    List<Map<String, Object>> countByRisk();
}
