package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetApiDto;
import com.coolxer.model.business.asset.dto.AssetApiSearchDto;
import com.coolxer.model.business.asset.vo.AssetApiVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * RESTful API接口资产服务接口
 * 提供RESTful API接口资产的增删改查等基础操作
 */
@Service
public interface AssetApiService {

    /**
     * 新增API接口资产
     *
     * @param assetApiDto API接口资产信息
     * @return 是否新增成功
     */
    boolean add(AssetApiDto assetApiDto);

    /**
     * 删除API接口资产
     *
     * @param id API接口资产ID
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 批量删除API接口资产
     *
     * @param ids API接口资产ID列表
     * @return 是否删除成功
     */
    boolean deleteALL(List<String> ids);

    /**
     * 更新API接口资产信息
     *
     * @param id          API接口资产ID
     * @param assetApiDto 更新的API接口资产信息
     * @return 是否更新成功
     */
    boolean update(String id, AssetApiDto assetApiDto);

    /**
     * 根据ID查询API接口资产
     *
     * @param id API接口资产ID
     * @return API接口资产详细信息
     */
    AssetApiVo getOne(String id);

    /**
     * 分页查询API接口资产列表
     *
     * @param assetApiSearchDto 查询条件
     * @return 分页结果
     */
    PageRowsVo<AssetApiVo> getPageList(AssetApiSearchDto assetApiSearchDto);

    /**
     * 获取所有不重复的标签列表
     *
     * @return 标签列表
     */
    List<String> getDistinctLabels();

    /**
     * 获取相似的资产ID列表
     */
    List<String> getSimilarIds(String term);

    /**
     * 获取相似的API名称列表
     */
    List<String> getSimilarApiNames(String term);

    /**
     * 获取相似的API版本列表
     */
    List<String> getSimilarApiVersions(String term);

    /**
     * 获取相似的API路径列表
     */
    List<String> getSimilarApiPaths(String term);

    /**
     * 获取相似的HTTP方法列表
     */
    List<String> getSimilarHttpMethods(String term);

    /**
     * 获取相似的内容类型列表
     */
    List<String> getSimilarContentTypes(String term);

    /**
     * 获取相似的认证类型列表
     */
    List<String> getSimilarAuthenticationTypes(String term);

    /**
     * 根据服务ID查询API接口列表
     */
    List<AssetApiVo> getByServiceId(String serviceId);

    /**
     * 统计总量
     */
    long countTotal();

    /**
     * 统计今日新增
     */
    long countIncrease();

    /**
     * 统计本周资产数量
     */
    Map<String, Object> getStatisticsByDateOfWeek();

    /**
     * 统计本周资产风险数量
     */
    Map<String, Object> getStatisticsByRisk();

    /**
     * 统计本周资产等级数量
     */
    Map<String, Object> getStatisticsByLevel();

} 