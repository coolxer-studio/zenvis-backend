package com.coolxer.controller.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.LocationEventDto;
import com.coolxer.model.business.operation.vo.LocationEventVo;
import com.coolxer.service.business.operation.LocationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation/location")
public class LocationEventController {
    @Autowired
    private LocationEventService locationEventService;

    @PostMapping("/add")
    public ResponseWrap<?> add(@RequestBody LocationEventDto dto) {
        return locationEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return locationEventService.delete(id) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/bulk/{ids}")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        return locationEventService.deleteAll(ids) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @PostMapping("/{id}/update")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody LocationEventDto dto) {
        return locationEventService.update(id, dto) ? ResponseWrap.success("修改成功") : ResponseWrap.fail();
    }

    @GetMapping("/list")
    public ResponseWrap<PageRowsVo<LocationEventVo>> list(LocationEventDto searchDto) {
        return ResponseWrap.success(locationEventService.getPageList(searchDto));
    }

    @GetMapping("/{id}/view")
    public ResponseWrap<LocationEventVo> query(@PathVariable("id") String id) {
        LocationEventVo vo = locationEventService.getOne(id);
        return vo == null ? ResponseWrap.fail() : ResponseWrap.success(vo);
    }

    @GetMapping("/auto_complete/id")
    public ResponseWrap<List<String>> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(locationEventService.getSimilarIds(term));
    }
} 