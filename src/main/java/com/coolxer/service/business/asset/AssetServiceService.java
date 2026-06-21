package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetServiceDto;
import com.coolxer.model.business.asset.dto.AssetServiceSearchDto;
import com.coolxer.model.business.asset.vo.AssetServiceVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统服务资产服务接口
 * 提供系统服务资产的增删改查等基础操作
 */
@Service
public interface AssetServiceService {

    /**
     * 新增系统服务资产
     *
     * @param assetServiceDto 系统服务资产信息
     * @return 是否新增成功
     */
    boolean add(AssetServiceDto assetServiceDto);

    /**
     * 删除系统服务资产
     *
     * @param id 系统服务资产ID
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 批量删除系统服务资产
     *
     * @param ids 系统服务资产ID列表
     * @return 是否删除成功
     */
    boolean deleteALL(List<String> ids);

    /**
     * 更新系统服务资产信息
     *
     * @param id              系统服务资产ID
     * @param assetServiceDto 更新的系统服务资产信息
     * @return 是否更新成功
     */
    boolean update(String id, AssetServiceDto assetServiceDto);

    /**
     * 根据ID查询系统服务资产
     *
     * @param id 系统服务资产ID
     * @return 系统服务资产详细信息
     */
    AssetServiceVo getOne(String id);

    /**
     * 分页查询系统服务资产列表
     *
     * @param assetServiceSearchDto 查询条件
     * @return 分页结果
     */
    PageRowsVo<AssetServiceVo> getPageList(AssetServiceSearchDto assetServiceSearchDto);

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
     * 获取相似的服务名称列表
     */
    List<String> getSimilarServiceNames(String term);

    /**
     * 获取相似的服务版本列表
     */
    List<String> getSimilarServiceVersions(String term);

    /**
     * 获取相似的服务类型列表
     */
    List<String> getSimilarServiceTypes(String term);

    /**
     * 获取相似的运行环境列表
     */
    List<String> getSimilarRuntimeEnvironments(String term);

    /**
     * 获取相似的部署类型列表
     */
    List<String> getSimilarDeploymentTypes(String term);

    /**
     * 获取相似的进程名称列表
     */
    List<String> getSimilarProcessNames(String term);

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