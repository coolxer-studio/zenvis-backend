package com.coolxer.dao.clickhouse.repository.asset;

import com.coolxer.dao.clickhouse.entity.asset.AssetIot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 资产IoT设备数据访问接口
 * 提供资产IoT设备的增删改查等基础操作
 */
public interface AssetIotRepository extends JpaRepository<AssetIot, String> {

    /**
     * 分页查询资产IoT设备列表（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_iot ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_iot")
    Page<AssetIot> findByPage(Pageable pageable);

    /**
     * 分页查询资产IoT设备列表（按更新时间升序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_iot WHERE " +
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
                    "(:deviceName IS NULL OR :deviceName = '' OR device_name = :deviceName) AND " +
                    "(:deviceType IS NULL OR :deviceType = '' OR device_type = :deviceType) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:serialNumber IS NULL OR :serialNumber = '' OR serial_number = :serialNumber) AND " +
                    "(:firmwareVersion IS NULL OR :firmwareVersion = '' OR firmware_version = :firmwareVersion) AND " +
                    "(:powerType IS NULL OR :powerType = '' OR power_type = :powerType) AND " +
                    "(:batteryLevel IS NULL OR battery_level = :batteryLevel) AND " +
                    "(:communicationProtocol IS NULL OR :communicationProtocol = '' OR communication_protocol = :communicationProtocol) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_iot WHERE " +
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
                    "(:deviceName IS NULL OR :deviceName = '' OR device_name = :deviceName) AND " +
                    "(:deviceType IS NULL OR :deviceType = '' OR device_type = :deviceType) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:serialNumber IS NULL OR :serialNumber = '' OR serial_number = :serialNumber) AND " +
                    "(:firmwareVersion IS NULL OR :firmwareVersion = '' OR firmware_version = :firmwareVersion) AND " +
                    "(:powerType IS NULL OR :powerType = '' OR power_type = :powerType) AND " +
                    "(:batteryLevel IS NULL OR battery_level = :batteryLevel) AND " +
                    "(:communicationProtocol IS NULL OR :communicationProtocol = '' OR communication_protocol = :communicationProtocol) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<AssetIot> findByPageAsc(Pageable pageable,
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
                                 @Param("deviceName") String deviceName,
                                 @Param("deviceType") String deviceType,
                                 @Param("manufacturer") String manufacturer,
                                 @Param("model") String model,
                                 @Param("serialNumber") String serialNumber,
                                 @Param("firmwareVersion") String firmwareVersion,
                                 @Param("powerType") String powerType,
                                 @Param("batteryLevel") Integer batteryLevel,
                                 @Param("communicationProtocol") String communicationProtocol,
                                 @Param("netType") String netType,
                                 @Param("lanIp") String lanIp,
                                 @Param("wanIp") String wanIp,
                                 @Param("updateTimeStart") String updateTimeStart,
                                 @Param("updateTimeEnd") String updateTimeEnd,
                                 @Param("insertTimeStart") String insertTimeStart,
                                 @Param("insertTimeEnd") String insertTimeEnd);

    /**
     * 分页查询资产IoT设备列表（按更新时间降序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_iot WHERE " +
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
                    "(:deviceName IS NULL OR :deviceName = '' OR device_name = :deviceName) AND " +
                    "(:deviceType IS NULL OR :deviceType = '' OR device_type = :deviceType) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:serialNumber IS NULL OR :serialNumber = '' OR serial_number = :serialNumber) AND " +
                    "(:firmwareVersion IS NULL OR :firmwareVersion = '' OR firmware_version = :firmwareVersion) AND " +
                    "(:powerType IS NULL OR :powerType = '' OR power_type = :powerType) AND " +
                    "(:batteryLevel IS NULL OR battery_level = :batteryLevel) AND " +
                    "(:communicationProtocol IS NULL OR :communicationProtocol = '' OR communication_protocol = :communicationProtocol) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_iot WHERE " +
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
                    "(:deviceName IS NULL OR :deviceName = '' OR device_name = :deviceName) AND " +
                    "(:deviceType IS NULL OR :deviceType = '' OR device_type = :deviceType) AND " +
                    "(:manufacturer IS NULL OR :manufacturer = '' OR manufacturer = :manufacturer) AND " +
                    "(:model IS NULL OR :model = '' OR model = :model) AND " +
                    "(:serialNumber IS NULL OR :serialNumber = '' OR serial_number = :serialNumber) AND " +
                    "(:firmwareVersion IS NULL OR :firmwareVersion = '' OR firmware_version = :firmwareVersion) AND " +
                    "(:powerType IS NULL OR :powerType = '' OR power_type = :powerType) AND " +
                    "(:batteryLevel IS NULL OR battery_level = :batteryLevel) AND " +
                    "(:communicationProtocol IS NULL OR :communicationProtocol = '' OR communication_protocol = :communicationProtocol) AND " +
                    "(:netType IS NULL OR :netType = '' OR net_type = :netType) AND " +
                    "(:lanIp IS NULL OR :lanIp = '' OR lan_ip = :lanIp) AND " +
                    "(:wanIp IS NULL OR :wanIp = '' OR wan_ip = :wanIp) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeEnd IS NULL OR (update_time >= :updateTimeStart AND update_time <= :updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeEnd IS NULL OR (insert_time >= :insertTimeStart AND insert_time <= :insertTimeEnd))")
    Page<AssetIot> findByPageDesc(Pageable pageable,
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
                                  @Param("deviceName") String deviceName,
                                  @Param("deviceType") String deviceType,
                                  @Param("manufacturer") String manufacturer,
                                  @Param("model") String model,
                                  @Param("serialNumber") String serialNumber,
                                  @Param("firmwareVersion") String firmwareVersion,
                                  @Param("powerType") String powerType,
                                  @Param("batteryLevel") Integer batteryLevel,
                                  @Param("communicationProtocol") String communicationProtocol,
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
            value = "SELECT DISTINCT arrayJoin(label) as distinct_label FROM asset_iot WHERE label IS NOT NULL AND label != [] order by distinct_label")
    List<String> findDistinctLabels();

    /**
     * 根据ID模糊查询资产IoT设备
     */
    @Query(nativeQuery = true,
            value = "SELECT id FROM asset_iot WHERE id IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR id LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeIds(@Param("searchTerm") String searchTerm);

    /**
     * 根据设备名称模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT device_name FROM asset_iot WHERE device_name IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR device_name LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeDeviceNames(@Param("searchTerm") String searchTerm);

    /**
     * 根据设备类型模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT device_type FROM asset_iot WHERE device_type IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR device_type LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeDeviceTypes(@Param("searchTerm") String searchTerm);

    /**
     * 根据制造商名称模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT manufacturer FROM asset_iot WHERE manufacturer IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR manufacturer LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeManufacturers(@Param("searchTerm") String searchTerm);

    /**
     * 根据型号模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT model FROM asset_iot WHERE model IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR model LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeModels(@Param("searchTerm") String searchTerm);

    /**
     * 根据固件版本模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT firmware_version FROM asset_iot WHERE firmware_version IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR firmware_version LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeFirmwareVersions(@Param("searchTerm") String searchTerm);

    /**
     * 根据通信协议模糊查询
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT communication_protocol FROM asset_iot WHERE communication_protocol IS NOT NULL AND (:searchTerm IS NULL OR :searchTerm = '' OR communication_protocol LIKE concat('%', :searchTerm, '%'))")
    List<String> findLikeCommunicationProtocols(@Param("searchTerm") String searchTerm);

    default long countTotal() {
        return count();
    }

    @Query(value = "SELECT count(*) FROM asset_iot WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_iot WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true, value = "select level as group_key, count(*) as count from asset_iot GROUP BY level")
    List<Map<String, Object>> countByLevel();

    @Query(nativeQuery = true, value = "select risk as group_key, count(*) as count from asset_iot GROUP BY risk")
    List<Map<String, Object>> countByRisk();
} 