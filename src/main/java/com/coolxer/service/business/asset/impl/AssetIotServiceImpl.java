package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRiskLevel;
import com.coolxer.commons.enums.business.asset.AssetSource;
import com.coolxer.dao.clickhouse.entity.asset.AssetIot;
import com.coolxer.dao.clickhouse.repository.asset.AssetIotRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetIotDto;
import com.coolxer.model.business.asset.dto.AssetIotSearchDto;
import com.coolxer.model.business.asset.vo.AssetIotVo;
import com.coolxer.service.business.asset.AssetIotService;
import com.coolxer.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 资产IoT设备服务实现类
 */
@Slf4j
@Service
public class AssetIotServiceImpl implements AssetIotService {

    @Autowired
    private AssetIotRepository assetIotRepository;

    @Override
    public boolean add(AssetIotDto assetIotDto) {
        try {
            AssetIot assetIot = new AssetIot();
            // 设置基础属性
            String id = assetIotDto.getId();
            if (id == null || id.isEmpty() || !CommonUtil.isValidUUID(id)) {
                id = UUID.randomUUID().toString();
            }
            assetIot.setId(id);
            assetIot.setSource(AssetSource.MANUAL);
            assetIot.setRisk(AssetRiskLevel.LOW);
            assetIot.setLabel(new ArrayList<>());
            assetIot.setRiskInfo("{}");
            assetIot.setInfo("{}");
            assetIot.setNetType("-");
            assetIot.setUpdateTime(new Date());
            assetIot.setInsertTime(new Date());
            // 从DTO更新其他属性
            assetIot.updateFromDto(assetIotDto);

            // 保存到数据库
            assetIotRepository.save(assetIot);
            return true;
        } catch (Exception e) {
            log.error("添加资产IoT设备失败", e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assetIotRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除资产IoT设备失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteALL(List<String> ids) {
        try {
            assetIotRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除资产IoT设备失败, ids: {}", ids, e);
            return false;
        }
    }

    @Override
    public boolean update(String id, AssetIotDto assetIotDto) {
        try {
            Optional<AssetIot> optionalAssetIot = assetIotRepository.findById(id);
            if (optionalAssetIot.isPresent()) {
                AssetIot assetIot = optionalAssetIot.get();
                assetIot.updateFromDto(assetIotDto);
                assetIotRepository.save(assetIot);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新资产IoT设备失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public AssetIotVo getOne(String id) {
        try {
            Optional<AssetIot> optionalAssetIot = assetIotRepository.findById(id);
            return optionalAssetIot.map(AssetIotVo::new).orElse(null);
        } catch (Exception e) {
            log.error("获取资产IoT设备失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public PageRowsVo<AssetIotVo> getPageList(AssetIotSearchDto assetIotSearchDto) {
        try {
            Pageable pageable = PageRequest.of(assetIotSearchDto.getPage() - 1, assetIotSearchDto.getPerPage());
            Page<AssetIot> byPage;

            // 处理时间字符串
            String updateTimeStart = null;
            String updateTimeEnd = null;
            String insertTimeStart = null;
            String insertTimeEnd = null;

            if (assetIotSearchDto.getUpdateTime() != null && !assetIotSearchDto.getUpdateTime().isEmpty()) {
                String[] updateTimeParts = assetIotSearchDto.getUpdateTime().split(",");
                if (updateTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    updateTimeStart = updateTimeParts[0];
                    updateTimeEnd = updateTimeParts[1];
                }
            }

            if (assetIotSearchDto.getInsertTime() != null && !assetIotSearchDto.getInsertTime().isEmpty()) {
                String[] insertTimeParts = assetIotSearchDto.getInsertTime().split(",");
                if (insertTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    insertTimeStart = insertTimeParts[0];
                    insertTimeEnd = insertTimeParts[1];
                }
            }

            // 根据排序方向选择查询方法
            if ("desc".equalsIgnoreCase(assetIotSearchDto.getOrderDir())) {
                byPage = assetIotRepository.findByPageDesc(
                        pageable,
                        assetIotSearchDto.getId(),
                        assetIotSearchDto.getSource(),
                        assetIotSearchDto.getType(),
                        assetIotSearchDto.getOwner(),
                        assetIotSearchDto.getStatus(),
                        assetIotSearchDto.getAreaCode(),
                        assetIotSearchDto.getLabel(),
                        assetIotSearchDto.getAccess(),
                        assetIotSearchDto.getLevel(),
                        assetIotSearchDto.getRisk(),
                        assetIotSearchDto.getDeviceName(),
                        assetIotSearchDto.getDeviceType(),
                        assetIotSearchDto.getManufacturer(),
                        assetIotSearchDto.getModel(),
                        assetIotSearchDto.getSerialNumber(),
                        assetIotSearchDto.getFirmwareVersion(),
                        assetIotSearchDto.getPowerType(),
                        assetIotSearchDto.getBatteryLevel(),
                        assetIotSearchDto.getCommunicationProtocol(),
                        assetIotSearchDto.getNetType(),
                        assetIotSearchDto.getLanIp(),
                        assetIotSearchDto.getWanIp(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd
                );
            } else {
                byPage = assetIotRepository.findByPageAsc(
                        pageable,
                        assetIotSearchDto.getId(),
                        assetIotSearchDto.getSource(),
                        assetIotSearchDto.getType(),
                        assetIotSearchDto.getOwner(),
                        assetIotSearchDto.getStatus(),
                        assetIotSearchDto.getAreaCode(),
                        assetIotSearchDto.getLabel(),
                        assetIotSearchDto.getAccess(),
                        assetIotSearchDto.getLevel(),
                        assetIotSearchDto.getRisk(),
                        assetIotSearchDto.getDeviceName(),
                        assetIotSearchDto.getDeviceType(),
                        assetIotSearchDto.getManufacturer(),
                        assetIotSearchDto.getModel(),
                        assetIotSearchDto.getSerialNumber(),
                        assetIotSearchDto.getFirmwareVersion(),
                        assetIotSearchDto.getPowerType(),
                        assetIotSearchDto.getBatteryLevel(),
                        assetIotSearchDto.getCommunicationProtocol(),
                        assetIotSearchDto.getNetType(),
                        assetIotSearchDto.getLanIp(),
                        assetIotSearchDto.getWanIp(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd
                );
            }

            List<AssetIotVo> assetIotVos = byPage.getContent().stream()
                    .map(AssetIotVo::new)
                    .toList();

            return new PageRowsVo<>(assetIotVos, byPage.getTotalElements());
        } catch (Exception e) {
            log.error("分页查询资产IoT设备列表失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0);
        }
    }

    @Override
    public List<String> getDistinctLabels() {
        try {
            return assetIotRepository.findDistinctLabels();
        } catch (Exception e) {
            log.error("获取资产IoT设备标签列表失败", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarIds(String term) {
        try {
            return assetIotRepository.findLikeIds(term);
        } catch (Exception e) {
            log.error("获取相似资产IoT设备ID列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarDeviceNames(String term) {
        try {
            return assetIotRepository.findLikeDeviceNames(term);
        } catch (Exception e) {
            log.error("获取相似设备名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarDeviceTypes(String term) {
        try {
            return assetIotRepository.findLikeDeviceTypes(term);
        } catch (Exception e) {
            log.error("获取相似设备类型列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarManufacturers(String term) {
        try {
            return assetIotRepository.findLikeManufacturers(term);
        } catch (Exception e) {
            log.error("获取相似制造商名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarModels(String term) {
        try {
            return assetIotRepository.findLikeModels(term);
        } catch (Exception e) {
            log.error("获取相似型号列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarFirmwareVersions(String term) {
        try {
            return assetIotRepository.findLikeFirmwareVersions(term);
        } catch (Exception e) {
            log.error("获取相似固件版本列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarCommunicationProtocols(String term) {
        try {
            return assetIotRepository.findLikeCommunicationProtocols(term);
        } catch (Exception e) {
            log.error("获取相似通信协议列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public long countTotal() {
        return assetIotRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return assetIotRepository.countTodayIncrease();
    }

    @Override
    public Map<String, Object> getStatisticsByDateOfWeek() {
        return CommonUtil.getStatisticsMap(assetIotRepository::countByDateOfWeek);
    }

    @Override
    public Map<String, Object> getStatisticsByRisk() {
        return CommonUtil.getStatisticsMap(assetIotRepository::countByRisk);
    }

    @Override
    public Map<String, Object> getStatisticsByLevel() {
        return CommonUtil.getStatisticsMap(assetIotRepository::countByLevel);
    }
} 