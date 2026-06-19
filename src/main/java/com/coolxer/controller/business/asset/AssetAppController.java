package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetAppDto;
import com.coolxer.model.business.asset.dto.AssetAppSearchDto;
import com.coolxer.model.business.asset.vo.AssetAppVo;
import com.coolxer.service.business.asset.AssetAppService;
import com.coolxer.utils.CommonUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产管理-APP应用程序
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/app")
public class AssetAppController extends BaseController {

    @Autowired
    private AssetAppService assetAppService;

    /**
     * 添加APP应用程序资产
     */
    @PostMapping({"/add"})
    public ResponseWrap<?> add(@RequestBody AssetAppDto assetAppDto) {
        try {
            if (assetAppService.add(assetAppDto)) {
                return ResponseWrap.success("添加成功");
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 删除APP应用程序资产
     */
    @DeleteMapping({"/{id}"})
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        try {
            assetAppService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 批量删除APP应用程序资产
     */
    @DeleteMapping({"/bulk/{ids}"})
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        try {
            assetAppService.deleteALL(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 更新APP应用程序资产
     */
    @PostMapping({"/{id}/update"})
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AssetAppDto assetAppDto) {
        try {
            if (assetAppService.update(id, assetAppDto)) {
                return ResponseWrap.success("修改成功");
            } else
                return ResponseWrap.fail();
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 批量更新APP应用程序资产
     */
    @PostMapping({"/{ids}/bulk_update"})
    public ResponseWrap<?> bulkUpdate(@PathVariable("ids") String[] ids, @RequestBody AssetAppDto assetAppDto) {
        try {
            for (String id : ids) {
                assetAppService.update(id, assetAppDto);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
        return ResponseWrap.success("修改成功");
    }

    /**
     * 分页查询APP应用程序资产列表
     */
    @GetMapping({"/list"})
    public ResponseWrap<?> list(AssetAppSearchDto assetAppSearchDto) {
        try {
            PageRowsVo<AssetAppVo> pageDataVo = assetAppService.getPageList(assetAppSearchDto);
            return ResponseWrap.success(pageDataVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 查询单个APP应用程序资产详情
     */
    @GetMapping({"/{id}/view"})
    public ResponseWrap<AssetAppVo> query(@PathVariable("id") String id) {
        try {
            AssetAppVo assetAppVo = assetAppService.getOne(id);
            if (assetAppVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetAppVo);
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
            List<String> labels = assetAppService.getDistinctLabels();
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
            List<String> similarIds = assetAppService.getSimilarIds(term);
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
     * 应用名称自动补全
     */
    @GetMapping("/auto_complete/app_name")
    public ResponseWrap<?> autoCompleteAppName(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarAppNames = assetAppService.getSimilarAppNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarAppNames,
                    appName -> appName,
                    appName -> appName
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 应用版本自动补全
     */
    @GetMapping("/auto_complete/app_version")
    public ResponseWrap<?> autoCompleteAppVersion(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarAppVersions = assetAppService.getSimilarAppVersions(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarAppVersions,
                    appVersion -> appVersion,
                    appVersion -> appVersion
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 应用类型自动补全
     */
    @GetMapping("/auto_complete/app_type")
    public ResponseWrap<?> autoCompleteAppType(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarAppTypes = assetAppService.getSimilarAppTypes(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarAppTypes,
                    appType -> appType,
                    appType -> appType
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 平台自动补全
     */
    @GetMapping("/auto_complete/platform")
    public ResponseWrap<?> autoCompletePlatform(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarPlatforms = assetAppService.getSimilarPlatforms(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarPlatforms,
                    platform -> platform,
                    platform -> platform
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 包名自动补全
     */
    @GetMapping("/auto_complete/package_name")
    public ResponseWrap<?> autoCompletePackageName(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarPackageNames = assetAppService.getSimilarPackageNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarPackageNames,
                    packageName -> packageName,
                    packageName -> packageName
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 开发者自动补全
     */
    @GetMapping("/auto_complete/developer")
    public ResponseWrap<?> autoCompleteDeveloper(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarDevelopers = assetAppService.getSimilarDevelopers(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarDevelopers,
                    developer -> developer,
                    developer -> developer
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

} 