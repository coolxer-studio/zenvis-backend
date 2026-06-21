package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetProbeDto;
import com.coolxer.model.business.asset.dto.AssetProbeSearchDto;
import com.coolxer.model.business.asset.vo.AssetProbeVo;
import com.coolxer.service.business.asset.AssetProbeService;
import com.coolxer.utils.CommonUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产管理-数据探针SDK
 */
@Tag(name = "资产管理-数据探针SDK")
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/probe")
public class AssetProbeController extends BaseController {

    @Autowired
    private AssetProbeService assetProbeService;

    @PostMapping({"/add"})
    public ResponseWrap<?> add(@RequestBody AssetProbeDto assetProbeDto) {
        try {
            if (assetProbeService.add(assetProbeDto)) {
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
            assetProbeService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/bulk/{ids}"})
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        try {
            assetProbeService.deleteALL(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{id}/update"})
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AssetProbeDto assetProbeDto) {
        try {
            if (assetProbeService.update(id, assetProbeDto)) {
                return ResponseWrap.success("修改成功");
            } else
                return ResponseWrap.fail();
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{ids}/bulk_update"})
    public ResponseWrap<?> bulkUpdate(@PathVariable("ids") String[] ids, @RequestBody AssetProbeDto assetProbeDto) {
        try {
            for (String id : ids) {
                assetProbeService.update(id, assetProbeDto);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
        return ResponseWrap.success("修改成功");
    }

    @GetMapping({"/{id}/view"})
    public ResponseWrap<?> query(@PathVariable("id") String id) {
        try {
            AssetProbeVo assetProbeVo = assetProbeService.getOne(id);
            if (assetProbeVo != null) {
                return ResponseWrap.success(assetProbeVo);
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/list"})
    public ResponseWrap<?> list(AssetProbeSearchDto searchDto) {
        try {
            PageRowsVo<AssetProbeVo> pageRowsVo = assetProbeService.getPageList(searchDto);
            return ResponseWrap.success(pageRowsVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/label/list"})
    public ResponseWrap<?> listLabel() {
        try {
            List<String> labels = assetProbeService.getDistinctLabels();
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
    public ResponseWrap<?> autoCompleteId(@RequestParam("term") String term) {
        try {
            List<String> ids = assetProbeService.getSimilarIds(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(ids,
                    id -> id,    // label 使用原值
                    id -> id     // value 使用原值
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/auto_complete/probe_name"})
    public ResponseWrap<?> autoCompleteProbeNames(@RequestParam("term") String term) {
        try {
            List<String> names = assetProbeService.getSimilarProbeNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(names,
                    name -> name,
                    name -> name
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/auto_complete/probe_type"})
    public ResponseWrap<?> autoCompleteProbeTypes(@RequestParam("term") String term) {
        try {
            List<String> types = assetProbeService.getSimilarProbeTypes(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(types,
                    type -> type,
                    type -> type
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/auto_complete/language"})
    public ResponseWrap<?> autoCompleteLanguages(@RequestParam("term") String term) {
        try {
            List<String> languages = assetProbeService.getSimilarLanguages(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(languages,
                    language -> language,
                    language -> language
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/auto_complete/framework"})
    public ResponseWrap<?> autoCompleteFrameworks(@RequestParam("term") String term) {
        try {
            List<String> frameworks = assetProbeService.getSimilarFrameworks(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(frameworks,
                    framework -> framework,
                    framework -> framework
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/auto_complete/encryption_method"})
    public ResponseWrap<?> autoCompleteEncryptionMethods(@RequestParam("term") String term) {
        try {
            List<String> methods = assetProbeService.getSimilarEncryptionMethods(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(methods,
                    method -> method,
                    method -> method
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/auto_complete/authentication_method"})
    public ResponseWrap<?> autoCompleteAuthenticationMethods(@RequestParam("term") String term) {
        try {
            List<String> methods = assetProbeService.getSimilarAuthenticationMethods(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(methods,
                    method -> method,
                    method -> method
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/auto_complete/data_transmission_protocol"})
    public ResponseWrap<?> autoCompleteDataTransmissionProtocols(@RequestParam("term") String term) {
        try {
            List<String> protocols = assetProbeService.getSimilarDataTransmissionProtocols(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(protocols,
                    protocol -> protocol,
                    protocol -> protocol
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

} 