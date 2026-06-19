package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetServiceDto;
import com.coolxer.model.business.asset.dto.AssetServiceSearchDto;
import com.coolxer.model.business.asset.vo.AssetServiceVo;
import com.coolxer.service.business.asset.AssetServiceService;
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
 * 系统服务资产
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/service")
@Tag(name = "系统服务资产管理", description = "系统服务资产的增删改查等接口")
public class AssetServiceController extends BaseController {

    @Autowired
    private AssetServiceService assetServiceService;

    @PostMapping("/add")
    @Operation(summary = "新增系统服务资产", description = "新增一个系统服务资产")
    public ResponseWrap<?> add(@RequestBody AssetServiceDto assetServiceDto) {
        try {
            if (assetServiceService.add(assetServiceDto)) {
                return ResponseWrap.success("添加成功");
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除系统服务资产", description = "根据ID删除一个系统服务资产")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        try {
            assetServiceService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping("/bulk/{ids}")
    @Operation(summary = "批量删除系统服务资产", description = "根据ID列表批量删除系统服务资产")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        try {
            assetServiceService.deleteALL(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping("/{id}/update")
    @Operation(summary = "更新系统服务资产", description = "更新指定ID的系统服务资产信息")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AssetServiceDto assetServiceDto) {
        try {
            if (assetServiceService.update(id, assetServiceDto)) {
                return ResponseWrap.success("修改成功");
            } else {
                return ResponseWrap.fail();
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/{id}/view")
    @Operation(summary = "获取系统服务资产详情", description = "根据ID获取系统服务资产详细信息")
    public ResponseWrap<AssetServiceVo> query(@PathVariable("id") String id) {
        try {
            AssetServiceVo assetServiceVo = assetServiceService.getOne(id);
            if (assetServiceVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetServiceVo);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/list")
    @Operation(summary = "分页查询系统服务资产", description = "根据条件分页查询系统服务资产列表")
    public ResponseWrap<?> list(AssetServiceSearchDto assetServiceSearchDto) {
        try {
            PageRowsVo<AssetServiceVo> pageRowsVo = assetServiceService.getPageList(assetServiceSearchDto);
            return ResponseWrap.success(pageRowsVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/label/list")
    @Operation(summary = "获取系统服务资产标签列表", description = "获取所有不重复的系统服务资产标签")
    public ResponseWrap<?> listLabel() {
        try {
            List<String> labels = assetServiceService.getDistinctLabels();
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
    @Operation(summary = "获取相似的资产ID列表", description = "根据关键词获取相似的系统服务资产ID列表")
    public ResponseWrap<?> autoCompleteId(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarIds = assetServiceService.getSimilarIds(term);
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

    @GetMapping("/auto_complete/serviceName")
    @Operation(summary = "获取相似的服务名称列表", description = "根据关键词获取相似的服务名称列表")
    public ResponseWrap<?> autoCompleteServiceName(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> serviceNames = assetServiceService.getSimilarServiceNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(serviceNames,
                    name -> name,
                    name -> name
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/auto_complete/serviceVersion")
    @Operation(summary = "获取相似的服务版本列表", description = "根据关键词获取相似的服务版本列表")
    public ResponseWrap<?> autoCompleteServiceVersion(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> serviceVersions = assetServiceService.getSimilarServiceVersions(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(serviceVersions,
                    version -> version,
                    version -> version
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/auto_complete/serviceType")
    @Operation(summary = "获取相似的服务类型列表", description = "根据关键词获取相似的服务类型列表")
    public ResponseWrap<?> autoCompleteServiceType(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> serviceTypes = assetServiceService.getSimilarServiceTypes(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(serviceTypes,
                    type -> type,
                    type -> type
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/auto_complete/runtimeEnvironment")
    @Operation(summary = "获取相似的运行环境列表", description = "根据关键词获取相似的运行环境列表")
    public ResponseWrap<?> autoCompleteRuntimeEnvironment(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> runtimeEnvironments = assetServiceService.getSimilarRuntimeEnvironments(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(runtimeEnvironments,
                    env -> env,
                    env -> env
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/auto_complete/deploymentType")
    @Operation(summary = "获取相似的部署类型列表", description = "根据关键词获取相似的部署类型列表")
    public ResponseWrap<?> autoCompleteDeploymentType(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> deploymentTypes = assetServiceService.getSimilarDeploymentTypes(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(deploymentTypes,
                    type -> type,
                    type -> type
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping("/auto_complete/processName")
    @Operation(summary = "获取相似的进程名称列表", description = "根据关键词获取相似的进程名称列表")
    public ResponseWrap<?> autoCompleteProcessName(@Parameter(description = "关键词") @RequestParam(value = "term", required = false) String term) {
        try {
            List<String> processNames = assetServiceService.getSimilarProcessNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(processNames,
                    name -> name,
                    name -> name
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

} 