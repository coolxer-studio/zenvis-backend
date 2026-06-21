package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetIotDto;
import com.coolxer.model.business.asset.dto.AssetIotSearchDto;
import com.coolxer.model.business.asset.vo.AssetIotVo;
import com.coolxer.service.business.asset.AssetIotService;
import com.coolxer.utils.CommonUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产管理-IoT设备
 */
@Tag(name = "资产管理-IoT设备")
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/iot")
public class AssetIotController extends BaseController {

    @Autowired
    private AssetIotService assetIotService;

    @PostMapping({"/add"})
    public ResponseWrap<?> add(@RequestBody AssetIotDto assetIotDto) {
        try {
            if (assetIotService.add(assetIotDto)) {
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
            assetIotService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/bulk/{ids}"})
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        try {
            assetIotService.deleteALL(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{id}/update"})
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AssetIotDto assetIotDto) {
        try {
            if (assetIotService.update(id, assetIotDto)) {
                return ResponseWrap.success("修改成功");
            } else
                return ResponseWrap.fail();
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{ids}/bulk_update"})
    public ResponseWrap<?> bulkUpdate(@PathVariable("ids") String[] ids, @RequestBody AssetIotDto assetIotDto) {
        try {
            for (String id : ids) {
                assetIotService.update(id, assetIotDto);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
        return ResponseWrap.success("修改成功");
    }

    @GetMapping({"/list"})
    public ResponseWrap<?> list(AssetIotSearchDto assetIotSearchDto) {
        try {
            PageRowsVo<AssetIotVo> pageDataVo = assetIotService.getPageList(assetIotSearchDto);
            return ResponseWrap.success(pageDataVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/{id}/view"})
    public ResponseWrap<AssetIotVo> query(@PathVariable("id") String id) {
        try {
            AssetIotVo assetIotVo = assetIotService.getOne(id);
            if (assetIotVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetIotVo);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/label/list"})
    public ResponseWrap<?> listLabel() {
        try {
            List<String> labels = assetIotService.getDistinctLabels();
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
            List<String> similarIds = assetIotService.getSimilarIds(term);
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
     * 设备名称自动补全
     */
    @GetMapping("/auto_complete/device_name")
    public ResponseWrap<?> autoCompleteDeviceName(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarDeviceNames = assetIotService.getSimilarDeviceNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarDeviceNames,
                    deviceName -> deviceName,
                    deviceName -> deviceName
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 设备类型自动补全
     */
    @GetMapping("/auto_complete/device_type")
    public ResponseWrap<?> autoCompleteDeviceType(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarDeviceTypes = assetIotService.getSimilarDeviceTypes(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarDeviceTypes,
                    deviceType -> deviceType,
                    deviceType -> deviceType
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
            List<String> similarManufacturers = assetIotService.getSimilarManufacturers(term);
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
            List<String> similarModels = assetIotService.getSimilarModels(term);
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
     * 固件版本自动补全
     */
    @GetMapping("/auto_complete/firmware_version")
    public ResponseWrap<?> autoCompleteFirmwareVersion(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarVersions = assetIotService.getSimilarFirmwareVersions(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarVersions,
                    version -> version,
                    version -> version
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 通信协议自动补全
     */
    @GetMapping("/auto_complete/communication_protocol")
    public ResponseWrap<?> autoCompleteCommunicationProtocol(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarProtocols = assetIotService.getSimilarCommunicationProtocols(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarProtocols,
                    protocol -> protocol,
                    protocol -> protocol
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

} 