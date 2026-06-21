package com.coolxer.service.business.asset;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetIotDto;
import com.coolxer.model.business.asset.dto.AssetIotSearchDto;
import com.coolxer.model.business.asset.vo.AssetIotVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 资产IoT设备服务接口
 * 提供资产IoT设备的增删改查等基础操作
 */
@Service
public interface AssetIotService {

    /**
     * 新增资产IoT设备
     *
     * @param assetIotDto 资产IoT设备信息
     * @return 是否新增成功
     */
    boolean add(AssetIotDto assetIotDto);

    /**
     * 删除资产IoT设备
     *
     * @param id 资产IoT设备ID
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 批量删除资产IoT设备
     *
     * @param ids 资产IoT设备ID列表
     * @return 是否删除成功
     */
    boolean deleteALL(List<String> ids);

    /**
     * 更新资产IoT设备信息
     *
     * @param id          资产IoT设备ID
     * @param assetIotDto 更新的资产IoT设备信息
     * @return 是否更新成功
     */
    boolean update(String id, AssetIotDto assetIotDto);

    /**
     * 根据ID查询资产IoT设备
     *
     * @param id 资产IoT设备ID
     * @return 资产IoT设备详细信息
     */
    AssetIotVo getOne(String id);

    /**
     * 分页查询资产IoT设备列表
     *
     * @param assetIotSearchDto 查询条件
     * @return 分页结果
     */
    PageRowsVo<AssetIotVo> getPageList(AssetIotSearchDto assetIotSearchDto);

    /**
     * 获取所有不重复的标签列表
     *
     * @return 标签列表
     */
    List<String> getDistinctLabels();

    /**
     * 获取相似的资产IoT设备ID列表
     */
    List<String> getSimilarIds(String term);

    /**
     * 获取设备名称列表
     */
    List<String> getSimilarDeviceNames(String term);

    /**
     * 获取设备类型列表
     */
    List<String> getSimilarDeviceTypes(String term);

    /**
     * 获取制造商名称列表
     */
    List<String> getSimilarManufacturers(String term);

    /**
     * 获取型号列表
     */
    List<String> getSimilarModels(String term);

    /**
     * 获取固件版本列表
     */
    List<String> getSimilarFirmwareVersions(String term);

    /**
     * 获取通信协议列表
     */
    List<String> getSimilarCommunicationProtocols(String term);

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