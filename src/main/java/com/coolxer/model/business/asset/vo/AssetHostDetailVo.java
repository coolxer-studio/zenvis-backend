package com.coolxer.model.business.asset.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统信息
 */
@Data
public class AssetHostDetailVo implements Serializable {

    private AssetHostVo assetHost;
    private String bootTime;
    private String runningStatus;
    private HealthTrend healthTrend;
    private double cpuRatio;
    private double memoryRatio;
    private double diskRatio;
    private Long diskUsed;
    private Long cpuCore;
    private Long cpuLogicalCore;
    private Long cpuUsed;
    private Long memoryUsed;
    private Long memoryTotal;
    private Long memoryFree;
    private Long memoryBuffer;

    private List<fileDisk> fileDiskList = new ArrayList<>();


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class fileDisk {
        private String name;
        private int ratio;
        private long total;
        private long used;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HealthTrend {
        private List<String> dateList;
        private List<Integer> valueList;
    }
}
