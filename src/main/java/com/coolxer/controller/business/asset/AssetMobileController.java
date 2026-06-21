package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetMobileDto;
import com.coolxer.model.business.asset.dto.AssetMobileSearchDto;
import com.coolxer.model.business.asset.vo.AssetMobileVo;
import com.coolxer.service.business.asset.AssetMobileService;
import com.coolxer.utils.CommonUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产管理-移动端
 */
@Tag(name = "资产管理-移动端")
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/mobile")
public class AssetMobileController extends BaseController {

    @Autowired
    private AssetMobileService assetMobileService;

    @PostMapping({"/add"})
    public ResponseWrap<?> add(@RequestBody AssetMobileDto assetMobileDto) {
        try {
            if (assetMobileService.add(assetMobileDto)) {
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
            assetMobileService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/bulk/{ids}"})
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        try {
            assetMobileService.deleteALL(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{id}/update"})
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AssetMobileDto assetMobileDto) {
        try {
            if (assetMobileService.update(id, assetMobileDto)) {
                return ResponseWrap.success("修改成功");
            } else
                return ResponseWrap.fail();
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{ids}/bulk_update"})
    public ResponseWrap<?> bulkUpdate(@PathVariable("ids") String[] ids, @RequestBody AssetMobileDto assetMobileDto) {
        try {
            for (String id : ids) {
                assetMobileService.update(id, assetMobileDto);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
        return ResponseWrap.success("修改成功");
    }

    @GetMapping({"/list"})
    public ResponseWrap<?> list(AssetMobileSearchDto assetMobileSearchDto) {
        try {
            PageRowsVo<AssetMobileVo> pageDataVo = assetMobileService.getPageList(assetMobileSearchDto);
            return ResponseWrap.success(pageDataVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/{id}/view"})
    public ResponseWrap<AssetMobileVo> query(@PathVariable("id") String id) {
        try {
            AssetMobileVo assetMobileVo = assetMobileService.getOne(id);
            if (assetMobileVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetMobileVo);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/label/list"})
    public ResponseWrap<?> listLabel() {
        try {
            List<String> labels = assetMobileService.getDistinctLabels();
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
            List<String> similarIds = assetMobileService.getSimilarIds(term);
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
     * 设备品牌自动补全
     */
    @GetMapping("/auto_complete/brand")
    public ResponseWrap<?> autoCompleteBrand(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarBrands = assetMobileService.getSimilarBrands(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarBrands,
                    brand -> brand,
                    brand -> brand
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 设备型号代码自动补全
     */
    @GetMapping("/auto_complete/model")
    public ResponseWrap<?> autoCompleteModel(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarModels = assetMobileService.getSimilarModels(term);
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
     * 制造商名称自动补全
     */
    @GetMapping("/auto_complete/manufacturer")
    public ResponseWrap<?> autoCompleteManufacturer(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarManufacturers = assetMobileService.getSimilarManufacturers(term);
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
     * 系统名称自动补全
     */
    @GetMapping("/auto_complete/system_name")
    public ResponseWrap<?> autoCompleteSystemName(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarSystemNames = assetMobileService.getSimilarSystemNames(term);
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
     * 操作系统版本自动补全
     */
    @GetMapping("/auto_complete/system_version")
    public ResponseWrap<?> autoCompleteOsVersion(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarOsVersions = assetMobileService.getSimilarSystemVersions(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarOsVersions,
                    osVersion -> osVersion,
                    osVersion -> osVersion
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 操作系统名称自动补全
     */
    @GetMapping("/auto_complete/os_name")
    public ResponseWrap<?> autoCompleteOsName(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarOsNames = assetMobileService.getSimilarOsNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarOsNames,
                    osName -> osName,
                    osName -> osName
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }
} 