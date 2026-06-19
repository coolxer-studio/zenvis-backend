package com.coolxer.controller.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.PerformanceEventDto;
import com.coolxer.model.business.operation.vo.PerformanceEventVo;
import com.coolxer.service.business.operation.PerformanceEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation/performance")
public class PerformanceEventController {
    @Autowired
    private PerformanceEventService performanceEventService;

    @PostMapping("/add")
    public ResponseWrap<?> add(@RequestBody PerformanceEventDto dto) {
        return performanceEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return performanceEventService.delete(id) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/bulk/{ids}")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        return performanceEventService.deleteAll(ids) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @PostMapping("/{id}/update")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody PerformanceEventDto dto) {
        return performanceEventService.update(id, dto) ? ResponseWrap.success("修改成功") : ResponseWrap.fail();
    }

    @GetMapping("/list")
    public ResponseWrap<PageRowsVo<PerformanceEventVo>> list(PerformanceEventDto searchDto) {
        return ResponseWrap.success(performanceEventService.getPageList(searchDto));
    }

    @GetMapping("/{id}/view")
    public ResponseWrap<PerformanceEventVo> query(@PathVariable("id") String id) {
        PerformanceEventVo vo = performanceEventService.getOne(id);
        return vo == null ? ResponseWrap.fail() : ResponseWrap.success(vo);
    }

    @GetMapping("/auto_complete/id")
    public ResponseWrap<List<String>> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(performanceEventService.getSimilarIds(term));
    }
} 