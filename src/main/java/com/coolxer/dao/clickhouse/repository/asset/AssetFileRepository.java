package com.coolxer.dao.clickhouse.repository.asset;

import com.coolxer.dao.clickhouse.entity.asset.AssetFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 文件资产数据访问接口
 * 提供文件资产的增删改查等基础操作
 */
public interface AssetFileRepository extends JpaRepository<AssetFile, String> {

    /**
     * 分页查询文件资产列表（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_file ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_file")
    Page<AssetFile> findByPage(Pageable pageable);

    /**
     * 分页查询文件资产列表（按更新时间降序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_file ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_file")
    Page<AssetFile> findByPageDesc(Pageable pageable);

    /**
     * 分页查询文件资产列表（按更新时间升序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_file WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:fileName IS NULL OR :fileName = '' OR file_name LIKE concat('%', :fileName, '%')) AND " +
                    "(:fileType IS NULL OR :fileType = '' OR file_type LIKE concat('%', :fileType, '%')) AND " +
                    "(:fileFormat IS NULL OR :fileFormat = '' OR file_format LIKE concat('%', :fileFormat, '%')) AND " +
                    "(:filePath IS NULL OR :filePath = '' OR file_path LIKE concat('%', :filePath, '%')) AND " +
                    "(:fileSize IS NULL OR file_size = :fileSize) AND " +
                    "(:creationTimeStart IS NULL OR :creationTimeStart = 0 OR creation_time >= :creationTimeStart) AND " +
                    "(:creationTimeEnd IS NULL OR :creationTimeEnd = 0 OR creation_time <= :creationTimeEnd) AND " +
                    "(:modificationTimeStart IS NULL OR :modificationTimeStart = 0 OR modification_time >= :modificationTimeStart) AND " +
                    "(:modificationTimeEnd IS NULL OR :modificationTimeEnd = 0 OR modification_time <= :modificationTimeEnd) AND " +
                    "(:sourceSystem IS NULL OR :sourceSystem = '' OR source_system LIKE concat('%', :sourceSystem, '%')) AND " +
                    "(:fileOwner IS NULL OR :fileOwner = '' OR file_owner LIKE concat('%', :fileOwner, '%')) AND " +
                    "(:permissions IS NULL OR :permissions = '' OR permissions LIKE concat('%', :permissions, '%')) AND " +
                    "(:isEncrypted IS NULL OR is_encrypted = :isEncrypted) AND " +
                    "(:isCompressed IS NULL OR is_compressed = :isCompressed) AND " +
                    "(:fileHash IS NULL OR :fileHash = '' OR file_hash LIKE concat('%', :fileHash, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_file WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:fileName IS NULL OR :fileName = '' OR file_name LIKE concat('%', :fileName, '%')) AND " +
                    "(:fileType IS NULL OR :fileType = '' OR file_type LIKE concat('%', :fileType, '%')) AND " +
                    "(:fileFormat IS NULL OR :fileFormat = '' OR file_format LIKE concat('%', :fileFormat, '%')) AND " +
                    "(:filePath IS NULL OR :filePath = '' OR file_path LIKE concat('%', :filePath, '%')) AND " +
                    "(:fileSize IS NULL OR file_size = :fileSize) AND " +
                    "(:creationTimeStart IS NULL OR :creationTimeStart = 0 OR creation_time >= :creationTimeStart) AND " +
                    "(:creationTimeEnd IS NULL OR :creationTimeEnd = 0 OR creation_time <= :creationTimeEnd) AND " +
                    "(:modificationTimeStart IS NULL OR :modificationTimeStart = 0 OR modification_time >= :modificationTimeStart) AND " +
                    "(:modificationTimeEnd IS NULL OR :modificationTimeEnd = 0 OR modification_time <= :modificationTimeEnd) AND " +
                    "(:sourceSystem IS NULL OR :sourceSystem = '' OR source_system LIKE concat('%', :sourceSystem, '%')) AND " +
                    "(:fileOwner IS NULL OR :fileOwner = '' OR file_owner LIKE concat('%', :fileOwner, '%')) AND " +
                    "(:permissions IS NULL OR :permissions = '' OR permissions LIKE concat('%', :permissions, '%')) AND " +
                    "(:isEncrypted IS NULL OR is_encrypted = :isEncrypted) AND " +
                    "(:isCompressed IS NULL OR is_compressed = :isCompressed) AND " +
                    "(:fileHash IS NULL OR :fileHash = '' OR file_hash LIKE concat('%', :fileHash, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd))")
    Page<AssetFile> findByConditionsASC(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("fileName") String fileName,
            @Param("fileType") String fileType,
            @Param("fileFormat") String fileFormat,
            @Param("filePath") String filePath,
            @Param("fileSize") Long fileSize,
            @Param("creationTimeStart") Long creationTimeStart,
            @Param("creationTimeEnd") Long creationTimeEnd,
            @Param("modificationTimeStart") Long modificationTimeStart,
            @Param("modificationTimeEnd") Long modificationTimeEnd,
            @Param("sourceSystem") String sourceSystem,
            @Param("fileOwner") String fileOwner,
            @Param("permissions") String permissions,
            @Param("isEncrypted") Boolean isEncrypted,
            @Param("isCompressed") Boolean isCompressed,
            @Param("fileHash") String fileHash,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 分页查询文件资产列表（按更新时间降序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_file WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:fileName IS NULL OR :fileName = '' OR file_name LIKE concat('%', :fileName, '%')) AND " +
                    "(:fileType IS NULL OR :fileType = '' OR file_type LIKE concat('%', :fileType, '%')) AND " +
                    "(:fileFormat IS NULL OR :fileFormat = '' OR file_format LIKE concat('%', :fileFormat, '%')) AND " +
                    "(:filePath IS NULL OR :filePath = '' OR file_path LIKE concat('%', :filePath, '%')) AND " +
                    "(:fileSize IS NULL OR file_size = :fileSize) AND " +
                    "(:creationTimeStart IS NULL OR :creationTimeStart = 0 OR creation_time >= :creationTimeStart) AND " +
                    "(:creationTimeEnd IS NULL OR :creationTimeEnd = 0 OR creation_time <= :creationTimeEnd) AND " +
                    "(:modificationTimeStart IS NULL OR :modificationTimeStart = 0 OR modification_time >= :modificationTimeStart) AND " +
                    "(:modificationTimeEnd IS NULL OR :modificationTimeEnd = 0 OR modification_time <= :modificationTimeEnd) AND " +
                    "(:sourceSystem IS NULL OR :sourceSystem = '' OR source_system LIKE concat('%', :sourceSystem, '%')) AND " +
                    "(:fileOwner IS NULL OR :fileOwner = '' OR file_owner LIKE concat('%', :fileOwner, '%')) AND " +
                    "(:permissions IS NULL OR :permissions = '' OR permissions LIKE concat('%', :permissions, '%')) AND " +
                    "(:isEncrypted IS NULL OR is_encrypted = :isEncrypted) AND " +
                    "(:isCompressed IS NULL OR is_compressed = :isCompressed) AND " +
                    "(:fileHash IS NULL OR :fileHash = '' OR file_hash LIKE concat('%', :fileHash, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_file WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:fileName IS NULL OR :fileName = '' OR file_name LIKE concat('%', :fileName, '%')) AND " +
                    "(:fileType IS NULL OR :fileType = '' OR file_type LIKE concat('%', :fileType, '%')) AND " +
                    "(:fileFormat IS NULL OR :fileFormat = '' OR file_format LIKE concat('%', :fileFormat, '%')) AND " +
                    "(:filePath IS NULL OR :filePath = '' OR file_path LIKE concat('%', :filePath, '%')) AND " +
                    "(:fileSize IS NULL OR file_size = :fileSize) AND " +
                    "(:creationTimeStart IS NULL OR :creationTimeStart = 0 OR creation_time >= :creationTimeStart) AND " +
                    "(:creationTimeEnd IS NULL OR :creationTimeEnd = 0 OR creation_time <= :creationTimeEnd) AND " +
                    "(:modificationTimeStart IS NULL OR :modificationTimeStart = 0 OR modification_time >= :modificationTimeStart) AND " +
                    "(:modificationTimeEnd IS NULL OR :modificationTimeEnd = 0 OR modification_time <= :modificationTimeEnd) AND " +
                    "(:sourceSystem IS NULL OR :sourceSystem = '' OR source_system LIKE concat('%', :sourceSystem, '%')) AND " +
                    "(:fileOwner IS NULL OR :fileOwner = '' OR file_owner LIKE concat('%', :fileOwner, '%')) AND " +
                    "(:permissions IS NULL OR :permissions = '' OR permissions LIKE concat('%', :permissions, '%')) AND " +
                    "(:isEncrypted IS NULL OR is_encrypted = :isEncrypted) AND " +
                    "(:isCompressed IS NULL OR is_compressed = :isCompressed) AND " +
                    "(:fileHash IS NULL OR :fileHash = '' OR file_hash LIKE concat('%', :fileHash, '%')) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd))")
    Page<AssetFile> findByConditionsDesc(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("fileName") String fileName,
            @Param("fileType") String fileType,
            @Param("fileFormat") String fileFormat,
            @Param("filePath") String filePath,
            @Param("fileSize") Long fileSize,
            @Param("creationTimeStart") Long creationTimeStart,
            @Param("creationTimeEnd") Long creationTimeEnd,
            @Param("modificationTimeStart") Long modificationTimeStart,
            @Param("modificationTimeEnd") Long modificationTimeEnd,
            @Param("sourceSystem") String sourceSystem,
            @Param("fileOwner") String fileOwner,
            @Param("permissions") String permissions,
            @Param("isEncrypted") Boolean isEncrypted,
            @Param("isCompressed") Boolean isCompressed,
            @Param("fileHash") String fileHash,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 获取所有不重复的标签列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT arrayJoin(label) FROM asset_file")
    List<String> findDistinctLabels();

    /**
     * 获取相似的资产ID列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT id FROM asset_file WHERE id LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeIds(@Param("term") String term);

    /**
     * 获取相似的文件名称列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT file_name FROM asset_file WHERE file_name LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeFileNames(@Param("term") String term);

    /**
     * 获取相似的文件类型列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT file_type FROM asset_file WHERE file_type LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeFileTypes(@Param("term") String term);

    /**
     * 获取相似的文件格式列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT file_format FROM asset_file WHERE file_format LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeFileFormats(@Param("term") String term);

    /**
     * 获取相似的文件路径列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT file_path FROM asset_file WHERE file_path LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeFilePaths(@Param("term") String term);

    /**
     * 获取相似的源系统列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT source_system FROM asset_file WHERE source_system LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeSourceSystems(@Param("term") String term);

    /**
     * 获取相似的文件所有者列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT file_owner FROM asset_file WHERE file_owner LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeFileOwners(@Param("term") String term);

    default long countTotal() {
        return count();
    }

    @Query(value = "SELECT count(*) FROM asset_file WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_file WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true, value = "select level as group_key, count(*) as count from asset_file GROUP BY level")
    List<Map<String, Object>> countByLevel();

    @Query(nativeQuery = true, value = "select risk as group_key, count(*) as count from asset_file GROUP BY risk")
    List<Map<String, Object>> countByRisk();
} 