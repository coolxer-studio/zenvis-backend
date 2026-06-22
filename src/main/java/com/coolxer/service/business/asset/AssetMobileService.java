package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetMobileDto;
import com.coolxer.model.business.asset.dto.AssetMobileSearchDto;
import com.coolxer.model.business.asset.vo.AssetMobileVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 资产移动设备服务接口
 * 提供资产移动设备的增删改查等基础操作
 */
@Service
public interface AssetMobileService {

    /**
     * 新增资产移动设备
     *
     * @param assetMobileDto 资产移动设备信息
     * @return 是否新增成功
     */
    boolean add(AssetMobileDto assetMobileDto);

    /**
     * 删除资产移动设备
     *
     * @param id 资产移动设备ID
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 批量删除资产移动设备
     *
     * @param ids 资产移动设备ID列表
     * @return 是否删除成功
     */
    boolean deleteALL(List<String> ids);

    /**
     * 更新资产移动设备信息
     *
     * @param id             资产移动设备ID
     * @param assetMobileDto 更新的资产移动设备信息
     * @return 是否更新成功
     */
    boolean update(String id, AssetMobileDto assetMobileDto);

    /**
     * 根据ID查询资产移动设备
     *
     * @param id 资产移动设备ID
     * @return 资产移动设备详细信息
     */
    AssetMobileVo getOne(String id);

    /**
     * 分页查询资产移动设备列表
     *
     * @param assetMobileSearchDto 查询条件
     * @return 分页结果
     */
    PageRowsVo<AssetMobileVo> getPageList(AssetMobileSearchDto assetMobileSearchDto);

    /**
     * 获取所有不重复的标签列表
     *
     * @return 标签列表
     */
    List<String> getDistinctLabels();

    /**
     * 获取相似的资产移动设备ID列表
     */
    List<String> getSimilarIds(String term);

    /**
     * 获取设备品牌列表
     */
    List<String> getSimilarBrands(String term);

    /**
     * 获取设备型号代码列表
     */
    List<String> getSimilarModels(String term);

    /**
     * 获取制造商名称列表
     */
    List<String> getSimilarManufacturers(String term);

    /**
     * 获取操作系统名称列表
     */
    List<String> getSimilarSystemNames(String term);

    /**
     * 获取操作系统版本列表
     */
    List<String> getSimilarSystemVersions(String term);

    /**
     * 获取操作系统名称列表
     */
    List<String> getSimilarOsNames(String term);

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