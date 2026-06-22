package com.coolxer.model.business.asset.dto;

import com.coolxer.commons.enums.business.asset.*;
import lombok.Data;

/**
 * 文件资产数据传输对象
 */
@Data
public class AssetFileDto {

    /**
     * 资产ID，资产的唯一编号信息
     */
    private String id;

    /**
     * 资产来源
     */
    private AssetSource source;

    /**
     * 资产类型
     */
    private AssetType type;

    /**
     * 资产属主（企业、终端客户）
     */
    private AssetOwner owner;

    /**
     * 资产状态，在网、停用、下线、删除
     */
    private AssetStatus status;

    /**
     * 资产标签，以逗号分隔
     */
    private String label;

    /**
     * 资产是否开放
     */
    private Boolean access;

    /**
     * 资产重要等级
     */
    private AssetLevel level;

    /**
     * 风险等级
     */
    private AssetRiskLevel risk;

    /**
     * 风险信息，漏洞详情等，json格式
     */
    private String riskInfo;

    /**
     * 资产信息，不同的资产信息不一样，json格式
     */
    private String info;

    /**
     * 城市信息
     */
    private CityInfo cityInfo;

    // 文件资产特有字段
    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件类型（文档、图片、视频、数据库等）
     */
    private String fileType;

    /**
     * 文件格式（扩展名）
     */
    private String fileFormat;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件大小（KB）
     */
    private Long fileSize;

    /**
     * 文件创建时间（时间戳）
     */
    private Long creationTime;

    /**
     * 文件修改时间（时间戳）
     */
    private Long modificationTime;

    /**
     * 文件所属系统
     */
    private String sourceSystem;

    /**
     * 文件所有者
     */
    private String fileOwner;

    /**
     * 文件权限
     */
    private String permissions;

    /**
     * 是否加密
     */
    private Boolean isEncrypted;

    /**
     * 是否压缩
     */
    private Boolean isCompressed;

    /**
     * 文件哈希值（MD5/SHA等）
     */
    private String fileHash;
} 