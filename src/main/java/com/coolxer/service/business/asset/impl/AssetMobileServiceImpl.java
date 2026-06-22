package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRiskLevel;
import com.coolxer.commons.enums.business.asset.AssetSource;
import com.coolxer.dao.clickhouse.entity.asset.AssetMobile;
import com.coolxer.dao.clickhouse.repository.asset.AssetMobileRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetMobileDto;
import com.coolxer.model.business.asset.dto.AssetMobileSearchDto;
import com.coolxer.model.business.asset.vo.AssetMobileVo;
import com.coolxer.service.business.asset.AssetMobileService;
import com.coolxer.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

/**
 * 资产移动设备服务实现类
 */
@Slf4j
@Service
public class AssetMobileServiceImpl implements AssetMobileService {

    @Autowired
    private AssetMobileRepository assetMobileRepository;

    @Override
    public boolean add(AssetMobileDto assetMobileDto) {
        try {
            AssetMobile assetMobile = new AssetMobile();
            // 设置基础属性
            String id = assetMobileDto.getId();
            if (id == null || id.isEmpty() || !CommonUtil.isValidUUID(id)) {
                id = UUID.randomUUID().toString();
            }
            assetMobile.setId(id);
            assetMobile.setSource(AssetSource.MANUAL);
            assetMobile.setRisk(AssetRiskLevel.LOW);
            assetMobile.setLabel(new ArrayList<>());
            assetMobile.setRiskInfo("{}");
            assetMobile.setInfo("{}");
            assetMobile.setUpdateTime(new Date());
            assetMobile.setInsertTime(new Date());
            // 从DTO更新其他属性
            assetMobile.updateFromDto(assetMobileDto);

            // 保存到数据库
            assetMobileRepository.save(assetMobile);
            return true;
        } catch (Exception e) {
            log.error("添加资产移动设备失败", e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assetMobileRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除资产移动设备失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteALL(List<String> ids) {
        try {
            assetMobileRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除资产移动设备失败, ids: {}", ids, e);
            return false;
        }
    }

    @Override
    public boolean update(String id, AssetMobileDto assetMobileDto) {
        try {
            Optional<AssetMobile> optionalAssetMobile = assetMobileRepository.findById(id);
            if (optionalAssetMobile.isPresent()) {
                AssetMobile assetMobile = optionalAssetMobile.get();
                assetMobile.updateFromDto(assetMobileDto);
                assetMobileRepository.save(assetMobile);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新资产移动设备失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public AssetMobileVo getOne(String id) {
        try {
            Optional<AssetMobile> optionalAssetMobile = assetMobileRepository.findById(id);
            return optionalAssetMobile.map(AssetMobileVo::new).orElse(null);
        } catch (Exception e) {
            log.error("获取资产移动设备失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public PageRowsVo<AssetMobileVo> getPageList(AssetMobileSearchDto assetMobileSearchDto) {
        try {
            Pageable pageable = PageRequest.of(assetMobileSearchDto.getPage() - 1, assetMobileSearchDto.getPerPage());
            Page<AssetMobile> byPage;

            // 处理时间字符串
            String updateTimeStart = null;
            String updateTimeEnd = null;
            String insertTimeStart = null;
            String insertTimeEnd = null;

            if (assetMobileSearchDto.getUpdateTime() != null && !assetMobileSearchDto.getUpdateTime().isEmpty()) {
                String[] updateTimeParts = assetMobileSearchDto.getUpdateTime().split(",");
                if (updateTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    updateTimeStart = updateTimeParts[0];
                    updateTimeEnd = updateTimeParts[1];
                }
            }

            if (assetMobileSearchDto.getInsertTime() != null && !assetMobileSearchDto.getInsertTime().isEmpty()) {
                String[] insertTimeParts = assetMobileSearchDto.getInsertTime().split(",");
                if (insertTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    insertTimeStart = insertTimeParts[0];
                    insertTimeEnd = insertTimeParts[1];
                }
            }

            // 根据排序方向选择查询方法
            if ("desc".equalsIgnoreCase(assetMobileSearchDto.getOrderDir())) {
                byPage = assetMobileRepository.findByPageDesc(
                        pageable,
                        assetMobileSearchDto.getId(),
                        assetMobileSearchDto.getSource(),
                        assetMobileSearchDto.getType(),
                        assetMobileSearchDto.getOwner(),
                        assetMobileSearchDto.getStatus(),
                        assetMobileSearchDto.getAreaCode(),
                        assetMobileSearchDto.getLabel(),
                        assetMobileSearchDto.getAccess(),
                        assetMobileSearchDto.getLevel(),
                        assetMobileSearchDto.getRisk(),
                        assetMobileSearchDto.getBrand(),
                        assetMobileSearchDto.getModelCode(),
                        assetMobileSearchDto.getManufacturer(),
                        assetMobileSearchDto.getSystemName(),
                        assetMobileSearchDto.getSystemVersion(),
                        assetMobileSearchDto.getOsName(),
                        assetMobileSearchDto.getNetType(),
                        assetMobileSearchDto.getLanIp(),
                        assetMobileSearchDto.getWanIp(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd
                );
            } else {
                byPage = assetMobileRepository.findByPageAsc(
                        pageable,
                        assetMobileSearchDto.getId(),
                        assetMobileSearchDto.getSource(),
                        assetMobileSearchDto.getType(),
                        assetMobileSearchDto.getOwner(),
                        assetMobileSearchDto.getStatus(),
                        assetMobileSearchDto.getAreaCode(),
                        assetMobileSearchDto.getLabel(),
                        assetMobileSearchDto.getAccess(),
                        assetMobileSearchDto.getLevel(),
                        assetMobileSearchDto.getRisk(),
                        assetMobileSearchDto.getBrand(),
                        assetMobileSearchDto.getModelCode(),
                        assetMobileSearchDto.getManufacturer(),
                        assetMobileSearchDto.getSystemName(),
                        assetMobileSearchDto.getSystemVersion(),
                        assetMobileSearchDto.getOsName(),
                        assetMobileSearchDto.getNetType(),
                        assetMobileSearchDto.getLanIp(),
                        assetMobileSearchDto.getWanIp(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd
                );
            }

            List<AssetMobileVo> assetMobileVos = byPage.getContent().stream()
                    .map(AssetMobileVo::new)
                    .toList();

            return new PageRowsVo<>(assetMobileVos, byPage.getTotalElements());
        } catch (Exception e) {
            log.error("分页查询资产移动设备列表失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0);
        }
    }

    @Override
    public List<String> getDistinctLabels() {
        try {
            return assetMobileRepository.findDistinctLabels();
        } catch (Exception e) {
            log.error("获取资产移动设备标签列表失败", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarIds(String term) {
        try {
            return assetMobileRepository.findLikeIds(term);
        } catch (Exception e) {
            log.error("获取相似资产移动设备ID列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarBrands(String term) {
        try {
            return assetMobileRepository.findLikeBrands(term);
        } catch (Exception e) {
            log.error("获取相似品牌列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarModels(String term) {
        try {
            return assetMobileRepository.findLikeModels(term);
        } catch (Exception e) {
            log.error("获取相似型号代码列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarManufacturers(String term) {
        try {
            return assetMobileRepository.findLikeManufacturers(term);
        } catch (Exception e) {
            log.error("获取相似制造商名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarSystemNames(String term) {
        try {
            return assetMobileRepository.findLikeSystemNames(term);
        } catch (Exception e) {
            log.error("获取相似系统列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarSystemVersions(String term) {
        try {
            return assetMobileRepository.findLikeSystemVersions(term);
        } catch (Exception e) {
            log.error("获取相似系统版本列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarOsNames(String term) {
        try {
            return assetMobileRepository.findLikeOsNames(term);
        } catch (Exception e) {
            log.error("获取相似系统名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public long countTotal() {
        return assetMobileRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return assetMobileRepository.countTodayIncrease();
    }

    private Map<String, Object> getStatisticsByWeek(Supplier<List<Map<String, Object>>> supplier) {
        return CommonUtil.getStatisticsMap(supplier);
    }

    @Override
    public Map<String, Object> getStatisticsByDateOfWeek() {
        return CommonUtil.getStatisticsMap(assetMobileRepository::countByDateOfWeek);
    }

    @Override
    public Map<String, Object> getStatisticsByRisk() {
        return CommonUtil.getStatisticsMap(assetMobileRepository::countByRisk);
    }

    @Override
    public Map<String, Object> getStatisticsByLevel() {
        return CommonUtil.getStatisticsMap(assetMobileRepository::countByLevel);
    }
} 