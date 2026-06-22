package com.coolxer.dao.clickhouse.entity.asset;

import com.coolxer.commons.enums.business.asset.*;
import com.coolxer.dao.clickhouse.constant.ClickhouseFinalTableName;
import com.coolxer.dao.clickhouse.converter.StringListConverter;
import com.coolxer.model.business.asset.dto.AssetFileDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 文件资产表实体类
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = ClickhouseFinalTableName.T_ASSET_FILE)
public class AssetFile {

    /**
     * 资产ID，资产的唯一编号信息
     */
    @Id
    private String id;

    /**
     * 资产来源
     */
    @Enumerated(EnumType.STRING)
    private AssetSource source;

    /**
     * 资产类型
     */
    @Enumerated(EnumType.STRING)
    private AssetType type;

    /**
     * 资产属主（企业、终端客户）
     */
    @Enumerated(EnumType.STRING)
    private AssetOwner owner;

    /**
     * 资产状态，在网、停用、下线、删除
     */
    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    /**
     * 资产标签
     */
    @Convert(converter = StringListConverter.class)
    private List<String> label;

    /**
     * 资产是否开放
     */
    private Boolean access;

    /**
     * 资产重要等级
     */
    @Enumerated(EnumType.STRING)
    private AssetLevel level;

    /**
     * 风险等级
     */
    @Enumerated(EnumType.STRING)
    private AssetRiskLevel risk;

    /**
     * 风险信息，漏洞详情等，json格式
     */
    @Column(name = "risk_info")
    private String riskInfo;

    /**
     * 资产信息，不同的资产信息不一样，json格式
     */
    private String info;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "insert_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertTime;

    // 文件资产特有字段
    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件类型（文档、图片、视频、数据库等）
     */
    @Column(name = "file_type")
    private String fileType;

    /**
     * 文件格式（扩展名）
     */
    @Column(name = "file_format")
    private String fileFormat;

    /**
     * 文件路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 文件大小（KB）
     */
    @Column(name = "file_size")
    private Long fileSize;

    /**
     * 文件创建时间（时间戳）
     */
    @Column(name = "creation_time")
    private Long creationTime;

    /**
     * 文件修改时间（时间戳）
     */
    @Column(name = "modification_time")
    private Long modificationTime;

    /**
     * 文件所属系统
     */
    @Column(name = "source_system")
    private String sourceSystem;

    /**
     * 文件所有者
     */
    @Column(name = "file_owner")
    private String fileOwner;

    /**
     * 文件权限
     */
    private String permissions;

    /**
     * 是否加密
     */
    @Column(name = "is_encrypted")
    private Boolean isEncrypted;

    /**
     * 是否压缩
     */
    @Column(name = "is_compressed")
    private Boolean isCompressed;

    /**
     * 文件哈希值（MD5/SHA等）
     */
    @Column(name = "file_hash")
    private String fileHash;

    /**
     * 从DTO转换为实体
     */
    public static AssetFile fromDto(AssetFileDto dto) {
        if (dto == null) {
            return null;
        }

        AssetFile entity = new AssetFile();
        entity.setId(dto.getId());
        entity.setSource(dto.getSource());
        entity.setType(dto.getType());
        entity.setOwner(dto.getOwner());
        entity.setStatus(dto.getStatus());
        entity.setAccess(dto.getAccess());
        entity.setLevel(dto.getLevel());
        entity.setRisk(dto.getRisk());
        entity.setRiskInfo(dto.getRiskInfo());
        entity.setInfo(dto.getInfo());

        // 处理标签
        if (dto.getLabel() != null && !dto.getLabel().isEmpty()) {
            entity.setLabel(Arrays.asList(dto.getLabel().split(",")));
        } else {
            entity.setLabel(new ArrayList<>());
        }

        // 文件资产特有字段
        entity.setFileName(dto.getFileName());
        entity.setFileType(dto.getFileType());
        entity.setFileFormat(dto.getFileFormat());
        entity.setFilePath(dto.getFilePath());
        entity.setFileSize(dto.getFileSize());
        entity.setCreationTime(dto.getCreationTime());
        entity.setModificationTime(dto.getModificationTime());
        entity.setSourceSystem(dto.getSourceSystem());
        entity.setFileOwner(dto.getFileOwner());
        entity.setPermissions(dto.getPermissions());
        entity.setIsEncrypted(dto.getIsEncrypted());
        entity.setIsCompressed(dto.getIsCompressed());
        entity.setFileHash(dto.getFileHash());

        entity.setUpdateTime(new Date());
        entity.setInsertTime(new Date());

        return entity;
    }
} 