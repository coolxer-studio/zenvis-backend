package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.business.asset.*;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.vo.AssetCountVo;
import com.coolxer.model.business.asset.vo.AssetLevelCountVo;
import com.coolxer.model.business.asset.vo.AssetRiskCountVo;
import com.coolxer.service.business.asset.*;
import com.coolxer.utils.CommonUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 资产概览
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/v1/asset")
public class AssetOverallController extends BaseController {

    @Autowired
    private AssetHostService assetHostService;
    @Autowired
    private AssetMobileService assetMobileService;
    @Autowired
    private AssetPcService assetPcService;
    @Autowired
    private AssetIotService assetIotService;
    @Autowired
    private AssetProbeService assetProbeService;
    @Autowired
    private AssetAppService assetAppService;
    @Autowired
    private AssetServiceService assetServiceService;
    @Autowired
    private AssetApiService assetApiService;
    @Autowired
    private AssetLogService assetLogService;
    @Autowired
    private AssetFileService assetFileService;

    @GetMapping({"/count"})
    public ResponseWrap<?> count() {
        try {
            AssetCountVo vo = new AssetCountVo();
            vo.setHostTotal(assetHostService.countTotal());
            vo.setHostIncrease(assetHostService.countIncrease());
            vo.setMobileTotal(assetMobileService.countTotal());
            vo.setMobileIncrease(assetMobileService.countIncrease());
            vo.setPcTotal(assetPcService.countTotal());
            vo.setPcIncrease(assetPcService.countIncrease());
            vo.setIotTotal(assetIotService.countTotal());
            vo.setIotIncrease(assetIotService.countIncrease());
            vo.setProbeTotal(assetProbeService.countTotal());
            vo.setProbeIncrease(assetProbeService.countIncrease());
            vo.setAppTotal(assetAppService.countTotal());
            vo.setAppIncrease(assetAppService.countIncrease());
            vo.setServiceTotal(assetServiceService.countTotal());
            vo.setServiceIncrease(assetServiceService.countIncrease());
            vo.setApiTotal(assetApiService.countTotal());
            vo.setApiIncrease(assetApiService.countIncrease());
            vo.setLogTotal(assetLogService.countTotal());
            vo.setLogIncrease(assetLogService.countIncrease());
            vo.setFileTotal(assetFileService.countTotal());
            vo.setFileIncrease(assetFileService.countIncrease());
            return ResponseWrap.success(vo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/trend"})
    public ResponseWrap<?> trend() {
        try {
            // 资产类型顺序与前端一致
            List<String> assetNames = Arrays.asList(
                    "服务器设备", "移动端设备", "PC端设备", "IOT设备", "探针资产", "应用资产", "服务资产", "API资产", "日志", "文件"
            );
            // 依次获取每类资产的趋势统计
            List<Map<String, Object>> trendDataList = Arrays.asList(
                    assetHostService.getStatisticsByDateOfWeek(),
                    assetMobileService.getStatisticsByDateOfWeek(),
                    assetPcService.getStatisticsByDateOfWeek(),
                    assetIotService.getStatisticsByDateOfWeek(),
                    assetProbeService.getStatisticsByDateOfWeek(),
                    assetAppService.getStatisticsByDateOfWeek(),
                    assetServiceService.getStatisticsByDateOfWeek(),
                    assetApiService.getStatisticsByDateOfWeek(),
                    assetLogService.getStatisticsByDateOfWeek(),
                    assetFileService.getStatisticsByDateOfWeek()
            );
            // 获取最近7天日期（格式：yyyy-MM-dd 00:00:00）
            List<String> dateList = new ArrayList<>();
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.0");
            for (int i = 6; i >= 0; i--) {
                dateList.add(today.minusDays(i).format(formatter));
            }
            // 组装series数据
            Map<String, List<Long>> seriesMap = new LinkedHashMap<>();
            for (int i = 0; i < assetNames.size(); i++) {
                String key = assetNames.get(i);
                Map<String, Object> trendData = trendDataList.get(i);
                List<Long> values = new ArrayList<>();
                for (String date : dateList) {
                    Object v = trendData.getOrDefault(date, 0L);
                    values.add(Long.parseLong(String.valueOf(v)));
                }
                seriesMap.put(key, values);
            }
            // 组装返回结构
            Map<String, Object> result = new LinkedHashMap<>();
            result.put("legend_data", assetNames);
            result.put("xaxis_data", dateList);
            // 每个资产类型一组series
            for (int i = 0; i < assetNames.size(); i++) {
                result.put("series_data_" + getSeriesKey(assetNames.get(i)), seriesMap.get(assetNames.get(i)));
            }
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    // 资产类型转series key
    private String getSeriesKey(String name) {
        switch (name) {
            case "服务器设备":
                return "host";
            case "移动端设备":
                return "mobile";
            case "PC端设备":
                return "pc";
            case "IOT设备":
                return "iot";
            case "探针资产":
                return "probe";
            case "应用资产":
                return "app";
            case "服务资产":
                return "service";
            case "API资产":
                return "api";
            case "日志":
                return "log";
            case "文件":
                return "file";
            default:
                return name;
        }
    }


    @GetMapping({"/statistics/level"})
    public ResponseWrap<?> statisticsLevel() {
        try {
            AssetLevelCountVo vo = new AssetLevelCountVo();
            // 资产类型顺序与前端一致
            List<String> assetNames = Arrays.asList(
                    "服务器设备", "移动端设备", "PC端设备", "IOT设备", "探针资产", "应用资产", "服务资产", "API资产", "日志", "文件"
            );
            vo.setYaxisData(assetNames);

            // 依次获取每类资产的等级统计
            List<Map<String, Object>> levelDataList = Arrays.asList(
                    assetHostService.getStatisticsByLevel(),
                    assetMobileService.getStatisticsByLevel(),
                    assetPcService.getStatisticsByLevel(),
                    assetIotService.getStatisticsByLevel(),
                    assetProbeService.getStatisticsByLevel(),
                    assetAppService.getStatisticsByLevel(),
                    assetServiceService.getStatisticsByLevel(),
                    assetApiService.getStatisticsByLevel(),
                    assetLogService.getStatisticsByLevel(),
                    assetFileService.getStatisticsByLevel()
            );

            // 资产等级顺序与前端一致
            List<AssetLevel> levels = Arrays.asList(
                    AssetLevel.AUXILIARY, AssetLevel.GENERAL, AssetLevel.MINOR, AssetLevel.IMPORTANT, AssetLevel.CRITICAL
            );

            // 初始化每个等级的统计list
            List<List<String>> series = new ArrayList<>();
            for (int i = 0; i < levels.size(); i++) {
                series.add(new ArrayList<>());
            }

            // 按资产类型遍历，填充每个等级的数量
            for (Map<String, Object> levelData : levelDataList) {
                for (int i = 0; i < levels.size(); i++) {
                    String key = levels.get(i).name();
                    Object value = levelData.getOrDefault(key, 0L);
                    series.get(i).add(String.valueOf(value));
                }
            }

            vo.setSeriesDataAuxiliary(series.get(0));
            vo.setSeriesDataGeneral(series.get(1));
            vo.setSeriesDataMinor(series.get(2));
            vo.setSeriesDataImportant(series.get(3));
            vo.setSeriesDataCritical(series.get(4));

            return ResponseWrap.success(vo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/statistics/risk"})
    public ResponseWrap<?> statisticsRisk() {
        try {
            AssetRiskCountVo vo = new AssetRiskCountVo();
            // 资产类型顺序与前端一致
            List<String> assetNames = Arrays.asList(
                    "服务器设备", "移动端设备", "PC端设备", "IOT设备", "探针资产", "应用资产", "服务资产", "API资产", "日志", "文件"
            );
            vo.setYaxisData(assetNames);

            // 依次获取每类资产的等级统计
            List<Map<String, Object>> riskDataList = Arrays.asList(
                    assetHostService.getStatisticsByRisk(),
                    assetMobileService.getStatisticsByRisk(),
                    assetPcService.getStatisticsByRisk(),
                    assetIotService.getStatisticsByRisk(),
                    assetProbeService.getStatisticsByRisk(),
                    assetAppService.getStatisticsByRisk(),
                    assetServiceService.getStatisticsByRisk(),
                    assetApiService.getStatisticsByRisk(),
                    assetLogService.getStatisticsByRisk(),
                    assetFileService.getStatisticsByRisk()
            );

            // 资产等级顺序与前端一致
            List<AssetRiskLevel> levels = Arrays.asList(
                    AssetRiskLevel.NONE, AssetRiskLevel.LOW, AssetRiskLevel.MEDIUM, AssetRiskLevel.HIGH, AssetRiskLevel.EXTREME
            );

            // 初始化每个等级的统计list
            List<List<String>> series = new ArrayList<>();
            for (int i = 0; i < levels.size(); i++) {
                series.add(new ArrayList<>());
            }

            // 按资产类型遍历，填充每个等级的数量
            for (Map<String, Object> levelData : riskDataList) {
                for (int i = 0; i < levels.size(); i++) {
                    String key = levels.get(i).name();
                    Object value = levelData.getOrDefault(key, 0L);
                    series.get(i).add(String.valueOf(value));
                }
            }

            vo.setSeriesDataNone(series.get(0));
            vo.setSeriesDataLow(series.get(1));
            vo.setSeriesDataMedium(series.get(2));
            vo.setSeriesDataHigh(series.get(3));
            vo.setSeriesDataExtreme(series.get(4));


            return ResponseWrap.success(vo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 获取资产来源列表
     */
    @GetMapping("/source/list")
    public ResponseWrap<?> listSource() {
        try {
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(
                    Arrays.asList(AssetSource.values()),
                    source -> source.getDescription(),
                    source -> source.name()
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 获取资产类型列表
     */
    @GetMapping("/type/list")
    public ResponseWrap<?> listType() {
        try {
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(
                    Arrays.asList(AssetType.values()),
                    type -> type.getDescription(),
                    type -> type.name()
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 获取资产所有者列表
     */
    @GetMapping("/owner/list")
    public ResponseWrap<?> listOwner() {
        try {
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(
                    Arrays.asList(AssetOwner.values()),
                    owner -> owner.getDescription(),
                    owner -> owner.name()
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 获取资产状态列表
     */
    @GetMapping("/status/list")
    public ResponseWrap<?> listStatus() {
        try {
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(
                    Arrays.asList(AssetStatus.values()),
                    status -> status.getDescription(),
                    status -> status.name()
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 获取资产等级列表
     */
    @GetMapping("/level/list")
    public ResponseWrap<?> listLevel() {
        try {
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(
                    Arrays.asList(AssetLevel.values()),
                    level -> level.getDescription(),
                    level -> level.name()
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 获取资产风险等级列表
     */
    @GetMapping("/risk/list")
    public ResponseWrap<?> listRisk() {
        try {
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(
                    Arrays.asList(AssetRiskLevel.values()),
                    risk -> risk.getDescription(),
                    risk -> risk.name()
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 获取资产对象列表
     */
    @GetMapping("/class/list")
    public ResponseWrap<?> listClass() {
        try {
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(
                    Arrays.asList(Asset.values()),
                    assetClass -> assetClass.getDescription(),
                    assetClass -> assetClass.name()
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 生成最近7天的日期字符串
     *
     * @return 格式化后的日期字符串列表
     */
    private String generateLastSevenDaysString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
        LocalDate today = LocalDate.now();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            sb.append("    \"").append(date.format(formatter)).append("\"");
            if (i > 0) {
                sb.append(",\n");
            } else {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
