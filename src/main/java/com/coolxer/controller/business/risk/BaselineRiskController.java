package com.coolxer.controller.business.risk;

import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.risk.dto.BaselineRiskDto;
import com.coolxer.model.business.risk.dto.BaselineRiskSearchDto;
import com.coolxer.service.business.risk.BaselineRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/risk/baseline")
public class BaselineRiskController {

    @Autowired
    private BaselineRiskService baselineRiskService;

    @PostMapping
    public ResponseWrap<?> add(@RequestBody BaselineRiskDto dto) {
        return baselineRiskService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return baselineRiskService.delete(id) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping
    public ResponseWrap<?> deleteAll(@RequestBody List<String> ids) {
        return baselineRiskService.deleteAll(ids) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @PutMapping("/{id}")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody BaselineRiskDto dto) {
        return baselineRiskService.update(id, dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @GetMapping("/{id}")
    public ResponseWrap<?> getOne(@PathVariable("id") String id) {
        return ResponseWrap.success(baselineRiskService.getOne(id));
    }

    @GetMapping("/list")
    public ResponseWrap<?> getPageList(BaselineRiskSearchDto searchDto) {
        return ResponseWrap.success(baselineRiskService.getPageList(searchDto));
    }

    @GetMapping("/labels")
    public ResponseWrap<?> getDistinctLabels() {
        return ResponseWrap.success(baselineRiskService.getDistinctLabels());
    }

    @GetMapping("/ids")
    public ResponseWrap<?> getSimilarIds(@RequestParam(required = false) String term) {
        return ResponseWrap.success(baselineRiskService.getSimilarIds(term));
    }

    @GetMapping("/names")
    public ResponseWrap<?> getSimilarConfigurationNames(@RequestParam(required = false) String term) {
        return ResponseWrap.success(baselineRiskService.getSimilarConfigurationNames(term));
    }

    @GetMapping("/statistics/total")
    public ResponseWrap<?> countTotal() {
        return ResponseWrap.success(baselineRiskService.countTotal());
    }

    @GetMapping("/statistics/increase")
    public ResponseWrap<?> countIncrease() {
        return ResponseWrap.success(baselineRiskService.countIncrease());
    }


    @GetMapping("/auto_complete/configuration_name")
    public ResponseWrap<List<String>> autoCompleteConfigurationName(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(new ArrayList<>());
    }

    @GetMapping("/auto_complete/expected_value")
    public ResponseWrap<List<String>> autoCompleteExpectedValue(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(new ArrayList<>());
    }

    @GetMapping("/auto_complete/current_value")
    public ResponseWrap<List<String>> autoCompleteCurrentValue(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(new ArrayList<>());
    }
} 