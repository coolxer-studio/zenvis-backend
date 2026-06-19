package com.coolxer.controller.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.StartEventDto;
import com.coolxer.model.business.operation.vo.StartEventVo;
import com.coolxer.service.business.operation.StartEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation/start")
public class StartEventController {
    @Autowired
    private StartEventService startEventService;

    @PostMapping("/add")
    public ResponseWrap<?> add(@RequestBody StartEventDto dto) {
        return startEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return startEventService.delete(id) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/bulk/{ids}")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        return startEventService.deleteAll(ids) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @PostMapping("/{id}/update")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody StartEventDto dto) {
        return startEventService.update(id, dto) ? ResponseWrap.success("修改成功") : ResponseWrap.fail();
    }

    @GetMapping("/list")
    public ResponseWrap<PageRowsVo<StartEventVo>> list(StartEventDto searchDto) {
        return ResponseWrap.success(startEventService.getPageList(searchDto));
    }

    @GetMapping("/{id}/view")
    public ResponseWrap<StartEventVo> query(@PathVariable("id") String id) {
        StartEventVo vo = startEventService.getOne(id);
        return vo == null ? ResponseWrap.fail() : ResponseWrap.success(vo);
    }

    @GetMapping("/auto_complete/id")
    public ResponseWrap<List<String>> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(startEventService.getSimilarIds(term));
    }

    @GetMapping("/auto_complete/app_name")
    public ResponseWrap<List<String>> autoCompleteAppName(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(startEventService.getSimilarAppNames(term));
    }

    @GetMapping("/auto_complete/package_name")
    public ResponseWrap<List<String>> autoCompletePackageName(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(startEventService.getSimilarPackageNames(term));
    }

    @GetMapping("/auto_complete/device_model")
    public ResponseWrap<List<String>> autoCompleteDeviceModel(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(startEventService.getSimilarDeviceModels(term));
    }

    @GetMapping("/auto_complete/device_os")
    public ResponseWrap<List<String>> autoCompleteDeviceOs(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(startEventService.getSimilarDeviceOses(term));
    }

} 