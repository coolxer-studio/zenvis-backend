package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRiskLevel;
import com.coolxer.commons.enums.business.asset.AssetSource;
import com.coolxer.dao.clickhouse.entity.asset.AssetPc;
import com.coolxer.dao.clickhouse.repository.asset.AssetPcRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetPcDto;
import com.coolxer.model.business.asset.dto.AssetPcSearchDto;
import com.coolxer.model.business.asset.vo.AssetPcVo;
import com.coolxer.service.business.asset.AssetPcService;
import com.coolxer.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 资产PC设备服务实现类
 */
@Slf4j
@Service
public class AssetPcServiceImpl implements AssetPcService {

    @Autowired
    private AssetPcRepository assetPcRepository;

    @Override
    public boolean add(AssetPcDto assetPcDto) {
        try {
            AssetPc assetPc = new AssetPc();
            // 设置基础属性
            String id = assetPcDto.getId();
            if (id == null || id.isEmpty() || !CommonUtil.isValidUUID(id)) {
                id = UUID.randomUUID().toString();
            }
            assetPc.setId(id);
            assetPc.setSource(AssetSource.MANUAL);
            assetPc.setRisk(AssetRiskLevel.LOW);
            assetPc.setLabel(new ArrayList<>());
            assetPc.setRiskInfo("{}");
            assetPc.setInfo("{}");
            assetPc.setNetType("-");
            assetPc.setUpdateTime(new Date());
            assetPc.setInsertTime(new Date());
            // 从DTO更新其他属性
            assetPc.updateFromDto(assetPcDto);

            // 保存到数据库
            assetPcRepository.save(assetPc);
            return true;
        } catch (Exception e) {
            log.error("添加资产PC设备失败", e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assetPcRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除资产PC设备失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteALL(List<String> ids) {
        try {
            assetPcRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除资产PC设备失败, ids: {}", ids, e);
            return false;
        }
    }

    @Override
    public boolean update(String id, AssetPcDto assetPcDto) {
        try {
            Optional<AssetPc> optionalAssetPc = assetPcRepository.findById(id);
            if (optionalAssetPc.isPresent()) {
                AssetPc assetPc = optionalAssetPc.get();
                assetPc.updateFromDto(assetPcDto);
                assetPcRepository.save(assetPc);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新资产PC设备失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public AssetPcVo getOne(String id) {
        try {
            Optional<AssetPc> optionalAssetPc = assetPcRepository.findById(id);
            return optionalAssetPc.map(AssetPcVo::new).orElse(null);
        } catch (Exception e) {
            log.error("获取资产PC设备失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public PageRowsVo<AssetPcVo> getPageList(AssetPcSearchDto assetPcSearchDto) {
        try {
            Pageable pageable = PageRequest.of(assetPcSearchDto.getPage() - 1, assetPcSearchDto.getPerPage());
            Page<AssetPc> byPage;

            // 处理时间字符串
            String updateTimeStart = null;
            String updateTimeEnd = null;
            String insertTimeStart = null;
            String insertTimeEnd = null;

            if (assetPcSearchDto.getUpdateTime() != null && !assetPcSearchDto.getUpdateTime().isEmpty()) {
                String[] updateTimeParts = assetPcSearchDto.getUpdateTime().split(",");
                if (updateTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    updateTimeStart = updateTimeParts[0];
                    updateTimeEnd = updateTimeParts[1];
                }
            }

            if (assetPcSearchDto.getInsertTime() != null && !assetPcSearchDto.getInsertTime().isEmpty()) {
                String[] insertTimeParts = assetPcSearchDto.getInsertTime().split(",");
                if (insertTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    insertTimeStart = insertTimeParts[0];
                    insertTimeEnd = insertTimeParts[1];
                }
            }

            // 根据排序方向选择查询方法
            if ("desc".equalsIgnoreCase(assetPcSearchDto.getOrderDir())) {
                byPage = assetPcRepository.findByPageDesc(
                        pageable,
                        assetPcSearchDto.getId(),
                        assetPcSearchDto.getSource(),
                        assetPcSearchDto.getType(),
                        assetPcSearchDto.getOwner(),
                        assetPcSearchDto.getStatus(),
                        assetPcSearchDto.getAreaCode(),
                        assetPcSearchDto.getLabel(),
                        assetPcSearchDto.getAccess(),
                        assetPcSearchDto.getLevel(),
                        assetPcSearchDto.getRisk(),
                        assetPcSearchDto.getManufacturer(),
                        assetPcSearchDto.getModel(),
                        assetPcSearchDto.getArchitecture(),
                        assetPcSearchDto.getSystemName(),
                        assetPcSearchDto.getSystemVersion(),
                        assetPcSearchDto.getCpuModel(),
                        assetPcSearchDto.getCpuCores(),
                        assetPcSearchDto.getMemorySize(),
                        assetPcSearchDto.getDiskSize(),
                        assetPcSearchDto.getGpuModel(),
                        assetPcSearchDto.getGpuBrand(),
                        assetPcSearchDto.getGpuMemorySize(),
                        assetPcSearchDto.getGpuMemoryType(),
                        assetPcSearchDto.getNetType(),
                        assetPcSearchDto.getLanIp(),
                        assetPcSearchDto.getWanIp(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd
                );
            } else {
                byPage = assetPcRepository.findByPageAsc(
                        pageable,
                        assetPcSearchDto.getId(),
                        assetPcSearchDto.getSource(),
                        assetPcSearchDto.getType(),
                        assetPcSearchDto.getOwner(),
                        assetPcSearchDto.getStatus(),
                        assetPcSearchDto.getAreaCode(),
                        assetPcSearchDto.getLabel(),
                        assetPcSearchDto.getAccess(),
                        assetPcSearchDto.getLevel(),
                        assetPcSearchDto.getRisk(),
                        assetPcSearchDto.getManufacturer(),
                        assetPcSearchDto.getModel(),
                        assetPcSearchDto.getArchitecture(),
                        assetPcSearchDto.getSystemName(),
                        assetPcSearchDto.getSystemVersion(),
                        assetPcSearchDto.getCpuModel(),
                        assetPcSearchDto.getCpuCores(),
                        assetPcSearchDto.getMemorySize(),
                        assetPcSearchDto.getDiskSize(),
                        assetPcSearchDto.getGpuModel(),
                        assetPcSearchDto.getGpuBrand(),
                        assetPcSearchDto.getGpuMemorySize(),
                        assetPcSearchDto.getGpuMemoryType(),
                        assetPcSearchDto.getNetType(),
                        assetPcSearchDto.getLanIp(),
                        assetPcSearchDto.getWanIp(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd
                );
            }

            List<AssetPcVo> assetPcVos = byPage.getContent().stream()
                    .map(AssetPcVo::new)
                    .toList();

            return new PageRowsVo<>(assetPcVos, byPage.getTotalElements());
        } catch (Exception e) {
            log.error("分页查询资产PC设备列表失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0);
        }
    }

    @Override
    public List<String> getDistinctLabels() {
        try {
            return assetPcRepository.findDistinctLabels();
        } catch (Exception e) {
            log.error("获取资产PC设备标签列表失败", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarIds(String term) {
        try {
            return assetPcRepository.findLikeIds(term);
        } catch (Exception e) {
            log.error("获取相似资产PC设备ID列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarManufacturers(String term) {
        try {
            return assetPcRepository.findLikeManufacturers(term);
        } catch (Exception e) {
            log.error("获取相似制造商列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarModels(String term) {
        try {
            return assetPcRepository.findLikeModels(term);
        } catch (Exception e) {
            log.error("获取相似型号列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarArchitectures(String term) {
        try {
            return assetPcRepository.findLikeArchitectures(term);
        } catch (Exception e) {
            log.error("获取相似架构列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarSystemNames(String term) {
        try {
            return assetPcRepository.findLikeSystemNames(term);
        } catch (Exception e) {
            log.error("获取相似系统名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarSystemVersions(String term) {
        try {
            return assetPcRepository.findLikeSystemVersions(term);
        } catch (Exception e) {
            log.error("获取相似系统版本列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarCpuModels(String term) {
        try {
            return assetPcRepository.findLikeCpuModels(term);
        } catch (Exception e) {
            log.error("获取相似CPU型号列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarGpuModels(String term) {
        try {
            return assetPcRepository.findLikeGpuModels(term);
        } catch (Exception e) {
            log.error("获取相似显卡型号列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public long countTotal() {
        return assetPcRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return assetPcRepository.countTodayIncrease();
    }


    @Override
    public Map<String, Object> getStatisticsByDateOfWeek() {
        return CommonUtil.getStatisticsMap(assetPcRepository::countByDateOfWeek);
    }

    @Override
    public Map<String, Object> getStatisticsByRisk() {
        return CommonUtil.getStatisticsMap(assetPcRepository::countByRisk);
    }

    @Override
    public Map<String, Object> getStatisticsByLevel() {
        return CommonUtil.getStatisticsMap(assetPcRepository::countByLevel);
    }
} 