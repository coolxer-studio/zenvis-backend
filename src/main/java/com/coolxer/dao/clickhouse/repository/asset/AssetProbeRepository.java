package com.coolxer.dao.clickhouse.repository.asset;

import com.coolxer.dao.clickhouse.entity.asset.AssetProbe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据探针SDK资产数据访问接口
 * 提供数据探针SDK资产的增删改查等基础操作
 */
public interface AssetProbeRepository extends JpaRepository<AssetProbe, String> {

    /**
     * 分页查询数据探针SDK资产列表（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_probe ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_probe")
    Page<AssetProbe> findByPage(Pageable pageable);

    /**
     * 分页查询数据探针SDK资产列表（按更新时间降序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_probe ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_probe")
    Page<AssetProbe> findByPageDesc(Pageable pageable);

    /**
     * 分页查询数据探针SDK资产列表（按更新时间升序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_probe WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:probeName IS NULL OR :probeName = '' OR probe_name LIKE concat('%', :probeName, '%')) AND " +
                    "(:probeVersion IS NULL OR :probeVersion = '' OR probe_version LIKE concat('%', :probeVersion, '%')) AND " +
                    "(:probeType IS NULL OR :probeType = '' OR probe_type LIKE concat('%', :probeType, '%')) AND " +
                    "(:language IS NULL OR :language = '' OR language LIKE concat('%', :language, '%')) AND " +
                    "(:framework IS NULL OR :framework = '' OR framework LIKE concat('%', :framework, '%')) AND " +
                    "(:compatibleVersions IS NULL OR :compatibleVersions = '' OR compatible_versions LIKE concat('%', :compatibleVersions, '%')) AND " +
                    "(:dataCollectionTypes IS NULL OR :dataCollectionTypes = '' OR arrayExists(x -> x = :dataCollectionTypes, data_collection_types)) AND " +
                    "(:encryptionMethod IS NULL OR :encryptionMethod = '' OR encryption_method LIKE concat('%', :encryptionMethod, '%')) AND " +
                    "(:authenticationMethod IS NULL OR :authenticationMethod = '' OR authentication_method LIKE concat('%', :authenticationMethod, '%')) AND " +
                    "(:dataTransmissionProtocol IS NULL OR :dataTransmissionProtocol = '' OR data_transmission_protocol LIKE concat('%', :dataTransmissionProtocol, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_probe WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:probeName IS NULL OR :probeName = '' OR probe_name LIKE concat('%', :probeName, '%')) AND " +
                    "(:probeVersion IS NULL OR :probeVersion = '' OR probe_version LIKE concat('%', :probeVersion, '%')) AND " +
                    "(:probeType IS NULL OR :probeType = '' OR probe_type LIKE concat('%', :probeType, '%')) AND " +
                    "(:language IS NULL OR :language = '' OR language LIKE concat('%', :language, '%')) AND " +
                    "(:framework IS NULL OR :framework = '' OR framework LIKE concat('%', :framework, '%')) AND " +
                    "(:compatibleVersions IS NULL OR :compatibleVersions = '' OR compatible_versions LIKE concat('%', :compatibleVersions, '%')) AND " +
                    "(:dataCollectionTypes IS NULL OR :dataCollectionTypes = '' OR arrayExists(x -> x = :dataCollectionTypes, data_collection_types)) AND " +
                    "(:encryptionMethod IS NULL OR :encryptionMethod = '' OR encryption_method LIKE concat('%', :encryptionMethod, '%')) AND " +
                    "(:authenticationMethod IS NULL OR :authenticationMethod = '' OR authentication_method LIKE concat('%', :authenticationMethod, '%')) AND " +
                    "(:dataTransmissionProtocol IS NULL OR :dataTransmissionProtocol = '' OR data_transmission_protocol LIKE concat('%', :dataTransmissionProtocol, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd))")
    Page<AssetProbe> findByConditionsASC(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("probeName") String probeName,
            @Param("probeVersion") String probeVersion,
            @Param("probeType") String probeType,
            @Param("language") String language,
            @Param("framework") String framework,
            @Param("compatibleVersions") String compatibleVersions,
            @Param("dataCollectionTypes") String dataCollectionTypes,
            @Param("encryptionMethod") String encryptionMethod,
            @Param("authenticationMethod") String authenticationMethod,
            @Param("dataTransmissionProtocol") String dataTransmissionProtocol,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 分页查询数据探针SDK资产列表（按更新时间降序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_probe WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:probeName IS NULL OR :probeName = '' OR probe_name LIKE concat('%', :probeName, '%')) AND " +
                    "(:probeVersion IS NULL OR :probeVersion = '' OR probe_version LIKE concat('%', :probeVersion, '%')) AND " +
                    "(:probeType IS NULL OR :probeType = '' OR probe_type LIKE concat('%', :probeType, '%')) AND " +
                    "(:language IS NULL OR :language = '' OR language LIKE concat('%', :language, '%')) AND " +
                    "(:framework IS NULL OR :framework = '' OR framework LIKE concat('%', :framework, '%')) AND " +
                    "(:compatibleVersions IS NULL OR :compatibleVersions = '' OR compatible_versions LIKE concat('%', :compatibleVersions, '%')) AND " +
                    "(:dataCollectionTypes IS NULL OR :dataCollectionTypes = '' OR arrayExists(x -> x = :dataCollectionTypes, data_collection_types)) AND " +
                    "(:encryptionMethod IS NULL OR :encryptionMethod = '' OR encryption_method LIKE concat('%', :encryptionMethod, '%')) AND " +
                    "(:authenticationMethod IS NULL OR :authenticationMethod = '' OR authentication_method LIKE concat('%', :authenticationMethod, '%')) AND " +
                    "(:dataTransmissionProtocol IS NULL OR :dataTransmissionProtocol = '' OR data_transmission_protocol LIKE concat('%', :dataTransmissionProtocol, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_probe WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:probeName IS NULL OR :probeName = '' OR probe_name LIKE concat('%', :probeName, '%')) AND " +
                    "(:probeVersion IS NULL OR :probeVersion = '' OR probe_version LIKE concat('%', :probeVersion, '%')) AND " +
                    "(:probeType IS NULL OR :probeType = '' OR probe_type LIKE concat('%', :probeType, '%')) AND " +
                    "(:language IS NULL OR :language = '' OR language LIKE concat('%', :language, '%')) AND " +
                    "(:framework IS NULL OR :framework = '' OR framework LIKE concat('%', :framework, '%')) AND " +
                    "(:compatibleVersions IS NULL OR :compatibleVersions = '' OR compatible_versions LIKE concat('%', :compatibleVersions, '%')) AND " +
                    "(:dataCollectionTypes IS NULL OR :dataCollectionTypes = '' OR arrayExists(x -> x = :dataCollectionTypes, data_collection_types)) AND " +
                    "(:encryptionMethod IS NULL OR :encryptionMethod = '' OR encryption_method LIKE concat('%', :encryptionMethod, '%')) AND " +
                    "(:authenticationMethod IS NULL OR :authenticationMethod = '' OR authentication_method LIKE concat('%', :authenticationMethod, '%')) AND " +
                    "(:dataTransmissionProtocol IS NULL OR :dataTransmissionProtocol = '' OR data_transmission_protocol LIKE concat('%', :dataTransmissionProtocol, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd))")
    Page<AssetProbe> findByConditionsDesc(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("probeName") String probeName,
            @Param("probeVersion") String probeVersion,
            @Param("probeType") String probeType,
            @Param("language") String language,
            @Param("framework") String framework,
            @Param("compatibleVersions") String compatibleVersions,
            @Param("dataCollectionTypes") String dataCollectionTypes,
            @Param("encryptionMethod") String encryptionMethod,
            @Param("authenticationMethod") String authenticationMethod,
            @Param("dataTransmissionProtocol") String dataTransmissionProtocol,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 获取所有不重复的标签列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT arrayJoin(label) FROM asset_probe")
    List<String> findDistinctLabels();

    /**
     * 获取相似的资产ID列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT id FROM asset_probe WHERE id LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeIds(@Param("term") String term);

    /**
     * 获取相似的探针名称列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT probe_name FROM asset_probe WHERE probe_name LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeProbeNames(@Param("term") String term);

    /**
     * 获取相似的探针类型列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT probe_type FROM asset_probe WHERE probe_type LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeProbeTypes(@Param("term") String term);

    /**
     * 获取相似的开发语言列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT language FROM asset_probe WHERE language LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeLanguages(@Param("term") String term);

    /**
     * 获取相似的框架列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT framework FROM asset_probe WHERE framework LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeFrameworks(@Param("term") String term);

    /**
     * 获取相似的加密方式列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT encryption_method FROM asset_probe WHERE encryption_method LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeEncryptionMethods(@Param("term") String term);

    /**
     * 获取相似的认证方式列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT authentication_method FROM asset_probe WHERE authentication_method LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeAuthenticationMethods(@Param("term") String term);

    /**
     * 获取相似的数据传输协议列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT data_transmission_protocol FROM asset_probe WHERE data_transmission_protocol LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeDataTransmissionProtocols(@Param("term") String term);

    default long countTotal() {
        return count();
    }

    @Query(value = "SELECT count(*) FROM asset_probe WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_probe WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true, value = "select level as group_key, count(*) as count from asset_probe GROUP BY level")
    List<Map<String, Object>> countByLevel();

    @Query(nativeQuery = true, value = "select risk as group_key, count(*) as count from asset_probe GROUP BY risk")
    List<Map<String, Object>> countByRisk();
} 