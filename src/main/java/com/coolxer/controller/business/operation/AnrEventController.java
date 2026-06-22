package com.coolxer.controller.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.AnrEventDto;
import com.coolxer.model.business.operation.vo.AnrEventVo;
import com.coolxer.service.business.operation.AnrEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation/anr")
public class AnrEventController {
    @Autowired
    private AnrEventService anrEventService;

    @PostMapping("/add")
    public ResponseWrap<?> add(@RequestBody AnrEventDto dto) {
        return anrEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return anrEventService.delete(id) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/bulk/{ids}")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        return anrEventService.deleteAll(ids) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @PostMapping("/{id}/update")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AnrEventDto dto) {
        return anrEventService.update(id, dto) ? ResponseWrap.success("修改成功") : ResponseWrap.fail();
    }

    @GetMapping("/list")
    public ResponseWrap<PageRowsVo<AnrEventVo>> list(AnrEventDto searchDto) {
        return ResponseWrap.success(anrEventService.getPageList(searchDto));
    }

    @GetMapping("/{id}/view")
    public ResponseWrap<AnrEventVo> query(@PathVariable("id") String id) {
        AnrEventVo vo = anrEventService.getOne(id);
        return vo == null ? ResponseWrap.fail() : ResponseWrap.success(vo);
    }

    @GetMapping("/auto_complete/id")
    public ResponseWrap<List<String>> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(anrEventService.getSimilarIds(term));
    }

    @GetMapping("/auto_complete/page_path")
    public ResponseWrap<List<String>> autoCompletePagePath(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(anrEventService.getSimilarPagePaths(term));
    }

    @GetMapping("/auto_complete/page_name")
    public ResponseWrap<List<String>> autoCompletePageName(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(anrEventService.getSimilarPageNames(term));
    }
} 