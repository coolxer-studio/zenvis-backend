package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetAppDto;
import com.coolxer.model.business.asset.dto.AssetAppSearchDto;
import com.coolxer.model.business.asset.vo.AssetAppVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * APP应用程序资产服务接口
 * 提供APP应用程序资产的增删改查等基础操作
 */
@Service
public interface AssetAppService {

    /**
     * 新增APP应用程序资产
     *
     * @param assetAppDto APP应用程序资产信息
     * @return 是否新增成功
     */
    boolean add(AssetAppDto assetAppDto);

    /**
     * 删除APP应用程序资产
     *
     * @param id APP应用程序资产ID
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 批量删除APP应用程序资产
     *
     * @param ids APP应用程序资产ID列表
     * @return 是否删除成功
     */
    boolean deleteALL(List<String> ids);

    /**
     * 更新APP应用程序资产信息
     *
     * @param id          APP应用程序资产ID
     * @param assetAppDto 更新的APP应用程序资产信息
     * @return 是否更新成功
     */
    boolean update(String id, AssetAppDto assetAppDto);

    /**
     * 根据ID查询APP应用程序资产
     *
     * @param id APP应用程序资产ID
     * @return APP应用程序资产详细信息
     */
    AssetAppVo getOne(String id);

    /**
     * 分页查询APP应用程序资产列表
     *
     * @param assetAppSearchDto 查询条件
     * @return 分页结果
     */
    PageRowsVo<AssetAppVo> getPageList(AssetAppSearchDto assetAppSearchDto);

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
     * 获取相似的应用名称列表
     */
    List<String> getSimilarAppNames(String term);

    /**
     * 获取相似的应用版本列表
     */
    List<String> getSimilarAppVersions(String term);

    /**
     * 获取相似的应用类型列表
     */
    List<String> getSimilarAppTypes(String term);

    /**
     * 获取相似的平台列表
     */
    List<String> getSimilarPlatforms(String term);

    /**
     * 获取相似的包名列表
     */
    List<String> getSimilarPackageNames(String term);

    /**
     * 获取相似的开发者列表
     */
    List<String> getSimilarDevelopers(String term);

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