package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRiskLevel;
import com.coolxer.commons.enums.business.asset.AssetSource;
import com.coolxer.dao.clickhouse.entity.asset.AssetService;
import com.coolxer.dao.clickhouse.repository.asset.AssetServiceRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetServiceDto;
import com.coolxer.model.business.asset.dto.AssetServiceSearchDto;
import com.coolxer.model.business.asset.vo.AssetServiceVo;
import com.coolxer.service.business.asset.AssetServiceService;
import com.coolxer.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 系统服务资产服务实现类
 */
@Slf4j
@Service
public class AssetServiceServiceImpl implements AssetServiceService {

    @Autowired
    private AssetServiceRepository assetServiceRepository;

    @Override
    public boolean add(AssetServiceDto assetServiceDto) {
        try {
            // 使用DTO创建实体
            AssetService assetService = AssetService.fromDto(assetServiceDto);

            // 如果ID为空，生成新的UUID
            if (assetService.getId() == null || assetService.getId().isEmpty() || !CommonUtil.isValidUUID(assetService.getId())) {
                assetService.setId(UUID.randomUUID().toString());
            }

            // 设置默认值
            if (assetService.getSource() == null) {
                assetService.setSource(AssetSource.MANUAL);
            }

            if (assetService.getRisk() == null) {
                assetService.setRisk(AssetRiskLevel.LOW);
            }

            if (assetService.getLabel() == null) {
                assetService.setLabel(new ArrayList<>());
            }

            if (assetService.getRiskInfo() == null) {
                assetService.setRiskInfo("{}");
            }

            if (assetService.getInfo() == null) {
                assetService.setInfo("{}");
            }

            // 设置时间
            Date now = new Date();
            assetService.setUpdateTime(now);
            assetService.setInsertTime(now);

            // 保存到数据库
            assetServiceRepository.save(assetService);
            return true;
        } catch (Exception e) {
            log.error("添加系统服务资产失败", e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assetServiceRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除系统服务资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteALL(List<String> ids) {
        try {
            assetServiceRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除系统服务资产失败, ids: {}", ids, e);
            return false;
        }
    }

    @Override
    public boolean update(String id, AssetServiceDto assetServiceDto) {
        try {
            Optional<AssetService> optionalAssetService = assetServiceRepository.findById(id);
            if (optionalAssetService.isPresent()) {
                AssetService assetService = optionalAssetService.get();

                // 更新实体属性
                AssetService updatedService = AssetService.fromDto(assetServiceDto);

                // 保留原有ID和创建时间
                updatedService.setId(id);
                updatedService.setInsertTime(assetService.getInsertTime());
                updatedService.setUpdateTime(new Date());

                assetServiceRepository.save(updatedService);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新系统服务资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public AssetServiceVo getOne(String id) {
        try {
            Optional<AssetService> optionalAssetService = assetServiceRepository.findById(id);
            return optionalAssetService.map(AssetServiceVo::fromEntity).orElse(null);
        } catch (Exception e) {
            log.error("获取系统服务资产失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public PageRowsVo<AssetServiceVo> getPageList(AssetServiceSearchDto assetServiceSearchDto) {
        try {
            Pageable pageable = PageRequest.of(assetServiceSearchDto.getPage() - 1, assetServiceSearchDto.getPerPage());
            Page<AssetService> byPage;

            // 处理时间字符串
            String updateTimeStart = null;
            String updateTimeEnd = null;
            String insertTimeStart = null;
            String insertTimeEnd = null;

            if (assetServiceSearchDto.getUpdateTime() != null && !assetServiceSearchDto.getUpdateTime().isEmpty()) {
                String[] updateTimeParts = assetServiceSearchDto.getUpdateTime().split(",");
                if (updateTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    updateTimeStart = updateTimeParts[0];
                    updateTimeEnd = updateTimeParts[1];
                }
            }

            if (assetServiceSearchDto.getInsertTime() != null && !assetServiceSearchDto.getInsertTime().isEmpty()) {
                String[] insertTimeParts = assetServiceSearchDto.getInsertTime().split(",");
                if (insertTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    insertTimeStart = insertTimeParts[0];
                    insertTimeEnd = insertTimeParts[1];
                }
            }

            // 根据排序方向选择查询方法
            if ("desc".equalsIgnoreCase(assetServiceSearchDto.getOrderDir())) {
                byPage = assetServiceRepository.findByConditionsDesc(
                        assetServiceSearchDto.getId(),
                        assetServiceSearchDto.getSource(),
                        assetServiceSearchDto.getType(),
                        assetServiceSearchDto.getOwner(),
                        assetServiceSearchDto.getStatus(),
                        assetServiceSearchDto.getLabel(),
                        assetServiceSearchDto.getAccess(),
                        assetServiceSearchDto.getLevel(),
                        assetServiceSearchDto.getRisk(),
                        assetServiceSearchDto.getServiceName(),
                        assetServiceSearchDto.getServiceVersion(),
                        assetServiceSearchDto.getServiceType(),
                        assetServiceSearchDto.getRuntimeEnvironment(),
                        assetServiceSearchDto.getDeploymentType(),
                        assetServiceSearchDto.getPort(),
                        assetServiceSearchDto.getProcessName(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            } else {
                byPage = assetServiceRepository.findByConditionsAsc(
                        assetServiceSearchDto.getId(),
                        assetServiceSearchDto.getSource(),
                        assetServiceSearchDto.getType(),
                        assetServiceSearchDto.getOwner(),
                        assetServiceSearchDto.getStatus(),
                        assetServiceSearchDto.getLabel(),
                        assetServiceSearchDto.getAccess(),
                        assetServiceSearchDto.getLevel(),
                        assetServiceSearchDto.getRisk(),
                        assetServiceSearchDto.getServiceName(),
                        assetServiceSearchDto.getServiceVersion(),
                        assetServiceSearchDto.getServiceType(),
                        assetServiceSearchDto.getRuntimeEnvironment(),
                        assetServiceSearchDto.getDeploymentType(),
                        assetServiceSearchDto.getPort(),
                        assetServiceSearchDto.getProcessName(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            }

            List<AssetServiceVo> assetServiceVos = byPage.getContent().stream()
                    .map(AssetServiceVo::fromEntity)
                    .toList();

            return new PageRowsVo<>(assetServiceVos, byPage.getTotalElements());
        } catch (Exception e) {
            log.error("分页查询系统服务资产列表失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0);
        }
    }

    @Override
    public List<String> getDistinctLabels() {
        try {
            return assetServiceRepository.findDistinctLabels();
        } catch (Exception e) {
            log.error("获取系统服务资产标签列表失败", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarIds(String term) {
        try {
            return assetServiceRepository.findLikeIds(term);
        } catch (Exception e) {
            log.error("获取相似系统服务资产ID列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarServiceNames(String term) {
        try {
            return assetServiceRepository.findLikeServiceNames(term);
        } catch (Exception e) {
            log.error("获取相似服务名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarServiceVersions(String term) {
        try {
            return assetServiceRepository.findLikeServiceVersions(term);
        } catch (Exception e) {
            log.error("获取相似服务版本列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarServiceTypes(String term) {
        try {
            return assetServiceRepository.findLikeServiceTypes(term);
        } catch (Exception e) {
            log.error("获取相似服务类型列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarRuntimeEnvironments(String term) {
        try {
            return assetServiceRepository.findLikeRuntimeEnvironments(term);
        } catch (Exception e) {
            log.error("获取相似运行环境列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarDeploymentTypes(String term) {
        try {
            return assetServiceRepository.findLikeDeploymentTypes(term);
        } catch (Exception e) {
            log.error("获取相似部署类型列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarProcessNames(String term) {
        try {
            return assetServiceRepository.findLikeProcessNames(term);
        } catch (Exception e) {
            log.error("获取相似进程名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public long countTotal() {
        return assetServiceRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return assetServiceRepository.countTodayIncrease();
    }

    @Override
    public Map<String, Object> getStatisticsByDateOfWeek() {
        return CommonUtil.getStatisticsMap(assetServiceRepository::countByDateOfWeek);
    }

    @Override
    public Map<String, Object> getStatisticsByRisk() {
        return CommonUtil.getStatisticsMap(assetServiceRepository::countByRisk);
    }

    @Override
    public Map<String, Object> getStatisticsByLevel() {
        return CommonUtil.getStatisticsMap(assetServiceRepository::countByLevel);
    }
} 