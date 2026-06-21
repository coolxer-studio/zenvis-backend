package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRiskLevel;
import com.coolxer.commons.enums.business.asset.AssetSource;
import com.coolxer.dao.clickhouse.entity.asset.AssetApp;
import com.coolxer.dao.clickhouse.repository.asset.AssetAppRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetAppDto;
import com.coolxer.model.business.asset.dto.AssetAppSearchDto;
import com.coolxer.model.business.asset.vo.AssetAppVo;
import com.coolxer.service.business.asset.AssetAppService;
import com.coolxer.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * APP应用程序资产服务实现类
 */
@Slf4j
@Service
public class AssetAppServiceImpl implements AssetAppService {

    @Autowired
    private AssetAppRepository assetAppRepository;

    @Override
    public boolean add(AssetAppDto assetAppDto) {
        try {
            AssetApp assetApp = new AssetApp();
            // 设置基础属性
            String id = assetAppDto.getId();
            if (id == null || id.isEmpty() || !CommonUtil.isValidUUID(id)) {
                id = UUID.randomUUID().toString();
            }
            assetApp.setId(id);
            assetApp.setSource(AssetSource.MANUAL);
            assetApp.setRisk(AssetRiskLevel.LOW);
            assetApp.setLabel(new ArrayList<>());
            assetApp.setRiskInfo("{}");
            assetApp.setInfo("{}");
            assetApp.setUpdateTime(new Date());
            assetApp.setInsertTime(new Date());
            // 从DTO更新其他属性
            assetApp.updateFromDto(assetAppDto);

            // 保存到数据库
            assetAppRepository.save(assetApp);
            return true;
        } catch (Exception e) {
            log.error("添加APP应用程序资产失败", e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assetAppRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除APP应用程序资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteALL(List<String> ids) {
        try {
            assetAppRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除APP应用程序资产失败, ids: {}", ids, e);
            return false;
        }
    }

    @Override
    public boolean update(String id, AssetAppDto assetAppDto) {
        try {
            Optional<AssetApp> optionalAssetApp = assetAppRepository.findById(id);
            if (optionalAssetApp.isPresent()) {
                AssetApp assetApp = optionalAssetApp.get();
                assetApp.updateFromDto(assetAppDto);
                assetAppRepository.save(assetApp);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新APP应用程序资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public AssetAppVo getOne(String id) {
        try {
            Optional<AssetApp> optionalAssetApp = assetAppRepository.findById(id);
            return optionalAssetApp.map(AssetAppVo::fromEntity).orElse(null);
        } catch (Exception e) {
            log.error("获取APP应用程序资产失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public PageRowsVo<AssetAppVo> getPageList(AssetAppSearchDto assetAppSearchDto) {
        try {
            Pageable pageable = PageRequest.of(assetAppSearchDto.getPage() - 1, assetAppSearchDto.getPerPage());
            Page<AssetApp> byPage;

            // 处理时间字符串
            String updateTimeStart = null;
            String updateTimeEnd = null;
            String insertTimeStart = null;
            String insertTimeEnd = null;
            String publishTimeStart = null;
            String publishTimeEnd = null;

            if (assetAppSearchDto.getUpdateTime() != null && !assetAppSearchDto.getUpdateTime().isEmpty()) {
                String[] updateTimeParts = assetAppSearchDto.getUpdateTime().split(",");
                if (updateTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    updateTimeStart = updateTimeParts[0];
                    updateTimeEnd = updateTimeParts[1];
                }
            }

            if (assetAppSearchDto.getInsertTime() != null && !assetAppSearchDto.getInsertTime().isEmpty()) {
                String[] insertTimeParts = assetAppSearchDto.getInsertTime().split(",");
                if (insertTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    insertTimeStart = insertTimeParts[0];
                    insertTimeEnd = insertTimeParts[1];
                }
            }

            // 处理发布时间
            if (assetAppSearchDto.getPublishTimeStart() != null) {
                publishTimeStart = assetAppSearchDto.getPublishTimeStart().toString();
            }

            if (assetAppSearchDto.getPublishTimeEnd() != null) {
                publishTimeEnd = assetAppSearchDto.getPublishTimeEnd().toString();
            }

            // 根据排序方向选择查询方法
            if ("desc".equalsIgnoreCase(assetAppSearchDto.getOrderDir())) {
                byPage = assetAppRepository.findByConditionsDesc(
                        assetAppSearchDto.getId(),
                        assetAppSearchDto.getSource(),
                        assetAppSearchDto.getType(),
                        assetAppSearchDto.getOwner(),
                        assetAppSearchDto.getStatus(),
                        assetAppSearchDto.getLabel(),
                        assetAppSearchDto.getAccess(),
                        assetAppSearchDto.getLevel(),
                        assetAppSearchDto.getRisk(),
                        assetAppSearchDto.getAppName(),
                        assetAppSearchDto.getAppVersion(),
                        assetAppSearchDto.getAppType(),
                        assetAppSearchDto.getPlatform(),
                        assetAppSearchDto.getPackageName(),
                        assetAppSearchDto.getDeveloper(),
                        publishTimeStart,
                        publishTimeEnd,
                        assetAppSearchDto.getUpdateChannel(),
                        assetAppSearchDto.getMinOsVersion(),
                        assetAppSearchDto.getTargetOsVersion(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            } else {
                byPage = assetAppRepository.findByConditionsASC(
                        assetAppSearchDto.getId(),
                        assetAppSearchDto.getSource(),
                        assetAppSearchDto.getType(),
                        assetAppSearchDto.getOwner(),
                        assetAppSearchDto.getStatus(),
                        assetAppSearchDto.getLabel(),
                        assetAppSearchDto.getAccess(),
                        assetAppSearchDto.getLevel(),
                        assetAppSearchDto.getRisk(),
                        assetAppSearchDto.getAppName(),
                        assetAppSearchDto.getAppVersion(),
                        assetAppSearchDto.getAppType(),
                        assetAppSearchDto.getPlatform(),
                        assetAppSearchDto.getPackageName(),
                        assetAppSearchDto.getDeveloper(),
                        publishTimeStart,
                        publishTimeEnd,
                        assetAppSearchDto.getUpdateChannel(),
                        assetAppSearchDto.getMinOsVersion(),
                        assetAppSearchDto.getTargetOsVersion(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            }

            List<AssetAppVo> assetAppVos = byPage.getContent().stream()
                    .map(AssetAppVo::fromEntity)
                    .toList();

            return new PageRowsVo<>(assetAppVos, byPage.getTotalElements());
        } catch (Exception e) {
            log.error("分页查询APP应用程序资产列表失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0);
        }
    }

    @Override
    public List<String> getDistinctLabels() {
        try {
            return assetAppRepository.findDistinctLabels();
        } catch (Exception e) {
            log.error("获取APP应用程序资产标签列表失败", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarIds(String term) {
        try {
            return assetAppRepository.findLikeIds(term);
        } catch (Exception e) {
            log.error("获取相似APP应用程序资产ID列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarAppNames(String term) {
        try {
            return assetAppRepository.findLikeAppNames(term);
        } catch (Exception e) {
            log.error("获取相似应用名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarAppVersions(String term) {
        try {
            return assetAppRepository.findLikeAppVersions(term);
        } catch (Exception e) {
            log.error("获取相似应用版本列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarAppTypes(String term) {
        try {
            return assetAppRepository.findLikeAppTypes(term);
        } catch (Exception e) {
            log.error("获取相似应用类型列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarPlatforms(String term) {
        try {
            return assetAppRepository.findLikePlatforms(term);
        } catch (Exception e) {
            log.error("获取相似平台列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarPackageNames(String term) {
        try {
            return assetAppRepository.findLikePackageNames(term);
        } catch (Exception e) {
            log.error("获取相似包名列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarDevelopers(String term) {
        try {
            return assetAppRepository.findLikeDevelopers(term);
        } catch (Exception e) {
            log.error("获取相似开发者列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public long countTotal() {
        return assetAppRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return assetAppRepository.countTodayIncrease();
    }

    @Override
    public Map<String, Object> getStatisticsByDateOfWeek() {
        return CommonUtil.getStatisticsMap(assetAppRepository::countByDateOfWeek);
    }

    @Override
    public Map<String, Object> getStatisticsByRisk() {
        return CommonUtil.getStatisticsMap(assetAppRepository::countByRisk);
    }

    @Override
    public Map<String, Object> getStatisticsByLevel() {
        return CommonUtil.getStatisticsMap(assetAppRepository::countByLevel);
    }
} 