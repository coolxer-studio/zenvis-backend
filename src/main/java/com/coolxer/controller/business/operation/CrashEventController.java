package com.coolxer.controller.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.CrashEventDto;
import com.coolxer.model.business.operation.vo.CrashEventVo;
import com.coolxer.service.business.operation.CrashEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation/crash")
public class CrashEventController {
    @Autowired
    private CrashEventService crashEventService;

    @PostMapping("/add")
    public ResponseWrap<?> add(@RequestBody CrashEventDto dto) {
        return crashEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return crashEventService.delete(id) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/bulk/{ids}")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        return crashEventService.deleteAll(ids) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @PostMapping("/{id}/update")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody CrashEventDto dto) {
        return crashEventService.update(id, dto) ? ResponseWrap.success("修改成功") : ResponseWrap.fail();
    }

    @GetMapping("/list")
    public ResponseWrap<PageRowsVo<CrashEventVo>> list(CrashEventDto searchDto) {
        return ResponseWrap.success(crashEventService.getPageList(searchDto));
    }

    @GetMapping("/{id}/view")
    public ResponseWrap<CrashEventVo> query(@PathVariable("id") String id) {
        CrashEventVo vo = crashEventService.getOne(id);
        return vo == null ? ResponseWrap.fail() : ResponseWrap.success(vo);
    }

    @GetMapping("/auto_complete/id")
    public ResponseWrap<List<String>> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(crashEventService.getSimilarIds(term));
    }

    @GetMapping("/auto_complete/message")
    public ResponseWrap<List<String>> autoCompleteMessage(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(crashEventService.getSimilarMessages(term));
    }
} 