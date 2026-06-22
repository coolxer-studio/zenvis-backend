package com.coolxer.controller.business.risk;

import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.risk.dto.DataRiskDto;
import com.coolxer.model.business.risk.dto.DataRiskSearchDto;
import com.coolxer.service.business.risk.DataRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/risk/data")
public class DataRiskController {

    @Autowired
    private DataRiskService dataRiskService;

    @PostMapping
    public ResponseWrap<?> add(@RequestBody DataRiskDto dto) {
        return dataRiskService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return dataRiskService.delete(id) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping
    public ResponseWrap<?> deleteAll(@RequestBody List<String> ids) {
        return dataRiskService.deleteAll(ids) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @PutMapping("/{id}")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody DataRiskDto dto) {
        return dataRiskService.update(id, dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @GetMapping("/{id}")
    public ResponseWrap<?> getOne(@PathVariable("id") String id) {
        return ResponseWrap.success(dataRiskService.getOne(id));
    }

    @GetMapping("/list")
    public ResponseWrap<?> getPageList(DataRiskSearchDto searchDto) {
        return ResponseWrap.success(dataRiskService.getPageList(searchDto));
    }

    @GetMapping("/labels")
    public ResponseWrap<?> getDistinctLabels() {
        return ResponseWrap.success(dataRiskService.getDistinctLabels());
    }

    @GetMapping("/ids")
    public ResponseWrap<?> getSimilarIds(@RequestParam(required = false) String term) {
        return ResponseWrap.success(dataRiskService.getSimilarIds(term));
    }

    @GetMapping("/types")
    public ResponseWrap<?> getSimilarTypes(@RequestParam(required = false) String term) {
        return ResponseWrap.success(dataRiskService.getSimilarTypes(term));
    }

    @GetMapping("/statistics/total")
    public ResponseWrap<?> countTotal() {
        return ResponseWrap.success(dataRiskService.countTotal());
    }

    @GetMapping("/statistics/increase")
    public ResponseWrap<?> countIncrease() {
        return ResponseWrap.success(dataRiskService.countIncrease());
    }

    @GetMapping("/auto_complete/type")
    public ResponseWrap<List<String>> autoCompleteType(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(new ArrayList<>());
    }
} 