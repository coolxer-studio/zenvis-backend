package com.coolxer.model.business.asset.vo;

import com.coolxer.dao.clickhouse.entity.asset.AssetFile;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文件资产视图对象
 */
@Data
public class AssetFileVo implements Serializable {

    private String id;
    private String source;
    private String type;
    private String owner;
    private String status;
    private String label;
    private Boolean access;
    private String level;
    private String risk;
    private String riskInfo;
    private String info;
    private Date updateTime;
    private Date insertTime;
    private String fileName;
    private String fileType;
    private String fileFormat;
    private String filePath;
    private Long fileSize;
    private Long creationTime;
    private Long modificationTime;
    private String sourceSystem;
    private String fileOwner;
    private String permissions;
    private Boolean isEncrypted;
    private Boolean isCompressed;
    private String fileHash;

    /**
     * 从实体转换为视图对象
     */
    public static AssetFileVo fromEntity(AssetFile entity) {
        if (entity == null) {
            return null;
        }

        AssetFileVo vo = new AssetFileVo();
        vo.setId(entity.getId());
        vo.setSource(entity.getSource() != null ? entity.getSource().getDescription() : null);
        vo.setType(entity.getType() != null ? entity.getType().getDescription() : null);
        vo.setOwner(entity.getOwner() != null ? entity.getOwner().getDescription() : null);
        vo.setStatus(entity.getStatus() != null ? entity.getStatus().getDescription() : null);
        vo.setAccess(entity.getAccess());
        vo.setLevel(entity.getLevel() != null ? entity.getLevel().getDescription() : null);
        vo.setRisk(entity.getRisk() != null ? entity.getRisk().getDescription() : null);
        vo.setRiskInfo(entity.getRiskInfo());
        vo.setInfo(entity.getInfo());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setInsertTime(entity.getInsertTime());

        // 处理标签
        List<String> labelList = entity.getLabel();
        if (labelList != null && !labelList.isEmpty()) {
            vo.setLabel(String.join(",", labelList));
        } else {
            vo.setLabel("");
        }

        // 文件资产特有字段
        vo.setFileName(entity.getFileName());
        vo.setFileType(entity.getFileType());
        vo.setFileFormat(entity.getFileFormat());
        vo.setFilePath(entity.getFilePath());
        vo.setFileSize(entity.getFileSize());
        vo.setCreationTime(entity.getCreationTime());
        vo.setModificationTime(entity.getModificationTime());
        vo.setSourceSystem(entity.getSourceSystem());
        vo.setFileOwner(entity.getFileOwner());
        vo.setPermissions(entity.getPermissions());
        vo.setIsEncrypted(entity.getIsEncrypted());
        vo.setIsCompressed(entity.getIsCompressed());
        vo.setFileHash(entity.getFileHash());

        return vo;
    }
}