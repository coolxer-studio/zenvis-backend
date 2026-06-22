package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetFileDto;
import com.coolxer.model.business.asset.dto.AssetFileSearchDto;
import com.coolxer.model.business.asset.vo.AssetFileVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文件资产服务接口
 * 提供文件资产的增删改查等基础操作
 */
@Service
public interface AssetFileService {

    /**
     * 新增文件资产
     *
     * @param assetFileDto 文件资产信息
     * @return 是否新增成功
     */
    boolean add(AssetFileDto assetFileDto);

    /**
     * 删除文件资产
     *
     * @param id 文件资产ID
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 批量删除文件资产
     *
     * @param ids 文件资产ID列表
     * @return 是否删除成功
     */
    boolean deleteALL(List<String> ids);

    /**
     * 更新文件资产信息
     *
     * @param id           文件资产ID
     * @param assetFileDto 更新的文件资产信息
     * @return 是否更新成功
     */
    boolean update(String id, AssetFileDto assetFileDto);

    /**
     * 根据ID查询文件资产
     *
     * @param id 文件资产ID
     * @return 文件资产详细信息
     */
    AssetFileVo getOne(String id);

    /**
     * 分页查询文件资产列表
     *
     * @param assetFileSearchDto 查询条件
     * @return 分页结果
     */
    PageRowsVo<AssetFileVo> getPageList(AssetFileSearchDto assetFileSearchDto);

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
     * 获取相似的文件名称列表
     */
    List<String> getSimilarFileNames(String term);

    /**
     * 获取相似的文件类型列表
     */
    List<String> getSimilarFileTypes(String term);

    /**
     * 获取相似的文件格式列表
     */
    List<String> getSimilarFileFormats(String term);

    /**
     * 获取相似的文件路径列表
     */
    List<String> getSimilarFilePaths(String term);

    /**
     * 获取相似的源系统列表
     */
    List<String> getSimilarSourceSystems(String term);

    /**
     * 获取相似的文件所有者列表
     */
    List<String> getSimilarFileOwners(String term);

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