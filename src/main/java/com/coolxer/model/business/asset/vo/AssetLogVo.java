package com.coolxer.model.business.asset.vo;

import com.coolxer.dao.clickhouse.entity.asset.AssetLog;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 日志资产视图对象
 */
@Data
public class AssetLogVo implements Serializable {

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
    private String logName;
    private String logPath;
    private String logType;
    private String logFormat;
    private Date logTime;
    private String logLevel;
    private String process;
    private String logMessage;

    /**
     * 从实体转换为视图对象
     */
    public static AssetLogVo fromEntity(AssetLog entity) {
        if (entity == null) {
            return null;
        }

        AssetLogVo vo = new AssetLogVo();
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

        // 日志资产特有字段
        vo.setLogName(entity.getLogName());
        vo.setLogPath(entity.getLogPath());
        vo.setLogType(entity.getLogType());
        vo.setLogFormat(entity.getLogFormat());
        vo.setLogTime(entity.getLogTime());
        vo.setLogLevel(entity.getLogLevel());
        vo.setProcess(entity.getProcess());
        vo.setLogMessage(entity.getLogMessage());

        return vo;
    }
} 