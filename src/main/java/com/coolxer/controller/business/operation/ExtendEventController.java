package com.coolxer.controller.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.ExtendEventDto;
import com.coolxer.model.business.operation.vo.ExtendEventVo;
import com.coolxer.service.business.operation.ExtendEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation/extend")
public class ExtendEventController {
    @Autowired
    private ExtendEventService extendEventService;

    @PostMapping("/add")
    public ResponseWrap<?> add(@RequestBody ExtendEventDto dto) {
        return extendEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return extendEventService.delete(id) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/bulk/{ids}")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        return extendEventService.deleteAll(ids) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @PostMapping("/{id}/update")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody ExtendEventDto dto) {
        return extendEventService.update(id, dto) ? ResponseWrap.success("修改成功") : ResponseWrap.fail();
    }

    @GetMapping("/list")
    public ResponseWrap<PageRowsVo<ExtendEventVo>> list(ExtendEventDto searchDto) {
        return ResponseWrap.success(extendEventService.getPageList(searchDto));
    }

    @GetMapping("/{id}/view")
    public ResponseWrap<ExtendEventVo> query(@PathVariable("id") String id) {
        ExtendEventVo vo = extendEventService.getOne(id);
        return vo == null ? ResponseWrap.fail() : ResponseWrap.success(vo);
    }

    @GetMapping("/auto_complete/id")
    public ResponseWrap<List<String>> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(extendEventService.getSimilarIds(term));
    }
} 