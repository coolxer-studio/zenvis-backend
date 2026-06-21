package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetHostDto;
import com.coolxer.model.business.asset.dto.AssetHostSearchDto;
import com.coolxer.model.business.asset.vo.AssetHostVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 资产主机服务接口
 * 提供资产主机的增删改查等基础操作
 */
@Service
public interface AssetHostService {

    /**
     * 新增资产主机
     *
     * @param assetHostDto 资产主机信息
     * @return 是否新增成功
     */
    boolean add(AssetHostDto assetHostDto);

    /**
     * 删除资产主机
     *
     * @param id 资产主机ID
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 批量删除资产主机
     *
     * @param ids 资产主机ID列表
     * @return 是否删除成功
     */
    boolean deleteALL(List<String> ids);

    /**
     * 更新资产主机信息
     *
     * @param id           资产主机ID
     * @param assetHostDto 更新的资产主机信息
     * @return 是否更新成功
     */
    boolean update(String id, AssetHostDto assetHostDto);

    /**
     * 根据ID查询资产主机
     *
     * @param id 资产主机ID
     * @return 资产主机详细信息
     */
    AssetHostVo getOne(String id);

    /**
     * 分页查询资产主机列表
     *
     * @param assetHostSearchDto 查询条件
     * @return 分页结果
     */
    PageRowsVo<AssetHostVo> getPageList(AssetHostSearchDto assetHostSearchDto);

    /**
     * 获取所有不重复的标签列表
     *
     * @return 标签列表
     */
    List<String> getDistinctLabels();

    /**
     * 获取相似的资产主机ID列表
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
