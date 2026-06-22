package com.coolxer.controller.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.PageEventDto;
import com.coolxer.model.business.operation.vo.PageEventVo;
import com.coolxer.service.business.operation.PageEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation/page")
public class PageEventController {
    @Autowired
    private PageEventService pageEventService;

    @PostMapping("/add")
    public ResponseWrap<?> add(@RequestBody PageEventDto dto) {
        return pageEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return pageEventService.delete(id) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/bulk/{ids}")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        return pageEventService.deleteAll(ids) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @PostMapping("/{id}/update")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody PageEventDto dto) {
        return pageEventService.update(id, dto) ? ResponseWrap.success("修改成功") : ResponseWrap.fail();
    }

    @GetMapping("/list")
    public ResponseWrap<PageRowsVo<PageEventVo>> list(PageEventDto searchDto) {
        return ResponseWrap.success(pageEventService.getPageList(searchDto));
    }

    @GetMapping("/{id}/view")
    public ResponseWrap<PageEventVo> query(@PathVariable("id") String id) {
        PageEventVo vo = pageEventService.getOne(id);
        return vo == null ? ResponseWrap.fail() : ResponseWrap.success(vo);
    }

    @GetMapping("/auto_complete/id")
    public ResponseWrap<List<String>> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(pageEventService.getSimilarIds(term));
    }

    @GetMapping("/auto_complete/page_path")
    public ResponseWrap<List<String>> autoCompletePagePath(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(pageEventService.getSimilarPagePaths(term));
    }

    @GetMapping("/auto_complete/page_name")
    public ResponseWrap<List<String>> autoCompletePageName(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(pageEventService.getSimilarPageNames(term));
    }

    @GetMapping("/auto_complete/referrer")
    public ResponseWrap<List<String>> autoCompleteReferrer(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(pageEventService.getSimilarReferrers(term));
    }
} 