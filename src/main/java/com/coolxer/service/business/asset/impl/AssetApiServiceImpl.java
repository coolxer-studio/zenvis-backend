package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRiskLevel;
import com.coolxer.commons.enums.business.asset.AssetSource;
import com.coolxer.dao.clickhouse.entity.asset.AssetApi;
import com.coolxer.dao.clickhouse.repository.asset.AssetApiRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetApiDto;
import com.coolxer.model.business.asset.dto.AssetApiSearchDto;
import com.coolxer.model.business.asset.vo.AssetApiVo;
import com.coolxer.service.business.asset.AssetApiService;
import com.coolxer.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * RESTful API接口资产服务实现类
 */
@Slf4j
@Service
public class AssetApiServiceImpl implements AssetApiService {

    @Autowired
    private AssetApiRepository assetApiRepository;

    @Override
    public boolean add(AssetApiDto assetApiDto) {
        try {
            // 使用DTO创建实体
            AssetApi assetApi = AssetApi.fromDto(assetApiDto);

            // 如果ID为空，生成新的UUID
            if (assetApi.getId() == null || assetApi.getId().isEmpty() || !CommonUtil.isValidUUID(assetApi.getId())) {
                assetApi.setId(UUID.randomUUID().toString());
            }

            // 设置默认值
            if (assetApi.getSource() == null) {
                assetApi.setSource(AssetSource.MANUAL);
            }

            if (assetApi.getRisk() == null) {
                assetApi.setRisk(AssetRiskLevel.LOW);
            }

            if (assetApi.getLabel() == null) {
                assetApi.setLabel(new ArrayList<>());
            }

            if (assetApi.getRiskInfo() == null) {
                assetApi.setRiskInfo("{}");
            }

            if (assetApi.getInfo() == null) {
                assetApi.setInfo("{}");
            }

            // 设置时间
            Date now = new Date();
            assetApi.setUpdateTime(now);
            assetApi.setInsertTime(now);

            // 保存到数据库
            assetApiRepository.save(assetApi);
            return true;
        } catch (Exception e) {
            log.error("添加API接口资产失败", e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assetApiRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除API接口资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteALL(List<String> ids) {
        try {
            assetApiRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除API接口资产失败, ids: {}", ids, e);
            return false;
        }
    }

    @Override
    public boolean update(String id, AssetApiDto assetApiDto) {
        try {
            Optional<AssetApi> optionalAssetApi = assetApiRepository.findById(id);
            if (optionalAssetApi.isPresent()) {
                AssetApi assetApi = optionalAssetApi.get();

                // 更新实体属性
                AssetApi updatedApi = AssetApi.fromDto(assetApiDto);

                // 保留原有ID和创建时间
                updatedApi.setId(id);
                updatedApi.setInsertTime(assetApi.getInsertTime());
                updatedApi.setUpdateTime(new Date());

                assetApiRepository.save(updatedApi);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新API接口资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public AssetApiVo getOne(String id) {
        try {
            Optional<AssetApi> optionalAssetApi = assetApiRepository.findById(id);
            return optionalAssetApi.map(AssetApiVo::fromEntity).orElse(null);
        } catch (Exception e) {
            log.error("获取API接口资产失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public PageRowsVo<AssetApiVo> getPageList(AssetApiSearchDto assetApiSearchDto) {
        try {
            Pageable pageable = PageRequest.of(assetApiSearchDto.getPage() - 1, assetApiSearchDto.getPerPage());
            Page<AssetApi> byPage;

            // 处理时间字符串
            String updateTimeStart = null;
            String updateTimeEnd = null;
            String insertTimeStart = null;
            String insertTimeEnd = null;

            if (assetApiSearchDto.getUpdateTime() != null && !assetApiSearchDto.getUpdateTime().isEmpty()) {
                String[] updateTimeParts = assetApiSearchDto.getUpdateTime().split(",");
                if (updateTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    updateTimeStart = updateTimeParts[0];
                    updateTimeEnd = updateTimeParts[1];
                }
            }

            if (assetApiSearchDto.getInsertTime() != null && !assetApiSearchDto.getInsertTime().isEmpty()) {
                String[] insertTimeParts = assetApiSearchDto.getInsertTime().split(",");
                if (insertTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    insertTimeStart = insertTimeParts[0];
                    insertTimeEnd = insertTimeParts[1];
                }
            }

            // 根据排序方向选择查询方法
            if ("desc".equalsIgnoreCase(assetApiSearchDto.getOrderDir())) {
                byPage = assetApiRepository.findByConditionsDesc(
                        assetApiSearchDto.getId(),
                        assetApiSearchDto.getSource(),
                        assetApiSearchDto.getType(),
                        assetApiSearchDto.getOwner(),
                        assetApiSearchDto.getStatus(),
                        assetApiSearchDto.getLabel(),
                        assetApiSearchDto.getAccess(),
                        assetApiSearchDto.getLevel(),
                        assetApiSearchDto.getRisk(),
                        assetApiSearchDto.getApiName(),
                        assetApiSearchDto.getApiVersion(),
                        assetApiSearchDto.getApiPath(),
                        assetApiSearchDto.getHttpMethod(),
                        assetApiSearchDto.getContentType(),
                        assetApiSearchDto.getAuthenticationType(),
                        assetApiSearchDto.getServiceId(),
                        assetApiSearchDto.getIsDeprecated(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            } else {
                byPage = assetApiRepository.findByConditionsAsc(
                        assetApiSearchDto.getId(),
                        assetApiSearchDto.getSource(),
                        assetApiSearchDto.getType(),
                        assetApiSearchDto.getOwner(),
                        assetApiSearchDto.getStatus(),
                        assetApiSearchDto.getLabel(),
                        assetApiSearchDto.getAccess(),
                        assetApiSearchDto.getLevel(),
                        assetApiSearchDto.getRisk(),
                        assetApiSearchDto.getApiName(),
                        assetApiSearchDto.getApiVersion(),
                        assetApiSearchDto.getApiPath(),
                        assetApiSearchDto.getHttpMethod(),
                        assetApiSearchDto.getContentType(),
                        assetApiSearchDto.getAuthenticationType(),
                        assetApiSearchDto.getServiceId(),
                        assetApiSearchDto.getIsDeprecated(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            }

            List<AssetApiVo> assetApiVos = byPage.getContent().stream()
                    .map(AssetApiVo::fromEntity)
                    .toList();

            return new PageRowsVo<>(assetApiVos, byPage.getTotalElements());
        } catch (Exception e) {
            log.error("分页查询API接口资产列表失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0);
        }
    }

    @Override
    public List<String> getDistinctLabels() {
        try {
            return assetApiRepository.findDistinctLabels();
        } catch (Exception e) {
            log.error("获取API接口资产标签列表失败", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarIds(String term) {
        try {
            return assetApiRepository.findLikeIds(term);
        } catch (Exception e) {
            log.error("获取相似API接口资产ID列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarApiNames(String term) {
        try {
            return assetApiRepository.findLikeApiNames(term);
        } catch (Exception e) {
            log.error("获取相似API名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarApiVersions(String term) {
        try {
            return assetApiRepository.findLikeApiVersions(term);
        } catch (Exception e) {
            log.error("获取相似API版本列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarApiPaths(String term) {
        try {
            return assetApiRepository.findLikeApiPaths(term);
        } catch (Exception e) {
            log.error("获取相似API路径列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarHttpMethods(String term) {
        try {
            return assetApiRepository.findLikeHttpMethods(term);
        } catch (Exception e) {
            log.error("获取相似HTTP方法列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarContentTypes(String term) {
        try {
            return assetApiRepository.findLikeContentTypes(term);
        } catch (Exception e) {
            log.error("获取相似内容类型列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarAuthenticationTypes(String term) {
        try {
            return assetApiRepository.findLikeAuthenticationTypes(term);
        } catch (Exception e) {
            log.error("获取相似认证类型列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<AssetApiVo> getByServiceId(String serviceId) {
        try {
            List<AssetApi> apis = assetApiRepository.findByServiceId(serviceId);
            return apis.stream()
                    .map(AssetApiVo::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("根据服务ID获取API接口列表失败, serviceId: {}", serviceId, e);
            return Collections.emptyList();
        }
    }

    @Override
    public long countTotal() {
        return assetApiRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return assetApiRepository.countTodayIncrease();
    }

    @Override
    public Map<String, Object> getStatisticsByDateOfWeek() {
        return CommonUtil.getStatisticsMap(assetApiRepository::countByDateOfWeek);
    }

    @Override
    public Map<String, Object> getStatisticsByRisk() {
        return CommonUtil.getStatisticsMap(assetApiRepository::countByRisk);
    }

    @Override
    public Map<String, Object> getStatisticsByLevel() {
        return CommonUtil.getStatisticsMap(assetApiRepository::countByLevel);
    }
} 