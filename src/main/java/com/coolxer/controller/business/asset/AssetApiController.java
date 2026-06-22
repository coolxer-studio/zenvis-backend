package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetApiDto;
import com.coolxer.model.business.asset.dto.AssetApiSearchDto;
import com.coolxer.model.business.asset.vo.AssetApiVo;
import com.coolxer.service.business.asset.AssetApiService;
import com.coolxer.utils.CommonUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API接口资产
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/api")
@Tag(name = "API接口资产管理", description = "API接口资产的增删改查等接口")
public class AssetApiController extends BaseController {

    @Autowired
    private AssetApiService assetApiService;

    @PostMapping("/add")
    @Operation(summary = "新增API接口资产", description = "新增一个API接口资产")
    public ResponseWrap<?> add(@RequestBody AssetApiDto assetApiDto) {
        try {
            if (assetApiService.add(assetApiDto)) {
                return ResponseWrap.success("添加成功");
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除API接口资产", description = "根据ID删除一个API接口资产")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        try {
            assetApiService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping("/bulk/{ids}")
    @Operation(summary = "批量删除API接口资产", description = "根据ID列表批量删除API接口资产")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        try {
            assetApiService.deleteALL(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping("/{id}/update")
    @Operation(summary = "更新API接口资产", description = "更新指定ID的API接口资产信息")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AssetApiDto assetApiDto) {
        try {
            if (assetApiService.update(id, assetApiDto)) {
                return ResponseWrap.success("修改成功");
            } else {
                return ResponseWrap.fail();
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/{id}/view")
    @Operation(summary = "获取API接口资产详情", description = "根据ID获取API接口资产详细信息")
    public ResponseWrap<AssetApiVo> query(@PathVariable("id") String id) {
        try {
            AssetApiVo assetApiVo = assetApiService.getOne(id);
            if (assetApiVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetApiVo);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/list")
    @Operation(summary = "分页查询API接口资产", description = "根据条件分页查询API接口资产列表")
    public ResponseWrap<?> list(AssetApiSearchDto assetApiSearchDto) {
        try {
            PageRowsVo<AssetApiVo> pageRowsVo = assetApiService.getPageList(assetApiSearchDto);
            return ResponseWrap.success(pageRowsVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/label/list")
    @Operation(summary = "获取API接口资产标签列表", description = "获取所有不重复的API接口资产标签")
    public ResponseWrap<?> listLabel() {
        try {
            List<String> labels = assetApiService.getDistinctLabels();
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

    @GetMapping("/auto_complete/id")
    @Operation(summary = "获取相似的资产ID列表", description = "根据关键词获取相似的API接口资产ID列表")
    public ResponseWrap<?> autoCompleteId(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarIds = assetApiService.getSimilarIds(term);
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

    @GetMapping("/auto_complete/api_name")
    @Operation(summary = "获取相似的API名称列表", description = "根据关键词获取相似的API名称列表")
    public ResponseWrap<?> autoCompleteApiName(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> apiNames = assetApiService.getSimilarApiNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(apiNames,
                    name -> name,
                    name -> name
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/auto_complete/api_version")
    @Operation(summary = "获取相似的API版本列表", description = "根据关键词获取相似的API版本列表")
    public ResponseWrap<?> autoCompleteApiVersion(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> apiVersions = assetApiService.getSimilarApiVersions(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(apiVersions,
                    version -> version,
                    version -> version
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/auto_complete/api_path")
    @Operation(summary = "获取相似的API路径列表", description = "根据关键词获取相似的API路径列表")
    public ResponseWrap<?> autoCompleteApiPath(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> apiPaths = assetApiService.getSimilarApiPaths(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(apiPaths,
                    path -> path,
                    path -> path
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/auto_complete/http_method")
    @Operation(summary = "获取相似的HTTP方法列表", description = "根据关键词获取相似的HTTP方法列表")
    public ResponseWrap<?> autoCompleteHttpMethod(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> httpMethods = assetApiService.getSimilarHttpMethods(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(httpMethods,
                    method -> method,
                    method -> method
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/auto_complete/content_type")
    @Operation(summary = "获取相似的内容类型列表", description = "根据关键词获取相似的内容类型列表")
    public ResponseWrap<?> autoCompleteContentType(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> contentTypes = assetApiService.getSimilarContentTypes(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(contentTypes,
                    type -> type,
                    type -> type
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/auto_complete/authentication_type")
    @Operation(summary = "获取相似的认证类型列表", description = "根据关键词获取相似的认证类型列表")
    public ResponseWrap<?> autoCompleteAuthenticationType(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> authenticationTypes = assetApiService.getSimilarAuthenticationTypes(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(authenticationTypes,
                    type -> type,
                    type -> type
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/service/{serviceId}")
    @Operation(summary = "根据服务ID获取API接口列表", description = "根据系统服务ID获取相关的API接口列表")
    public ResponseWrap<?> getByServiceId(@PathVariable("serviceId") String serviceId) {
        try {
            List<AssetApiVo> apis = assetApiService.getByServiceId(serviceId);
            return ResponseWrap.success(apis);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

} 