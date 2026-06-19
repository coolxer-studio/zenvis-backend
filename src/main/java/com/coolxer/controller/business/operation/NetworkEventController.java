package com.coolxer.controller.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.NetworkEventDto;
import com.coolxer.model.business.operation.vo.NetworkEventVo;
import com.coolxer.service.business.operation.NetworkEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation/network")
public class NetworkEventController {
    @Autowired
    private NetworkEventService networkEventService;

    @PostMapping("/add")
    public ResponseWrap<?> add(@RequestBody NetworkEventDto dto) {
        return networkEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return networkEventService.delete(id) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/bulk/{ids}")
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        return networkEventService.deleteAll(ids) ? ResponseWrap.success("删除成功") : ResponseWrap.fail();
    }

    @PostMapping("/{id}/update")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody NetworkEventDto dto) {
        return networkEventService.update(id, dto) ? ResponseWrap.success("修改成功") : ResponseWrap.fail();
    }

    @GetMapping("/list")
    public ResponseWrap<PageRowsVo<NetworkEventVo>> list(NetworkEventDto searchDto) {
        return ResponseWrap.success(networkEventService.getPageList(searchDto));
    }

    @GetMapping("/{id}/view")
    public ResponseWrap<NetworkEventVo> query(@PathVariable("id") String id) {
        NetworkEventVo vo = networkEventService.getOne(id);
        return vo == null ? ResponseWrap.fail() : ResponseWrap.success(vo);
    }

    @GetMapping("/auto_complete/id")
    public ResponseWrap<List<String>> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(networkEventService.getSimilarIds(term));
    }

    @GetMapping("/auto_complete/source_ip")
    public ResponseWrap<List<String>> autoCompleteSourceIp(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(networkEventService.getSimilarSourceIps(term));
    }

    @GetMapping("/auto_complete/target_ip")
    public ResponseWrap<List<String>> autoCompleteTargetIp(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(networkEventService.getSimilarTargetIps(term));
    }
} 