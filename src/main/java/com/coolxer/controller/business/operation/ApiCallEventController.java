package com.coolxer.controller.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.ApiCallEventDto;
import com.coolxer.model.business.operation.vo.ApiCallEventVo;
import com.coolxer.service.business.operation.ApiCallEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation/api_call")
public class ApiCallEventController {
    @Autowired
    private ApiCallEventService apiCallEventService;

    @PostMapping("/add")
    public ResponseWrap<?> add(@RequestBody ApiCallEventDto dto) {
        return apiCallEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return apiCallEventService.delete(id) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/bulk/{ids}")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        return apiCallEventService.deleteAll(ids) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @PostMapping("/{id}/update")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody ApiCallEventDto dto) {
        return apiCallEventService.update(id, dto) ? ResponseWrap.success("修改成功") : ResponseWrap.fail();
    }

    @GetMapping("/list")
    public ResponseWrap<PageRowsVo<ApiCallEventVo>> list(ApiCallEventDto searchDto) {
        return ResponseWrap.success(apiCallEventService.getPageList(searchDto));
    }

    @GetMapping("/{id}/view")
    public ResponseWrap<ApiCallEventVo> query(@PathVariable("id") String id) {
        ApiCallEventVo vo = apiCallEventService.getOne(id);
        return vo == null ? ResponseWrap.fail() : ResponseWrap.success(vo);
    }

    @GetMapping("/auto_complete/id")
    public ResponseWrap<List<String>> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(apiCallEventService.getSimilarIds(term));
    }

    @GetMapping("/auto_complete/caller")
    public ResponseWrap<List<String>> autoCompleteCaller(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(apiCallEventService.getSimilarCallers(term));
    }

    @GetMapping("/auto_complete/callee")
    public ResponseWrap<List<String>> autoCompleteCallee(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(apiCallEventService.getSimilarCallees(term));
    }

    @GetMapping("/auto_complete/function_name")
    public ResponseWrap<List<String>> autoCompleteFunctionName(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(apiCallEventService.getSimilarFunctionNames(term));
    }
} 