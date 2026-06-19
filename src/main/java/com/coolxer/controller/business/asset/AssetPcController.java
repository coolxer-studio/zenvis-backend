package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetPcDto;
import com.coolxer.model.business.asset.dto.AssetPcSearchDto;
import com.coolxer.model.business.asset.vo.AssetPcVo;
import com.coolxer.service.business.asset.AssetPcService;
import com.coolxer.utils.CommonUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产管理-PC
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/pc")
public class AssetPcController extends BaseController {

    @Autowired
    private AssetPcService assetPcService;

    @PostMapping({"/add"})
    public ResponseWrap<?> add(@RequestBody AssetPcDto assetPcDto) {
        try {
            if (assetPcService.add(assetPcDto)) {
                return ResponseWrap.success("添加成功");
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        try {
            assetPcService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/bulk/{ids}"})
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        try {
            assetPcService.deleteALL(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{id}/update"})
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AssetPcDto assetPcDto) {
        try {
            if (assetPcService.update(id, assetPcDto)) {
                return ResponseWrap.success("修改成功");
            } else
                return ResponseWrap.fail();
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{ids}/bulk_update"})
    public ResponseWrap<?> bulkUpdate(@PathVariable("ids") String[] ids, @RequestBody AssetPcDto assetPcDto) {
        try {
            for (String id : ids) {
                assetPcService.update(id, assetPcDto);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
        return ResponseWrap.success("修改成功");
    }

    @GetMapping({"/list"})
    public ResponseWrap<?> list(AssetPcSearchDto assetPcSearchDto) {
        try {
            PageRowsVo<AssetPcVo> pageDataVo = assetPcService.getPageList(assetPcSearchDto);
            return ResponseWrap.success(pageDataVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/{id}/view"})
    public ResponseWrap<AssetPcVo> query(@PathVariable("id") String id) {
        try {
            AssetPcVo assetPcVo = assetPcService.getOne(id);
            if (assetPcVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetPcVo);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/label/list"})
    public ResponseWrap<?> listLabel() {
        try {
            List<String> labels = assetPcService.getDistinctLabels();
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

    @GetMapping({"/auto_complete/id"})
    public ResponseWrap<?> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarIds = assetPcService.getSimilarIds(term);
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
     * 制造商名称自动补全
     */
    @GetMapping("/auto_complete/manufacturer")
    public ResponseWrap<?> autoCompleteManufacturer(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarManufacturers = assetPcService.getSimilarManufacturers(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarManufacturers,
                    manufacturer -> manufacturer,
                    manufacturer -> manufacturer
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 型号自动补全
     */
    @GetMapping("/auto_complete/model")
    public ResponseWrap<?> autoCompleteModel(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarModels = assetPcService.getSimilarModels(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarModels,
                    model -> model,
                    model -> model
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 架构自动补全
     */
    @GetMapping("/auto_complete/architecture")
    public ResponseWrap<?> autoCompleteArchitecture(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarArchitectures = assetPcService.getSimilarArchitectures(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarArchitectures,
                    architecture -> architecture,
                    architecture -> architecture
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 系统名称自动补全
     */
    @GetMapping("/auto_complete/system_name")
    public ResponseWrap<?> autoCompleteSystemName(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarSystemNames = assetPcService.getSimilarSystemNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarSystemNames,
                    systemName -> systemName,
                    systemName -> systemName
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 系统版本自动补全
     */
    @GetMapping("/auto_complete/system_version")
    public ResponseWrap<?> autoCompleteSystemVersion(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarSystemVersions = assetPcService.getSimilarSystemVersions(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarSystemVersions,
                    systemVersion -> systemVersion,
                    systemVersion -> systemVersion
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * CPU型号自动补全
     */
    @GetMapping("/auto_complete/cpu_model")
    public ResponseWrap<?> autoCompleteCpuModel(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarCpuModels = assetPcService.getSimilarCpuModels(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarCpuModels,
                    cpuModel -> cpuModel,
                    cpuModel -> cpuModel
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 显卡型号自动补全
     */
    @GetMapping("/auto_complete/gpu_model")
    public ResponseWrap<?> autoCompleteGpuModel(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarGpuModels = assetPcService.getSimilarGpuModels(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarGpuModels,
                    gpuModel -> gpuModel,
                    gpuModel -> gpuModel
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

} 