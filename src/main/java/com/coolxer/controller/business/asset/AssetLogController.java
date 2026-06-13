package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetLogDto;
import com.coolxer.model.business.asset.dto.AssetLogSearchDto;
import com.coolxer.model.business.asset.vo.AssetLogVo;
import com.coolxer.service.business.asset.AssetLogService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 资产管理-日志资产
 *
 * @author hunter
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/log")
public class AssetLogController extends BaseController {

    @Autowired
    private AssetLogService assetLogService;

    /**
     * 添加日志资产
     */
    @PostMapping({"/add"})
    public ResponseWrap<?> add(@RequestBody AssetLogDto assetLogDto) {
        try {
            if (assetLogService.add(assetLogDto)) {
                return ResponseWrap.success("添加成功");
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 删除日志资产
     */
    @DeleteMapping({"/{id}"})
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        try {
            assetLogService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 批量删除日志资产
     */
    @DeleteMapping({"/bulk/{ids}"})
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        try {
            assetLogService.deleteALL(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 更新日志资产
     */
    @PostMapping({"/{id}/update"})
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AssetLogDto assetLogDto) {
        try {
            if (assetLogService.update(id, assetLogDto)) {
                return ResponseWrap.success("修改成功");
            } else
                return ResponseWrap.fail();
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 批量更新日志资产
     */
    @PostMapping({"/{ids}/bulk_update"})
    public ResponseWrap<?> bulkUpdate(@PathVariable("ids") String[] ids, @RequestBody AssetLogDto assetLogDto) {
        try {
            for (String id : ids) {
                assetLogService.update(id, assetLogDto);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
        return ResponseWrap.success("修改成功");
    }

    /**
     * 分页查询日志资产列表
     */
    @GetMapping({"/list"})
    public ResponseWrap<?> list(AssetLogSearchDto assetLogSearchDto) {
        try {
            PageRowsVo<AssetLogVo> pageDataVo = assetLogService.getPageList(assetLogSearchDto);
            return ResponseWrap.success(pageDataVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 查询单个日志资产详情
     */
    @GetMapping({"/{id}/view"})
    public ResponseWrap<AssetLogVo> query(@PathVariable("id") String id) {
        try {
            AssetLogVo assetLogVo = assetLogService.getOne(id);
            if (assetLogVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetLogVo);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 获取所有不重复的标签列表
     */
    @GetMapping({"/label/list"})
    public ResponseWrap<?> listLabel() {
        try {
            List<String> labels = assetLogService.getDistinctLabels();
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", createOptions(labels,
                    label -> label,  // label 使用原值
                    label -> label   // value 使用原值
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 资产ID自动补全
     */
    @GetMapping({"/auto_complete/id"})
    public ResponseWrap<?> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarIds = assetLogService.getSimilarIds(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", createOptions(similarIds,
                    id -> id,    // label 使用原值
                    id -> id     // value 使用原值
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 日志名称自动补全
     */
    @GetMapping("/auto_complete/log_name")
    public ResponseWrap<?> autoCompleteLogName(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarLogNames = assetLogService.getSimilarLogNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", createOptions(similarLogNames,
                    logName -> logName,
                    logName -> logName
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 日志路径自动补全
     */
    @GetMapping("/auto_complete/log_path")
    public ResponseWrap<?> autoCompleteLogPath(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarLogPaths = assetLogService.getSimilarLogPaths(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", createOptions(similarLogPaths,
                    logPath -> logPath,
                    logPath -> logPath
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 日志类型自动补全
     */
    @GetMapping("/auto_complete/log_type")
    public ResponseWrap<?> autoCompleteLogType(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarLogTypes = assetLogService.getSimilarLogTypes(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", createOptions(similarLogTypes,
                    logType -> logType,
                    logType -> logType
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 日志格式自动补全
     */
    @GetMapping("/auto_complete/log_format")
    public ResponseWrap<?> autoCompleteLogFormat(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarLogFormats = assetLogService.getSimilarLogFormats(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", createOptions(similarLogFormats,
                    logFormat -> logFormat,
                    logFormat -> logFormat
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 日志级别自动补全
     */
    @GetMapping("/auto_complete/log_level")
    public ResponseWrap<?> autoCompleteLogLevel(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarLogLevels = assetLogService.getSimilarLogLevels(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", createOptions(similarLogLevels,
                    logLevel -> logLevel,
                    logLevel -> logLevel
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 进程信息自动补全
     */
    @GetMapping("/auto_complete/process")
    public ResponseWrap<?> autoCompleteProcess(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarProcesses = assetLogService.getSimilarProcesses(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", createOptions(similarProcesses,
                    process -> process,
                    process -> process
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 创建选项列表
     */
    private <T> List<Map<String, String>> createOptions(List<T> items,
                                                        Function<T, String> labelMapper,
                                                        Function<T, String> valueMapper) {
        if (items == null || items.isEmpty()) {
            return new ArrayList<>();
        }

        return items.stream()
                .map(item -> {
                    Map<String, String> option = new HashMap<>();
                    option.put("label", labelMapper.apply(item));
                    option.put("value", valueMapper.apply(item));
                    return option;
                })
                .collect(Collectors.toList());
    }
} 