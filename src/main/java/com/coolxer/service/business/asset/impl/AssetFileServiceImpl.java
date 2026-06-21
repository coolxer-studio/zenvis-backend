package com.coolxer.service.business.asset.impl;

import com.coolxer.commons.enums.business.asset.AssetRiskLevel;
import com.coolxer.commons.enums.business.asset.AssetSource;
import com.coolxer.dao.clickhouse.entity.asset.AssetFile;
import com.coolxer.dao.clickhouse.repository.asset.AssetFileRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.asset.dto.AssetFileDto;
import com.coolxer.model.business.asset.dto.AssetFileSearchDto;
import com.coolxer.model.business.asset.vo.AssetFileVo;
import com.coolxer.service.business.asset.AssetFileService;
import com.coolxer.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 文件资产服务实现类
 */
@Slf4j
@Service
public class AssetFileServiceImpl implements AssetFileService {

    @Autowired
    private AssetFileRepository assetFileRepository;

    @Override
    public boolean add(AssetFileDto assetFileDto) {
        try {
            AssetFile assetFile = AssetFile.fromDto(assetFileDto);

            // 如果ID为空，生成新的UUID
            if (assetFile.getId() == null || assetFile.getId().isEmpty() || !CommonUtil.isValidUUID(assetFile.getId())) {
                assetFile.setId(UUID.randomUUID().toString());
            }

            // 设置默认值
            if (assetFile.getSource() == null) {
                assetFile.setSource(AssetSource.MANUAL);
            }

            if (assetFile.getRisk() == null) {
                assetFile.setRisk(AssetRiskLevel.LOW);
            }

            if (assetFile.getLabel() == null) {
                assetFile.setLabel(new ArrayList<>());
            }

            if (assetFile.getRiskInfo() == null) {
                assetFile.setRiskInfo("{}");
            }

            if (assetFile.getInfo() == null) {
                assetFile.setInfo("{}");
            }

            assetFile.setUpdateTime(new Date());
            assetFile.setInsertTime(new Date());

            // 保存到数据库
            assetFileRepository.save(assetFile);
            return true;
        } catch (Exception e) {
            log.error("添加文件资产失败", e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assetFileRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除文件资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public boolean deleteALL(List<String> ids) {
        try {
            assetFileRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            log.error("批量删除文件资产失败, ids: {}", ids, e);
            return false;
        }
    }

    @Override
    public boolean update(String id, AssetFileDto assetFileDto) {
        try {
            Optional<AssetFile> optionalAssetFile = assetFileRepository.findById(id);
            if (optionalAssetFile.isPresent()) {
                AssetFile assetFile = optionalAssetFile.get();

                // 更新实体
                AssetFile updatedAssetFile = AssetFile.fromDto(assetFileDto);
                updatedAssetFile.setId(id);
                updatedAssetFile.setUpdateTime(new Date());
                updatedAssetFile.setInsertTime(assetFile.getInsertTime());

                assetFileRepository.save(updatedAssetFile);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新文件资产失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public AssetFileVo getOne(String id) {
        try {
            Optional<AssetFile> optionalAssetFile = assetFileRepository.findById(id);
            return optionalAssetFile.map(AssetFileVo::fromEntity).orElse(null);
        } catch (Exception e) {
            log.error("获取文件资产失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public PageRowsVo<AssetFileVo> getPageList(AssetFileSearchDto assetFileSearchDto) {
        try {
            Pageable pageable = PageRequest.of(assetFileSearchDto.getPage() - 1, assetFileSearchDto.getPerPage());
            Page<AssetFile> byPage;

            // 处理时间字符串
            String updateTimeStart = null;
            String updateTimeEnd = null;
            String insertTimeStart = null;
            String insertTimeEnd = null;

            if (assetFileSearchDto.getUpdateTime() != null && !assetFileSearchDto.getUpdateTime().isEmpty()) {
                String[] updateTimeParts = assetFileSearchDto.getUpdateTime().split(",");
                if (updateTimeParts.length == 2) {
                    updateTimeStart = updateTimeParts[0];
                    updateTimeEnd = updateTimeParts[1];
                }
            }

            if (assetFileSearchDto.getInsertTime() != null && !assetFileSearchDto.getInsertTime().isEmpty()) {
                String[] insertTimeParts = assetFileSearchDto.getInsertTime().split(",");
                if (insertTimeParts.length == 2) {
                    insertTimeStart = insertTimeParts[0];
                    insertTimeEnd = insertTimeParts[1];
                }
            }

            Long creationTimeStart = assetFileSearchDto.getCreationTimeStart() != null ?
                    assetFileSearchDto.getCreationTimeStart() : 0L;

            Long creationTimeEnd = assetFileSearchDto.getCreationTimeEnd() != null ?
                    assetFileSearchDto.getCreationTimeEnd() : 0L;

            Long modificationTimeStart = assetFileSearchDto.getModificationTimeStart() != null ?
                    assetFileSearchDto.getModificationTimeStart() : 0L;

            Long modificationTimeEnd = assetFileSearchDto.getModificationTimeEnd() != null ?
                    assetFileSearchDto.getModificationTimeEnd() : 0L;

            // 根据排序方向选择查询方法
            if ("desc".equalsIgnoreCase(assetFileSearchDto.getOrderDir())) {
                byPage = assetFileRepository.findByConditionsDesc(
                        assetFileSearchDto.getId(),
                        assetFileSearchDto.getSource(),
                        assetFileSearchDto.getType(),
                        assetFileSearchDto.getOwner(),
                        assetFileSearchDto.getStatus(),
                        assetFileSearchDto.getLabel(),
                        assetFileSearchDto.getAccess(),
                        assetFileSearchDto.getLevel(),
                        assetFileSearchDto.getRisk(),
                        assetFileSearchDto.getFileName(),
                        assetFileSearchDto.getFileType(),
                        assetFileSearchDto.getFileFormat(),
                        assetFileSearchDto.getFilePath(),
                        assetFileSearchDto.getFileSize(),
                        creationTimeStart,
                        creationTimeEnd,
                        modificationTimeStart,
                        modificationTimeEnd,
                        assetFileSearchDto.getSourceSystem(),
                        assetFileSearchDto.getFileOwner(),
                        assetFileSearchDto.getPermissions(),
                        assetFileSearchDto.getIsEncrypted(),
                        assetFileSearchDto.getIsCompressed(),
                        assetFileSearchDto.getFileHash(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            } else {
                byPage = assetFileRepository.findByConditionsASC(
                        assetFileSearchDto.getId(),
                        assetFileSearchDto.getSource(),
                        assetFileSearchDto.getType(),
                        assetFileSearchDto.getOwner(),
                        assetFileSearchDto.getStatus(),
                        assetFileSearchDto.getLabel(),
                        assetFileSearchDto.getAccess(),
                        assetFileSearchDto.getLevel(),
                        assetFileSearchDto.getRisk(),
                        assetFileSearchDto.getFileName(),
                        assetFileSearchDto.getFileType(),
                        assetFileSearchDto.getFileFormat(),
                        assetFileSearchDto.getFilePath(),
                        assetFileSearchDto.getFileSize(),
                        creationTimeStart,
                        creationTimeEnd,
                        modificationTimeStart,
                        modificationTimeEnd,
                        assetFileSearchDto.getSourceSystem(),
                        assetFileSearchDto.getFileOwner(),
                        assetFileSearchDto.getPermissions(),
                        assetFileSearchDto.getIsEncrypted(),
                        assetFileSearchDto.getIsCompressed(),
                        assetFileSearchDto.getFileHash(),
                        updateTimeStart,
                        updateTimeEnd,
                        insertTimeStart,
                        insertTimeEnd,
                        pageable
                );
            }

            List<AssetFileVo> assetFileVos = byPage.getContent().stream()
                    .map(AssetFileVo::fromEntity)
                    .toList();

            return new PageRowsVo<>(assetFileVos, byPage.getTotalElements());
        } catch (Exception e) {
            log.error("分页查询文件资产列表失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0);
        }
    }

    @Override
    public List<String> getDistinctLabels() {
        try {
            return assetFileRepository.findDistinctLabels();
        } catch (Exception e) {
            log.error("获取文件资产标签列表失败", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarIds(String term) {
        try {
            return assetFileRepository.findLikeIds(term);
        } catch (Exception e) {
            log.error("获取相似文件资产ID列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarFileNames(String term) {
        try {
            return assetFileRepository.findLikeFileNames(term);
        } catch (Exception e) {
            log.error("获取相似文件名称列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarFileTypes(String term) {
        try {
            return assetFileRepository.findLikeFileTypes(term);
        } catch (Exception e) {
            log.error("获取相似文件类型列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarFileFormats(String term) {
        try {
            return assetFileRepository.findLikeFileFormats(term);
        } catch (Exception e) {
            log.error("获取相似文件格式列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarFilePaths(String term) {
        try {
            return assetFileRepository.findLikeFilePaths(term);
        } catch (Exception e) {
            log.error("获取相似文件路径列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarSourceSystems(String term) {
        try {
            return assetFileRepository.findLikeSourceSystems(term);
        } catch (Exception e) {
            log.error("获取相似源系统列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getSimilarFileOwners(String term) {
        try {
            return assetFileRepository.findLikeFileOwners(term);
        } catch (Exception e) {
            log.error("获取相似文件所有者列表失败, term: {}", term, e);
            return Collections.emptyList();
        }
    }

    @Override
    public long countTotal() {
        return assetFileRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return assetFileRepository.countTodayIncrease();
    }

    @Override
    public Map<String, Object> getStatisticsByDateOfWeek() {
        return CommonUtil.getStatisticsMap(assetFileRepository::countByDateOfWeek);
    }

    @Override
    public Map<String, Object> getStatisticsByRisk() {
        return CommonUtil.getStatisticsMap(assetFileRepository::countByRisk);
    }

    @Override
    public Map<String, Object> getStatisticsByLevel() {
        return CommonUtil.getStatisticsMap(assetFileRepository::countByLevel);
    }
}