package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRiskLevel;
import com.coolxer.commons.enums.business.asset.AssetSource;
import com.coolxer.dao.clickhouse.entity.asset.AssetLog;
import com.coolxer.dao.clickhouse.repository.asset.AssetLogRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetLogDto;
import com.coolxer.model.business.asset.dto.AssetLogSearchDto;
import com.coolxer.model.business.asset.vo.AssetLogVo;
import com.coolxer.service.business.asset.AssetLogService;
import com.coolxer.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 日志资产服务实现类
 */
@Slf4j
@Service
public class AssetLogServiceImpl implements AssetLogService {

    @Autowired
    private AssetLogRepository assetLogRepository;

    @Override
    public boolean add(AssetLogDto assetLogDto) {
        try {
            AssetLog assetLog = AssetLog.fromDto(assetLogDto);

            // 如果ID为空，生成新的UUID
            if (assetLog.getId() == null || assetLog.getId().isEmpty() || !CommonUtil.isValidUUID(assetLog.getId())) {
                assetLog.setId(UUID.randomUUID().toString());
            }

            // 设置默认值
            if (assetLog.getSource() == null) {
                assetLog.setSource(AssetSource.MANUAL);
            }

            if (assetLog.getRisk() == null) {
                assetLog.setRisk(AssetRiskLevel.LOW);
            }

            if (assetLog.getLabel() == null) {
                assetLog.setLabel(new ArrayList<>());
            }

            if (assetLog.getRiskInfo() == null) {
                assetLog.setRiskInfo("{}");
            }

            if (assetLog.getInfo() == null) {
                assetLog.setInfo("{}");
            }

            assetLog.setUpdateTime(new Date());
            assetLog.setInsertTime(new Date());

            // 保存到数据库
            assetLogRepository.save(assetLog);
            return true;
        } catch (Exception e) {
            log.error("添加日志资产失败", e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assetLogRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除日志资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteALL(List<String> ids) {
        try {
            assetLogRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除日志资产失败, ids: {}", ids, e);
            return false;
        }
    }

    @Override
    public boolean update(String id, AssetLogDto assetLogDto) {
        try {
            Optional<AssetLog> optionalAssetLog = assetLogRepository.findById(id);
            if (optionalAssetLog.isPresent()) {
                AssetLog assetLog = optionalAssetLog.get();

                // 更新实体
                AssetLog updatedAssetLog = AssetLog.fromDto(assetLogDto);
                updatedAssetLog.setId(id);
                updatedAssetLog.setUpdateTime(new Date());
                updatedAssetLog.setInsertTime(assetLog.getInsertTime());

                assetLogRepository.save(updatedAssetLog);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新日志资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public AssetLogVo getOne(String id) {
        try {
            Optional<AssetLog> optionalAssetLog = assetLogRepository.findById(id);
            return optionalAssetLog.map(AssetLogVo::fromEntity).orElse(null);
        } catch (Exception e) {
            log.error("获取日志资产失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public PageRowsVo<AssetLogVo> getPageList(AssetLogSearchDto assetLogSearchDto) {
        try {
            Pageable pageable = PageRequest.of(assetLogSearchDto.getPage() - 1, assetLogSearchDto.getPerPage());
            Page<AssetLog> byPage;

            // 处理时间字符串
            String updateTimeStart = null;
            String updateTimeEnd = null;
            String insertTimeStart = null;
            String insertTimeEnd = null;
            String logTimeStart = null;
            String logTimeEnd = null;

            if (assetLogSearchDto.getUpdateTime() != null && !assetLogSearchDto.getUpdateTime().isEmpty()) {
                String[] updateTimeParts = assetLogSearchDto.getUpdateTime().split(",");
                if (updateTimeParts.length == 2) {
                    updateTimeStart = updateTimeParts[0];
                    updateTimeEnd = updateTimeParts[1];
                }
            }

            if (assetLogSearchDto.getInsertTime() != null && !assetLogSearchDto.getInsertTime().isEmpty()) {
                String[] insertTimeParts = assetLogSearchDto.getInsertTime().split(",");
                if (insertTimeParts.length == 2) {
                    insertTimeStart = insertTimeParts[0];
                    insertTimeEnd = insertTimeParts[1];
                }
            }

            logTimeStart = assetLogSearchDto.getLogTimeStart();
            logTimeEnd = assetLogSearchDto.getLogTimeEnd();

            // 根据排序方向选择查询方法
            if ("desc".equalsIgnoreCase(assetLogSearchDto.getOrderDir())) {
                byPage = assetLogRepository.findByConditionsDesc(
                        assetLogSearchDto.getId(),
                        assetLogSearchDto.getSource(),
                        assetLogSearchDto.getType(),
                        assetLogSearchDto.getOwner(),
                        assetLogSearchDto.getStatus(),
                        assetLogSearchDto.getLabel(),
                        assetLogSearchDto.getAccess(),
                        assetLogSearchDto.getLevel(),
                        assetLogSearchDto.getRisk(),
                        assetLogSearchDto.getLogName(),
                        assetLogSearchDto.getLogPath(),
                        assetLogSearchDto.getLogType(),
                        assetLogSearchDto.getLogFormat(),
                        logTimeStart,
                        logTimeEnd,
                        assetLogSearchDto.getLogLevel(),
                        assetLogSearchDto.getProcess(),
                        assetLogSearchDto.getLogMessage(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            } else {
                byPage = assetLogRepository.findByConditionsASC(
                        assetLogSearchDto.getId(),
                        assetLogSearchDto.getSource(),
                        assetLogSearchDto.getType(),
                        assetLogSearchDto.getOwner(),
                        assetLogSearchDto.getStatus(),
                        assetLogSearchDto.getLabel(),
                        assetLogSearchDto.getAccess(),
                        assetLogSearchDto.getLevel(),
                        assetLogSearchDto.getRisk(),
                        assetLogSearchDto.getLogName(),
                        assetLogSearchDto.getLogPath(),
                        assetLogSearchDto.getLogType(),
                        assetLogSearchDto.getLogFormat(),
                        logTimeStart,
                        logTimeEnd,
                        assetLogSearchDto.getLogLevel(),
                        assetLogSearchDto.getProcess(),
                        assetLogSearchDto.getLogMessage(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            }

            List<AssetLogVo> assetLogVos = byPage.getContent().stream()
                    .map(AssetLogVo::fromEntity)
                    .toList();

            return new PageRowsVo<>(assetLogVos, byPage.getTotalElements());
        } catch (Exception e) {
            log.error("分页查询日志资产列表失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0);
        }
    }

    @Override
    public List<String> getDistinctLabels() {
        try {
            return assetLogRepository.findDistinctLabels();
        } catch (Exception e) {
            log.error("获取日志资产标签列表失败", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarIds(String term) {
        try {
            return assetLogRepository.findLikeIds(term);
        } catch (Exception e) {
            log.error("获取相似日志资产ID列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarLogNames(String term) {
        try {
            return assetLogRepository.findLikeLogNames(term);
        } catch (Exception e) {
            log.error("获取相似日志名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarLogPaths(String term) {
        try {
            return assetLogRepository.findLikeLogPaths(term);
        } catch (Exception e) {
            log.error("获取相似日志路径列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarLogTypes(String term) {
        try {
            return assetLogRepository.findLikeLogTypes(term);
        } catch (Exception e) {
            log.error("获取相似日志类型列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarLogFormats(String term) {
        try {
            return assetLogRepository.findLikeLogFormats(term);
        } catch (Exception e) {
            log.error("获取相似日志格式列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarLogLevels(String term) {
        try {
            return assetLogRepository.findLikeLogLevels(term);
        } catch (Exception e) {
            log.error("获取相似日志级别列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarProcesses(String term) {
        try {
            return assetLogRepository.findLikeProcesses(term);
        } catch (Exception e) {
            log.error("获取相似进程信息列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public long countTotal() {
        return assetLogRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return assetLogRepository.countTodayIncrease();
    }

    @Override
    public Map<String, Object> getStatisticsByDateOfWeek() {
        return CommonUtil.getStatisticsMap(assetLogRepository::countByDateOfWeek);
    }

    @Override
    public Map<String, Object> getStatisticsByRisk() {
        return CommonUtil.getStatisticsMap(assetLogRepository::countByRisk);
    }

    @Override
    public Map<String, Object> getStatisticsByLevel() {
        return CommonUtil.getStatisticsMap(assetLogRepository::countByLevel);
    }
} 