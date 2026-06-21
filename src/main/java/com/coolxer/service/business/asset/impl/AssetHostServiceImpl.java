package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRiskLevel;
import com.coolxer.commons.enums.business.asset.AssetSource;
import com.coolxer.dao.clickhouse.entity.asset.AssetHost;
import com.coolxer.dao.clickhouse.repository.asset.AssetHostRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetHostDto;
import com.coolxer.model.business.asset.dto.AssetHostSearchDto;
import com.coolxer.model.business.asset.vo.AssetHostVo;
import com.coolxer.service.business.asset.AssetHostService;
import com.coolxer.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 资产主机服务实现类
 */
@Slf4j
@Service
public class AssetHostServiceImpl implements AssetHostService {

    @Autowired
    private AssetHostRepository assetHostRepository;

    @Override
    public boolean add(AssetHostDto assetHostDto) {
        try {
            AssetHost assetHost = new AssetHost();
            // 设置基础属性
            String id = assetHostDto.getId();
            if (id == null || id.isEmpty() || !CommonUtil.isValidUUID(id)) {
                id = UUID.randomUUID().toString();
            }
            assetHost.setId(id);
            assetHost.setSource(AssetSource.MANUAL);
            assetHost.setRisk(AssetRiskLevel.LOW);
            assetHost.setLabel(new ArrayList<>());
            assetHost.setRiskInfo("{}");
            assetHost.setInfo("{}");
            assetHost.setNetType("-");
            assetHost.setUpdateTime(new Date());
            assetHost.setInsertTime(new Date());
            // 从DTO更新其他属性
            assetHost.updateFromDto(assetHostDto);

            // 保存到数据库
            assetHostRepository.save(assetHost);
            return true;
        } catch (Exception e) {
            log.error("添加资产主机失败", e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assetHostRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除资产主机失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteALL(List<String> ids) {
        try {
            assetHostRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除资产主机失败, ids: {}", ids, e);
            return false;
        }
    }

    @Override
    public boolean update(String id, AssetHostDto assetHostDto) {
        try {
            Optional<AssetHost> optionalAssetHost = assetHostRepository.findById(id);
            if (optionalAssetHost.isPresent()) {
                AssetHost assetHost = optionalAssetHost.get();
                assetHost.updateFromDto(assetHostDto);
                assetHostRepository.save(assetHost);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新资产主机失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public AssetHostVo getOne(String id) {
        try {
            Optional<AssetHost> optionalAssetHost = assetHostRepository.findById(id);
            return optionalAssetHost.map(AssetHostVo::new).orElse(null);
        } catch (Exception e) {
            log.error("获取资产主机失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public PageRowsVo<AssetHostVo> getPageList(AssetHostSearchDto assetHostSearchDto) {
        try {
            Pageable pageable = PageRequest.of(assetHostSearchDto.getPage() - 1, assetHostSearchDto.getPerPage());
            Page<AssetHost> byPage;

            // 处理时间字符串
            String updateTimeStart = null;
            String updateTimeEnd = null;
            String insertTimeStart = null;
            String insertTimeEnd = null;

            if (assetHostSearchDto.getUpdateTime() != null && !assetHostSearchDto.getUpdateTime().isEmpty()) {
                String[] updateTimeParts = assetHostSearchDto.getUpdateTime().split(",");
                if (updateTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    updateTimeStart = updateTimeParts[0];
                    updateTimeEnd = updateTimeParts[1];
                }
            }

            if (assetHostSearchDto.getInsertTime() != null && !assetHostSearchDto.getInsertTime().isEmpty()) {
                String[] insertTimeParts = assetHostSearchDto.getInsertTime().split(",");
                if (insertTimeParts.length == 2) {
                    // 将时间戳转换为日期时间格式
                    insertTimeStart = insertTimeParts[0];
                    insertTimeEnd = insertTimeParts[1];
                }
            }

            // 根据排序方向选择查询方法
            if ("desc".equalsIgnoreCase(assetHostSearchDto.getOrderDir())) {
                byPage = assetHostRepository.findByPageDesc(
                        pageable,
                        assetHostSearchDto.getId(),
                        assetHostSearchDto.getSource(),
                        assetHostSearchDto.getType(),
                        assetHostSearchDto.getOwner(),
                        assetHostSearchDto.getStatus(),
                        assetHostSearchDto.getAreaCode(),
                        assetHostSearchDto.getLabel(),
                        assetHostSearchDto.getAccess(),
                        assetHostSearchDto.getLevel(),
                        assetHostSearchDto.getRisk(),
                        assetHostSearchDto.getRoom(),
                        assetHostSearchDto.getCabinetNo(),
                        assetHostSearchDto.getPositionNo(),
                        assetHostSearchDto.getManufacturer(),
                        assetHostSearchDto.getModel(),
                        assetHostSearchDto.getArchitecture(),
                        assetHostSearchDto.getSystemName(),
                        assetHostSearchDto.getSystemVersion(),
                        assetHostSearchDto.getCpuModel(),
                        assetHostSearchDto.getCpuCores(),
                        assetHostSearchDto.getMemorySize(),
                        assetHostSearchDto.getDiskSize(),
                        assetHostSearchDto.getNetType(),
                        assetHostSearchDto.getLanIp(),
                        assetHostSearchDto.getWanIp(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd
                );
            } else {
                byPage = assetHostRepository.findByPageAsc(
                        pageable,
                        assetHostSearchDto.getId(),
                        assetHostSearchDto.getSource(),
                        assetHostSearchDto.getType(),
                        assetHostSearchDto.getOwner(),
                        assetHostSearchDto.getStatus(),
                        assetHostSearchDto.getAreaCode(),
                        assetHostSearchDto.getLabel(),
                        assetHostSearchDto.getAccess(),
                        assetHostSearchDto.getLevel(),
                        assetHostSearchDto.getRisk(),
                        assetHostSearchDto.getRoom(),
                        assetHostSearchDto.getCabinetNo(),
                        assetHostSearchDto.getPositionNo(),
                        assetHostSearchDto.getManufacturer(),
                        assetHostSearchDto.getModel(),
                        assetHostSearchDto.getArchitecture(),
                        assetHostSearchDto.getSystemName(),
                        assetHostSearchDto.getSystemVersion(),
                        assetHostSearchDto.getCpuModel(),
                        assetHostSearchDto.getCpuCores(),
                        assetHostSearchDto.getMemorySize(),
                        assetHostSearchDto.getDiskSize(),
                        assetHostSearchDto.getNetType(),
                        assetHostSearchDto.getLanIp(),
                        assetHostSearchDto.getWanIp(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd
                );
            }

            return new PageRowsVo<>(
                    byPage.getContent().stream().map(AssetHostVo::new).toList(),
                    byPage.getTotalElements()
            );
        } catch (Exception e) {
            log.error("分页查询资产主机失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0L);
        }
    }

    @Override
    public List<String> getDistinctLabels() {
        try {
            return assetHostRepository.findDistinctLabels();
        } catch (Exception e) {
            log.error("获取标签列表失败", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarIds(String term) {
        try {
            return assetHostRepository.findLikeIds(term);
        } catch (Exception e) {
            log.error("获取相似ID列表失败, searchId: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarManufacturers(String term) {
        try {
            return assetHostRepository.findLikeManufacturers(term);
        } catch (Exception e) {
            log.error("获取制造商名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarModels(String term) {
        try {
            return assetHostRepository.findLikeModels(term);
        } catch (Exception e) {
            log.error("获取型号列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarArchitectures(String term) {
        try {
            return assetHostRepository.findLikeArchitectures(term);
        } catch (Exception e) {
            log.error("获取架构列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarSystemNames(String term) {
        try {
            return assetHostRepository.findLikeSystemNames(term);
        } catch (Exception e) {
            log.error("获取系统名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarSystemVersions(String term) {
        try {
            return assetHostRepository.findLikeSystemVersions(term);
        } catch (Exception e) {
            log.error("获取系统版本列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarCpuModels(String term) {
        try {
            return assetHostRepository.findLikeCpuModels(term);
        } catch (Exception e) {
            log.error("获取CPU型号列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    // 统计总数
    @Override
    public long countTotal() {
        return assetHostRepository.countTotal();
    }

    // 统计今日新增
    @Override
    public long countIncrease() {
        return assetHostRepository.countTodayIncrease();
    }

    @Override
    public Map<String, Object> getStatisticsByDateOfWeek() {
        return CommonUtil.getStatisticsMap(assetHostRepository::countByDateOfWeek);
    }

    @Override
    public Map<String, Object> getStatisticsByRisk() {
        return CommonUtil.getStatisticsMap(assetHostRepository::countByRisk);
    }

    @Override
    public Map<String, Object> getStatisticsByLevel() {
        return CommonUtil.getStatisticsMap(assetHostRepository::countByLevel);
    }
}
