package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRiskLevel;
import com.coolxer.commons.enums.business.asset.AssetSource;
import com.coolxer.dao.clickhouse.entity.asset.AssetProbe;
import com.coolxer.dao.clickhouse.repository.asset.AssetProbeRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetProbeDto;
import com.coolxer.model.business.asset.dto.AssetProbeSearchDto;
import com.coolxer.model.business.asset.vo.AssetProbeVo;
import com.coolxer.service.business.asset.AssetProbeService;
import com.coolxer.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 数据探针SDK资产服务实现类
 */
@Slf4j
@Service
public class AssetProbeServiceImpl implements AssetProbeService {

    @Autowired
    private AssetProbeRepository assetProbeRepository;

    @Override
    public boolean add(AssetProbeDto assetProbeDto) {
        try {
            AssetProbe assetProbe = new AssetProbe();
            // 设置基础属性
            String id = assetProbeDto.getId();
            if (id == null || id.isEmpty() || !CommonUtil.isValidUUID(id)) {
                id = UUID.randomUUID().toString();
            }
            assetProbe.setId(id);
            assetProbe.setSource(AssetSource.MANUAL);
            assetProbe.setRisk(AssetRiskLevel.LOW);
            assetProbe.setLabel(new ArrayList<>());
            assetProbe.setRiskInfo("{}");
            assetProbe.setInfo("{}");
            assetProbe.setUpdateTime(new Date());
            assetProbe.setInsertTime(new Date());
            // 从DTO更新其他属性
            assetProbe.updateFromDto(assetProbeDto);

            // 保存到数据库
            assetProbeRepository.save(assetProbe);
            return true;
        } catch (Exception e) {
            log.error("添加数据探针SDK资产失败", e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assetProbeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除数据探针SDK资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteALL(List<String> ids) {
        try {
            assetProbeRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除数据探针SDK资产失败, ids: {}", ids, e);
            return false;
        }
    }

    @Override
    public boolean update(String id, AssetProbeDto assetProbeDto) {
        try {
            Optional<AssetProbe> optionalAssetProbe = assetProbeRepository.findById(id);
            if (optionalAssetProbe.isPresent()) {
                AssetProbe assetProbe = optionalAssetProbe.get();
                assetProbe.updateFromDto(assetProbeDto);
                assetProbeRepository.save(assetProbe);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新数据探针SDK资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public AssetProbeVo getOne(String id) {
        try {
            Optional<AssetProbe> optionalAssetProbe = assetProbeRepository.findById(id);
            return optionalAssetProbe.map(AssetProbeVo::new).orElse(null);
        } catch (Exception e) {
            log.error("获取数据探针SDK资产失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public PageRowsVo<AssetProbeVo> getPageList(AssetProbeSearchDto searchDto) {
        try {
            // 创建分页请求
            Pageable pageable = PageRequest.of(searchDto.getPage() - 1, searchDto.getPerPage());

            // 处理时间字符串
            String updateTimeStart = null;
            String updateTimeEnd = null;
            String insertTimeStart = null;
            String insertTimeEnd = null;

            if (searchDto.getUpdateTime() != null && !searchDto.getUpdateTime().isEmpty()) {
                String[] updateTimeParts = searchDto.getUpdateTime().split(",");
                if (updateTimeParts.length == 2) {
                    updateTimeStart = updateTimeParts[0];
                    updateTimeEnd = updateTimeParts[1];
                }
            }

            if (searchDto.getInsertTime() != null && !searchDto.getInsertTime().isEmpty()) {
                String[] insertTimeParts = searchDto.getInsertTime().split(",");
                if (insertTimeParts.length == 2) {
                    insertTimeStart = insertTimeParts[0];
                    insertTimeEnd = insertTimeParts[1];
                }
            }

            // 执行查询
            Page<AssetProbe> page;

            // 根据排序方向选择查询方法
            // 根据排序方向选择查询方法
            if ("desc".equalsIgnoreCase(searchDto.getOrderDir())) {
                page = assetProbeRepository.findByConditionsDesc(
                        searchDto.getId(),
                        searchDto.getSource(),
                        searchDto.getType(),
                        searchDto.getOwner(),
                        searchDto.getStatus(),
                        searchDto.getLabel(),
                        searchDto.getAccess(),
                        searchDto.getLevel(),
                        searchDto.getRisk(),
                        searchDto.getProbeName(),
                        searchDto.getProbeVersion(),
                        searchDto.getProbeType(),
                        searchDto.getLanguage(),
                        searchDto.getFramework(),
                        searchDto.getCompatibleVersions(),
                        searchDto.getDataCollectionTypes(),
                        searchDto.getEncryptionMethod(),
                        searchDto.getAuthenticationMethod(),
                        searchDto.getDataTransmissionProtocol(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            } else {
                page = assetProbeRepository.findByConditionsASC(
                        searchDto.getId(),
                        searchDto.getSource(),
                        searchDto.getType(),
                        searchDto.getOwner(),
                        searchDto.getStatus(),
                        searchDto.getLabel(),
                        searchDto.getAccess(),
                        searchDto.getLevel(),
                        searchDto.getRisk(),
                        searchDto.getProbeName(),
                        searchDto.getProbeVersion(),
                        searchDto.getProbeType(),
                        searchDto.getLanguage(),
                        searchDto.getFramework(),
                        searchDto.getCompatibleVersions(),
                        searchDto.getDataCollectionTypes(),
                        searchDto.getEncryptionMethod(),
                        searchDto.getAuthenticationMethod(),
                        searchDto.getDataTransmissionProtocol(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            }

            // 转换为VO
            List<AssetProbeVo> voList = page.getContent().stream()
                    .map(AssetProbeVo::new)
                    .toList();

            // 构建返回结果
            return new PageRowsVo<>(voList, page.getTotalElements());
        } catch (Exception e) {
            log.error("分页查询数据探针SDK资产列表失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0);
        }
    }

    @Override
    public List<String> getDistinctLabels() {
        try {
            return assetProbeRepository.findDistinctLabels();
        } catch (Exception e) {
            log.error("获取数据探针SDK资产标签列表失败", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarIds(String term) {
        try {
            return assetProbeRepository.findLikeIds(term);
        } catch (Exception e) {
            log.error("获取相似数据探针SDK资产ID列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarProbeNames(String term) {
        try {
            return assetProbeRepository.findLikeProbeNames(term);
        } catch (Exception e) {
            log.error("获取相似探针名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarProbeTypes(String term) {
        try {
            return assetProbeRepository.findLikeProbeTypes(term);
        } catch (Exception e) {
            log.error("获取相似探针类型列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarLanguages(String term) {
        try {
            return assetProbeRepository.findLikeLanguages(term);
        } catch (Exception e) {
            log.error("获取相似开发语言列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarFrameworks(String term) {
        try {
            return assetProbeRepository.findLikeFrameworks(term);
        } catch (Exception e) {
            log.error("获取相似框架列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarEncryptionMethods(String term) {
        try {
            return assetProbeRepository.findLikeEncryptionMethods(term);
        } catch (Exception e) {
            log.error("获取相似加密方式列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarAuthenticationMethods(String term) {
        try {
            return assetProbeRepository.findLikeAuthenticationMethods(term);
        } catch (Exception e) {
            log.error("获取相似认证方式列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarDataTransmissionProtocols(String term) {
        try {
            return assetProbeRepository.findLikeDataTransmissionProtocols(term);
        } catch (Exception e) {
            log.error("获取相似数据传输协议列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public long countTotal() {
        return assetProbeRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return assetProbeRepository.countTodayIncrease();
    }

    @Override
    public Map<String, Object> getStatisticsByDateOfWeek() {
        return CommonUtil.getStatisticsMap(assetProbeRepository::countByDateOfWeek);
    }

    @Override
    public Map<String, Object> getStatisticsByRisk() {
        return CommonUtil.getStatisticsMap(assetProbeRepository::countByRisk);
    }

    @Override
    public Map<String, Object> getStatisticsByLevel() {
        return CommonUtil.getStatisticsMap(assetProbeRepository::countByLevel);
    }

} 