package com.coolxer.controller.business.risk;

import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.risk.dto.RiskEventDto;
import com.coolxer.model.business.risk.dto.RiskEventSearchDto;
import com.coolxer.service.business.risk.RiskEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/risk/event")
public class RiskEventController {

    @Autowired
    private RiskEventService riskEventService;

    @PostMapping
    public ResponseWrap<?> add(@RequestBody RiskEventDto dto) {
        return riskEventService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return riskEventService.delete(id) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping
    public ResponseWrap<?> deleteAll(@RequestBody List<String> ids) {
        return riskEventService.deleteAll(ids) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @PutMapping("/{id}")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody RiskEventDto dto) {
        return riskEventService.update(id, dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @GetMapping("/{id}")
    public ResponseWrap<?> getOne(@PathVariable("id") String id) {
        return ResponseWrap.success(riskEventService.getOne(id));
    }

    @GetMapping("/list")
    public ResponseWrap<?> getPageList(RiskEventSearchDto searchDto) {
        return ResponseWrap.success(riskEventService.getPageList(searchDto));
    }

    @GetMapping("/labels")
    public ResponseWrap<?> getDistinctLabels() {
        return ResponseWrap.success(riskEventService.getDistinctLabels());
    }

    @GetMapping("/ids")
    public ResponseWrap<?> getSimilarIds(@RequestParam(required = false) String term) {
        return ResponseWrap.success(riskEventService.getSimilarIds(term));
    }

    @GetMapping("/types")
    public ResponseWrap<?> getSimilarTypes(@RequestParam(required = false) String term) {
        return ResponseWrap.success(riskEventService.getSimilarTypes(term));
    }

    @GetMapping("/statistics/total")
    public ResponseWrap<?> countTotal() {
        return ResponseWrap.success(riskEventService.countTotal());
    }

    @GetMapping("/statistics/increase")
    public ResponseWrap<?> countIncrease() {
        return ResponseWrap.success(riskEventService.countIncrease());
    }

    @GetMapping("/auto_complete/type")
    public ResponseWrap<List<String>> autoCompleteType(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(new ArrayList<>());
    }

} 