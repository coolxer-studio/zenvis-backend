package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetLogDto;
import com.coolxer.model.business.asset.dto.AssetLogSearchDto;
import com.coolxer.model.business.asset.vo.AssetLogVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 日志资产服务接口
 * 提供日志资产的增删改查等基础操作
 */
@Service
public interface AssetLogService {

    /**
     * 新增日志资产
     *
     * @param assetLogDto 日志资产信息
     * @return 是否新增成功
     */
    boolean add(AssetLogDto assetLogDto);

    /**
     * 删除日志资产
     *
     * @param id 日志资产ID
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 批量删除日志资产
     *
     * @param ids 日志资产ID列表
     * @return 是否删除成功
     */
    boolean deleteALL(List<String> ids);

    /**
     * 更新日志资产信息
     *
     * @param id          日志资产ID
     * @param assetLogDto 更新的日志资产信息
     * @return 是否更新成功
     */
    boolean update(String id, AssetLogDto assetLogDto);

    /**
     * 根据ID查询日志资产
     *
     * @param id 日志资产ID
     * @return 日志资产详细信息
     */
    AssetLogVo getOne(String id);

    /**
     * 分页查询日志资产列表
     *
     * @param assetLogSearchDto 查询条件
     * @return 分页结果
     */
    PageRowsVo<AssetLogVo> getPageList(AssetLogSearchDto assetLogSearchDto);

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
     * 获取相似的日志名称列表
     */
    List<String> getSimilarLogNames(String term);

    /**
     * 获取相似的日志路径列表
     */
    List<String> getSimilarLogPaths(String term);

    /**
     * 获取相似的日志类型列表
     */
    List<String> getSimilarLogTypes(String term);

    /**
     * 获取相似的日志格式列表
     */
    List<String> getSimilarLogFormats(String term);

    /**
     * 获取相似的日志级别列表
     */
    List<String> getSimilarLogLevels(String term);

    /**
     * 获取相似的进程信息列表
     */
    List<String> getSimilarProcesses(String term);

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