package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetFileDto;
import com.coolxer.model.business.asset.dto.AssetFileSearchDto;
import com.coolxer.model.business.asset.vo.AssetFileVo;
import com.coolxer.service.business.asset.AssetFileService;
import com.coolxer.utils.CommonUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产管理-文件资产
 */
@Tag(name = "资产管理-文件资产")
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/file")
public class AssetFileController extends BaseController {

    @Autowired
    private AssetFileService assetFileService;

    /**
     * 添加文件资产
     */
    @PostMapping({"/add"})
    public ResponseWrap<?> add(@RequestBody AssetFileDto assetFileDto) {
        try {
            if (assetFileService.add(assetFileDto)) {
                return ResponseWrap.success("添加成功");
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 删除文件资产
     */
    @DeleteMapping({"/{id}"})
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        try {
            assetFileService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 批量删除文件资产
     */
    @DeleteMapping({"/bulk/{ids}"})
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        try {
            assetFileService.deleteALL(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 更新文件资产
     */
    @PostMapping({"/{id}/update"})
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AssetFileDto assetFileDto) {
        try {
            if (assetFileService.update(id, assetFileDto)) {
                return ResponseWrap.success("修改成功");
            } else
                return ResponseWrap.fail();
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 批量更新文件资产
     */
    @PostMapping({"/{ids}/bulk_update"})
    public ResponseWrap<?> bulkUpdate(@PathVariable("ids") String[] ids, @RequestBody AssetFileDto assetFileDto) {
        try {
            for (String id : ids) {
                assetFileService.update(id, assetFileDto);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
        return ResponseWrap.success("修改成功");
    }

    /**
     * 分页查询文件资产列表
     */
    @GetMapping({"/list"})
    public ResponseWrap<?> list(AssetFileSearchDto assetFileSearchDto) {
        try {
            PageRowsVo<AssetFileVo> pageDataVo = assetFileService.getPageList(assetFileSearchDto);
            return ResponseWrap.success(pageDataVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 查询单个文件资产详情
     */
    @GetMapping({"/{id}/view"})
    public ResponseWrap<AssetFileVo> query(@PathVariable("id") String id) {
        try {
            AssetFileVo assetFileVo = assetFileService.getOne(id);
            if (assetFileVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetFileVo);
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
            List<String> labels = assetFileService.getDistinctLabels();
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(labels,
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
            List<String> similarIds = assetFileService.getSimilarIds(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarIds,
                    id -> id,    // label 使用原值
                    id -> id     // value 使用原值
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 文件名称自动补全
     */
    @GetMapping("/auto_complete/file_name")
    public ResponseWrap<?> autoCompleteFileName(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarFileNames = assetFileService.getSimilarFileNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarFileNames,
                    fileName -> fileName,
                    fileName -> fileName
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 文件类型自动补全
     */
    @GetMapping("/auto_complete/file_type")
    public ResponseWrap<?> autoCompleteFileType(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarFileTypes = assetFileService.getSimilarFileTypes(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarFileTypes,
                    fileType -> fileType,
                    fileType -> fileType
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 文件格式自动补全
     */
    @GetMapping("/auto_complete/file_format")
    public ResponseWrap<?> autoCompleteFileFormat(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarFileFormats = assetFileService.getSimilarFileFormats(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarFileFormats,
                    fileFormat -> fileFormat,
                    fileFormat -> fileFormat
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 文件路径自动补全
     */
    @GetMapping("/auto_complete/file_path")
    public ResponseWrap<?> autoCompleteFilePath(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarFilePaths = assetFileService.getSimilarFilePaths(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarFilePaths,
                    filePath -> filePath,
                    filePath -> filePath
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 源系统自动补全
     */
    @GetMapping("/auto_complete/source_system")
    public ResponseWrap<?> autoCompleteSourceSystem(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarSourceSystems = assetFileService.getSimilarSourceSystems(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarSourceSystems,
                    sourceSystem -> sourceSystem,
                    sourceSystem -> sourceSystem
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 文件所有者自动补全
     */
    @GetMapping("/auto_complete/file_owner")
    public ResponseWrap<?> autoCompleteFileOwner(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarFileOwners = assetFileService.getSimilarFileOwners(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarFileOwners,
                    fileOwner -> fileOwner,
                    fileOwner -> fileOwner
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }
} 