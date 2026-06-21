package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetProbeDto;
import com.coolxer.model.business.asset.dto.AssetProbeSearchDto;
import com.coolxer.model.business.asset.vo.AssetProbeVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据探针SDK资产服务接口
 * 提供数据探针SDK资产的增删改查等基础操作
 */
@Service
public interface AssetProbeService {

    /**
     * 新增数据探针SDK资产
     *
     * @param assetProbeDto 数据探针SDK资产信息
     * @return 是否新增成功
     */
    boolean add(AssetProbeDto assetProbeDto);

    /**
     * 删除数据探针SDK资产
     *
     * @param id 数据探针SDK资产ID
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 批量删除数据探针SDK资产
     *
     * @param ids 数据探针SDK资产ID列表
     * @return 是否删除成功
     */
    boolean deleteALL(List<String> ids);

    /**
     * 更新数据探针SDK资产信息
     *
     * @param id            数据探针SDK资产ID
     * @param assetProbeDto 更新的数据探针SDK资产信息
     * @return 是否更新成功
     */
    boolean update(String id, AssetProbeDto assetProbeDto);

    /**
     * 根据ID查询数据探针SDK资产
     *
     * @param id 数据探针SDK资产ID
     * @return 数据探针SDK资产详细信息
     */
    AssetProbeVo getOne(String id);

    /**
     * 分页查询数据探针SDK资产列表
     *
     * @param assetProbeSearchDto 查询条件
     * @return 分页结果
     */
    PageRowsVo<AssetProbeVo> getPageList(AssetProbeSearchDto assetProbeSearchDto);

    /**
     * 获取所有不重复的标签列表
     *
     * @return 标签列表
     */
    List<String> getDistinctLabels();

    /**
     * 获取相似的数据探针SDK资产ID列表
     */
    List<String> getSimilarIds(String term);

    /**
     * 获取探针名称列表
     */
    List<String> getSimilarProbeNames(String term);

    /**
     * 获取探针类型列表
     */
    List<String> getSimilarProbeTypes(String term);

    /**
     * 获取开发语言列表
     */
    List<String> getSimilarLanguages(String term);

    /**
     * 获取框架列表
     */
    List<String> getSimilarFrameworks(String term);

    /**
     * 获取加密方式列表
     */
    List<String> getSimilarEncryptionMethods(String term);

    /**
     * 获取认证方式列表
     */
    List<String> getSimilarAuthenticationMethods(String term);

    /**
     * 获取数据传输协议列表
     */
    List<String> getSimilarDataTransmissionProtocols(String term);

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