package com.coolxer.model.system.vo;

import com.coolxer.commons.enums.PluginStatusType;
import com.coolxer.dao.mysql.entity.Plugin;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 插件传输对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PluginVo implements Serializable {

    /**
     * id
     */
    private int id;

    /**
     * 插件名
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 版本
     */
    private String version;

    /**
     * 插件简介
     */
    private String description;

    /**
     * 作者
     */
    private String author;

    /**
     * 状态
     */
    @Column
    private PluginStatusType status;


    /**
     * 插件包路径
     */
    private String pluginPath;

    /**
     * 更新时间
     */
    private Date updateTime;

    public PluginVo(Plugin plugin) {
        this.id = plugin.getId();
        this.name = plugin.getName();
        this.icon = plugin.getIcon();
        this.packageName = plugin.getPackageName();
        this.version = plugin.getVersion();
        this.description = plugin.getDescription();
        this.author = plugin.getAuthor();
        this.status = plugin.getStatus();
        this.pluginPath = plugin.getPluginPath();
        this.updateTime = plugin.getUpdateTime();
    }

}
