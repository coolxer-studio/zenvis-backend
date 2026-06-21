package com.coolxer.dao.clickhouse.repository.asset;

import com.coolxer.dao.clickhouse.entity.asset.AssetApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * RESTful API接口资产数据访问接口
 * 提供RESTful API接口资产的增删改查等基础操作
 */
public interface AssetApiRepository extends JpaRepository<AssetApi, String> {

    /**
     * 分页查询API接口资产列表（按更新时间升序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_api ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_api")
    Page<AssetApi> findByPage(Pageable pageable);

    /**
     * 分页查询API接口资产列表（按更新时间降序）
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_api ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_api")
    Page<AssetApi> findByPageDesc(Pageable pageable);

    /**
     * 分页查询API接口资产列表（按更新时间升序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_api WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:apiName IS NULL OR :apiName = '' OR api_name LIKE concat('%', :apiName, '%')) AND " +
                    "(:apiVersion IS NULL OR :apiVersion = '' OR api_version LIKE concat('%', :apiVersion, '%')) AND " +
                    "(:apiPath IS NULL OR :apiPath = '' OR api_path LIKE concat('%', :apiPath, '%')) AND " +
                    "(:httpMethod IS NULL OR :httpMethod = '' OR http_method LIKE concat('%', :httpMethod, '%')) AND " +
                    "(:contentType IS NULL OR :contentType = '' OR content_type LIKE concat('%', :contentType, '%')) AND " +
                    "(:authenticationType IS NULL OR :authenticationType = '' OR authentication_type LIKE concat('%', :authenticationType, '%')) AND " +
                    "(:serviceId IS NULL OR :serviceId = '' OR service_id = :serviceId) AND " +
                    "(:isDeprecated IS NULL OR is_deprecated = :isDeprecated) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd)) " +
                    "ORDER BY update_time ASC",
            countQuery = "SELECT count(*) FROM asset_api WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:apiName IS NULL OR :apiName = '' OR api_name LIKE concat('%', :apiName, '%')) AND " +
                    "(:apiVersion IS NULL OR :apiVersion = '' OR api_version LIKE concat('%', :apiVersion, '%')) AND " +
                    "(:apiPath IS NULL OR :apiPath = '' OR api_path LIKE concat('%', :apiPath, '%')) AND " +
                    "(:httpMethod IS NULL OR :httpMethod = '' OR http_method LIKE concat('%', :httpMethod, '%')) AND " +
                    "(:contentType IS NULL OR :contentType = '' OR content_type LIKE concat('%', :contentType, '%')) AND " +
                    "(:authenticationType IS NULL OR :authenticationType = '' OR authentication_type LIKE concat('%', :authenticationType, '%')) AND " +
                    "(:serviceId IS NULL OR :serviceId = '' OR service_id = :serviceId) AND " +
                    "(:isDeprecated IS NULL OR is_deprecated = :isDeprecated) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd))")
    Page<AssetApi> findByConditionsAsc(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("apiName") String apiName,
            @Param("apiVersion") String apiVersion,
            @Param("apiPath") String apiPath,
            @Param("httpMethod") String httpMethod,
            @Param("contentType") String contentType,
            @Param("authenticationType") String authenticationType,
            @Param("serviceId") String serviceId,
            @Param("isDeprecated") Boolean isDeprecated,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 分页查询API接口资产列表（按更新时间降序）
     * 支持多条件组合查询
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM asset_api WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:apiName IS NULL OR :apiName = '' OR api_name LIKE concat('%', :apiName, '%')) AND " +
                    "(:apiVersion IS NULL OR :apiVersion = '' OR api_version LIKE concat('%', :apiVersion, '%')) AND " +
                    "(:apiPath IS NULL OR :apiPath = '' OR api_path LIKE concat('%', :apiPath, '%')) AND " +
                    "(:httpMethod IS NULL OR :httpMethod = '' OR http_method LIKE concat('%', :httpMethod, '%')) AND " +
                    "(:contentType IS NULL OR :contentType = '' OR content_type LIKE concat('%', :contentType, '%')) AND " +
                    "(:authenticationType IS NULL OR :authenticationType = '' OR authentication_type LIKE concat('%', :authenticationType, '%')) AND " +
                    "(:serviceId IS NULL OR :serviceId = '' OR service_id = :serviceId) AND " +
                    "(:isDeprecated IS NULL OR is_deprecated = :isDeprecated) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd)) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM asset_api WHERE " +
                    "(:id IS NULL OR :id = '' OR id = :id) AND " +
                    "(:source IS NULL OR :source = '' OR source = :source) AND " +
                    "(:type IS NULL OR :type = '' OR type = :type) AND " +
                    "(:owner IS NULL OR :owner = '' OR owner = :owner) AND " +
                    "(:status IS NULL OR :status = '' OR status = :status) AND " +
                    "(:label IS NULL OR :label = '' OR arrayExists(x -> x = :label, label)) AND " +
                    "(:access IS NULL OR access = :access) AND " +
                    "(:level IS NULL OR :level = '' OR level = :level) AND " +
                    "(:risk IS NULL OR :risk = '' OR risk = :risk) AND " +
                    "(:apiName IS NULL OR :apiName = '' OR api_name LIKE concat('%', :apiName, '%')) AND " +
                    "(:apiVersion IS NULL OR :apiVersion = '' OR api_version LIKE concat('%', :apiVersion, '%')) AND " +
                    "(:apiPath IS NULL OR :apiPath = '' OR api_path LIKE concat('%', :apiPath, '%')) AND " +
                    "(:httpMethod IS NULL OR :httpMethod = '' OR http_method LIKE concat('%', :httpMethod, '%')) AND " +
                    "(:contentType IS NULL OR :contentType = '' OR content_type LIKE concat('%', :contentType, '%')) AND " +
                    "(:authenticationType IS NULL OR :authenticationType = '' OR authentication_type LIKE concat('%', :authenticationType, '%')) AND " +
                    "(:serviceId IS NULL OR :serviceId = '' OR service_id = :serviceId) AND " +
                    "(:isDeprecated IS NULL OR is_deprecated = :isDeprecated) AND " +
                    "(:updateTimeStart IS NULL OR :updateTimeStart = '' OR update_time >= parseDateTimeBestEffort(:updateTimeStart)) AND " +
                    "(:updateTimeEnd IS NULL OR :updateTimeEnd = '' OR update_time <= parseDateTimeBestEffort(:updateTimeEnd)) AND " +
                    "(:insertTimeStart IS NULL OR :insertTimeStart = '' OR insert_time >= parseDateTimeBestEffort(:insertTimeStart)) AND " +
                    "(:insertTimeEnd IS NULL OR :insertTimeEnd = '' OR insert_time <= parseDateTimeBestEffort(:insertTimeEnd))")
    Page<AssetApi> findByConditionsDesc(
            @Param("id") String id,
            @Param("source") String source,
            @Param("type") String type,
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("label") String label,
            @Param("access") Boolean access,
            @Param("level") String level,
            @Param("risk") String risk,
            @Param("apiName") String apiName,
            @Param("apiVersion") String apiVersion,
            @Param("apiPath") String apiPath,
            @Param("httpMethod") String httpMethod,
            @Param("contentType") String contentType,
            @Param("authenticationType") String authenticationType,
            @Param("serviceId") String serviceId,
            @Param("isDeprecated") Boolean isDeprecated,
            @Param("updateTimeStart") String updateTimeStart,
            @Param("updateTimeEnd") String updateTimeEnd,
            @Param("insertTimeStart") String insertTimeStart,
            @Param("insertTimeEnd") String insertTimeEnd,
            Pageable pageable);

    /**
     * 获取所有不重复的标签列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT arrayJoin(label) FROM asset_api")
    List<String> findDistinctLabels();

    /**
     * 获取相似的资产ID列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT id FROM asset_api WHERE id LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeIds(@Param("term") String term);

    /**
     * 获取相似的API名称列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT api_name FROM asset_api WHERE api_name LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeApiNames(@Param("term") String term);

    /**
     * 获取相似的API版本列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT api_version FROM asset_api WHERE api_version LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeApiVersions(@Param("term") String term);

    /**
     * 获取相似的API路径列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT api_path FROM asset_api WHERE api_path LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeApiPaths(@Param("term") String term);

    /**
     * 获取相似的HTTP方法列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT http_method FROM asset_api WHERE http_method LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeHttpMethods(@Param("term") String term);

    /**
     * 获取相似的内容类型列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT content_type FROM asset_api WHERE content_type LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeContentTypes(@Param("term") String term);

    /**
     * 获取相似的认证类型列表
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT authentication_type FROM asset_api WHERE authentication_type LIKE concat('%', :term, '%') LIMIT 10")
    List<String> findLikeAuthenticationTypes(@Param("term") String term);

    /**
     * 根据服务ID查询API接口列表
     */
    @Query(nativeQuery = true, value = "SELECT * FROM asset_api WHERE service_id = :serviceId")
    List<AssetApi> findByServiceId(@Param("serviceId") String serviceId);

    default long countTotal() {
        return count();
    }

    @Query(value = "SELECT count(*) FROM asset_api WHERE toDate(insert_time) = toDate(now())", nativeQuery = true)
    long countTodayIncrease();

    @Query(nativeQuery = true, value = "SELECT toStartOfDay(insert_time) AS group_key, COUNT(*) AS count FROM asset_api WHERE insert_time >= today() - INTERVAL 1 WEEK GROUP BY toStartOfDay(insert_time) ORDER BY group_key")
    List<Map<String, Object>> countByDateOfWeek();

    @Query(nativeQuery = true, value = "select level as group_key, count(*) as count from asset_api GROUP BY level")
    List<Map<String, Object>> countByLevel();

    @Query(nativeQuery = true, value = "select risk as group_key, count(*) as count from asset_api GROUP BY risk")
    List<Map<String, Object>> countByRisk();
} 