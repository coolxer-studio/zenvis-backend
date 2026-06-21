package com.coolxer.model.business.asset.dto;

import com.coolxer.model.base.dto.SortPageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件资产搜索条件数据传输对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetFileSearchDto extends SortPageDto {
    /**
     * 资产ID，资产的唯一编号信息
     */
    private String id;

    /**
     * 资产来源
     */
    private String source;

    /**
     * 资产类型
     */
    private String type;

    /**
     * 资产属主
     */
    private String owner;

    /**
     * 资产状态
     */
    private String status;

    /**
     * 资产标签，多个标签用逗号分隔
     */
    private String label;

    /**
     * 资产是否开放
     */
    private Boolean access;

    /**
     * 资产重要等级
     */
    private String level;

    /**
     * 风险等级
     */
    private String risk;
    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件格式
     */
    private String fileFormat;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件创建时间范围（开始时间戳）
     */
    private Long creationTimeStart;

    /**
     * 文件创建时间范围（结束时间戳）
     */
    private Long creationTimeEnd;

    /**
     * 文件修改时间范围（开始时间戳）
     */
    private Long modificationTimeStart;

    /**
     * 文件修改时间范围（结束时间戳）
     */
    private Long modificationTimeEnd;

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
     * 文件哈希值
     */
    private String fileHash;

    /**
     * 活跃时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String insertTime;

} 