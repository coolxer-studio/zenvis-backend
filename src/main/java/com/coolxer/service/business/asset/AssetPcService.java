package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetPcDto;
import com.coolxer.model.business.asset.dto.AssetPcSearchDto;
import com.coolxer.model.business.asset.vo.AssetPcVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 资产PC设备服务接口
 * 提供资产PC设备的增删改查等基础操作
 */
@Service
public interface AssetPcService {

    /**
     * 新增资产PC设备
     *
     * @param assetPcDto 资产PC设备信息
     * @return 是否新增成功
     */
    boolean add(AssetPcDto assetPcDto);

    /**
     * 删除资产PC设备
     *
     * @param id 资产PC设备ID
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 批量删除资产PC设备
     *
     * @param ids 资产PC设备ID列表
     * @return 是否删除成功
     */
    boolean deleteALL(List<String> ids);

    /**
     * 更新资产PC设备信息
     *
     * @param id         资产PC设备ID
     * @param assetPcDto 更新的资产PC设备信息
     * @return 是否更新成功
     */
    boolean update(String id, AssetPcDto assetPcDto);

    /**
     * 根据ID查询资产PC设备
     *
     * @param id 资产PC设备ID
     * @return 资产PC设备详细信息
     */
    AssetPcVo getOne(String id);

    /**
     * 分页查询资产PC设备列表
     *
     * @param assetPcSearchDto 查询条件
     * @return 分页结果
     */
    PageRowsVo<AssetPcVo> getPageList(AssetPcSearchDto assetPcSearchDto);

    /**
     * 获取所有不重复的标签列表
     *
     * @return 标签列表
     */
    List<String> getDistinctLabels();

    /**
     * 获取相似的资产PC设备ID列表
     */
    List<String> getSimilarIds(String term);

    /**
     * 获取制造商名称列表
     */
    List<String> getSimilarManufacturers(String term);

    /**
     * 获取型号列表
     */
    List<String> getSimilarModels(String term);

    /**
     * 获取架构列表
     */
    List<String> getSimilarArchitectures(String term);

    /**
     * 获取系统名称列表
     */
    List<String> getSimilarSystemNames(String term);

    /**
     * 获取系统版本列表
     */
    List<String> getSimilarSystemVersions(String term);

    /**
     * 获取CPU型号列表
     */
    List<String> getSimilarCpuModels(String term);

    /**
     * 获取显卡型号列表
     */
    List<String> getSimilarGpuModels(String term);

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