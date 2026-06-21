package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.commons.enums.business.asset.AssetRuleAction;
import com.coolxer.commons.enums.business.asset.AssetRuleStatus;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetRuleDto;
import com.coolxer.model.business.asset.dto.AssetRuleSearchDto;
import com.coolxer.model.business.asset.vo.AssetRuleVo;
import com.coolxer.service.business.asset.AssetRuleService;
import com.coolxer.utils.CommonUtil;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 看板管理
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/rule")
public class AssetRuleController extends BaseController {

    @Autowired
    private AssetRuleService assetRuleService;

    @PostMapping({"/add"})
    public ResponseWrap<?> add(@RequestBody AssetRuleDto assetRuleDto) {

        try {
            if (assetRuleService.add(assetRuleDto)) {
                return ResponseWrap.success("创建成功");
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseWrap<?> delete(@PathVariable("id") Long id) {
        try {
            assetRuleService.deleteRule(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/bulk/{ids}"})
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<Long> ids) {
        try {
            assetRuleService.deleteRules(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{id}/update"})
    public ResponseWrap<?> update(@PathVariable("id") Long id, @Valid @RequestBody AssetRuleDto assetRuleDto) {
        try {
            if (assetRuleService.updateRule(id, assetRuleDto)) {
                return ResponseWrap.success("修改成功");
            } else
                return ResponseWrap.fail();
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{ids}/bulk_update"})
    public ResponseEntity<Void> bulkUpdate(@PathVariable("ids") Long[] ids, @RequestBody AssetRuleDto assetRuleDto) {
        try {
            for (long id : ids) {
                assetRuleService.updateRule(id, assetRuleDto);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping({"/list"})
    public ResponseWrap<?> list(AssetRuleSearchDto assetRuleSearchDto) {
        try {
            PageRowsVo<AssetRuleVo> pageDataVo = assetRuleService.getPageList(assetRuleSearchDto);
            return ResponseWrap.success(pageDataVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }

    }

    @GetMapping({"/{id}/view"})
    public ResponseWrap<AssetRuleVo> query(@PathVariable("id") Long id) {
        try {
            AssetRuleVo assetRuleVo = assetRuleService.getRule(id);
            if (assetRuleVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetRuleVo);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }

    }

    @PostMapping("/{id}/activate")
    public ResponseWrap<?> activateRule(@PathVariable("id") Long id) {
        if (assetRuleService.activateRule(id)) {
            return ResponseWrap.success("当前版本操作无效！");
        } else {
            return ResponseWrap.fail();
        }
    }

    @PostMapping("/{id}/deactivate")
    public ResponseWrap<?> deactivateRule(@PathVariable("id") Long id) {
        if (assetRuleService.deactivateRule(id)) {
            return ResponseWrap.success("当前版本操作无效！");
        } else {
            return ResponseWrap.fail();
        }
    }

    /**
     * 获取行动列表
     */
    @GetMapping("/action/list")
    public ResponseWrap<?> listAction() {
        try {
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(
                    Arrays.asList(AssetRuleAction.values()),
                    action -> action.getDescription(),
                    action -> action.name()
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 获取状态列表
     */
    @GetMapping("/status/list")
    public ResponseWrap<?> listStatus() {
        try {
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(
                    Arrays.asList(AssetRuleStatus.values()),
                    status -> status.getDescription(),
                    status -> status.name()
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

}
