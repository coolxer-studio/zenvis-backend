package com.coolxer.controller.business.risk;

import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.risk.dto.AttackRiskDto;
import com.coolxer.model.business.risk.dto.AttackRiskSearchDto;
import com.coolxer.service.business.risk.AttackRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/risk/attack")
public class AttackRiskController {

    @Autowired
    private AttackRiskService attackRiskService;

    @PostMapping
    public ResponseWrap<?> add(@RequestBody AttackRiskDto dto) {
        return attackRiskService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return attackRiskService.delete(id) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping
    public ResponseWrap<?> deleteAll(@RequestBody List<String> ids) {
        return attackRiskService.deleteAll(ids) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @PutMapping("/{id}")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AttackRiskDto dto) {
        return attackRiskService.update(id, dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @GetMapping("/{id}")
    public ResponseWrap<?> getOne(@PathVariable("id") String id) {
        return ResponseWrap.success(attackRiskService.getOne(id));
    }

    @GetMapping("/list")
    public ResponseWrap<?> getPageList(AttackRiskSearchDto searchDto) {
        return ResponseWrap.success(attackRiskService.getPageList(searchDto));
    }

    @GetMapping("/labels")
    public ResponseWrap<?> getDistinctLabels() {
        return ResponseWrap.success(attackRiskService.getDistinctLabels());
    }

    @GetMapping("/ids")
    public ResponseWrap<?> getSimilarIds(@RequestParam(required = false) String term) {
        return ResponseWrap.success(attackRiskService.getSimilarIds(term));
    }

    @GetMapping("/types")
    public ResponseWrap<?> getSimilarTypes(@RequestParam(required = false) String term) {
        return ResponseWrap.success(attackRiskService.getSimilarTypes(term));
    }

    @GetMapping("/statistics/total")
    public ResponseWrap<?> countTotal() {
        return ResponseWrap.success(attackRiskService.countTotal());
    }

    @GetMapping("/statistics/increase")
    public ResponseWrap<?> countIncrease() {
        return ResponseWrap.success(attackRiskService.countIncrease());
    }

    @GetMapping("/auto_complete/type")
    public ResponseWrap<List<String>> autoCompleteType(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(new ArrayList<>());
    }

} 