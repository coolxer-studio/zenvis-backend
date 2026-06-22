package com.coolxer.controller.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.ClickEventDto;
import com.coolxer.model.business.operation.vo.ClickEventVo;
import com.coolxer.service.business.operation.ClickEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation/click")
public class ClickEventController {
    @Autowired
    private ClickEventService clickEventService;

    @PostMapping("/add")
    public ResponseWrap<?> add(@RequestBody ClickEventDto dto) {
        return clickEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return clickEventService.delete(id) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/bulk/{ids}")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        return clickEventService.deleteAll(ids) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @PostMapping("/{id}/update")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody ClickEventDto dto) {
        return clickEventService.update(id, dto) ? ResponseWrap.success("修改成功") : ResponseWrap.fail();
    }

    @GetMapping("/list")
    public ResponseWrap<PageRowsVo<ClickEventVo>> list(ClickEventDto searchDto) {
        return ResponseWrap.success(clickEventService.getPageList(searchDto));
    }

    @GetMapping("/{id}/view")
    public ResponseWrap<ClickEventVo> query(@PathVariable("id") String id) {
        ClickEventVo vo = clickEventService.getOne(id);
        return vo == null ? ResponseWrap.fail() : ResponseWrap.success(vo);
    }

    @GetMapping("/auto_complete/id")
    public ResponseWrap<List<String>> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(clickEventService.getSimilarIds(term));
    }

    @GetMapping("/auto_complete/component_name")
    public ResponseWrap<List<String>> autoCompleteComponentName(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(clickEventService.getSimilarComponentNames(term));
    }

    @GetMapping("/auto_complete/page_path")
    public ResponseWrap<List<String>> autoCompletePagePath(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(clickEventService.getSimilarPagePaths(term));
    }

    @GetMapping("/auto_complete/page_name")
    public ResponseWrap<List<String>> autoCompletePageName(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(clickEventService.getSimilarPageNames(term));
    }
} 