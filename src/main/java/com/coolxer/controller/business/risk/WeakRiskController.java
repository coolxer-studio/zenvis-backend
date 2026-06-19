package com.coolxer.controller.business.risk;

import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.risk.dto.WeakRiskDto;
import com.coolxer.model.business.risk.dto.WeakRiskSearchDto;
import com.coolxer.service.business.risk.WeakRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/risk/weak")
public class WeakRiskController {

    @Autowired
    private WeakRiskService weakRiskService;

    @PostMapping
    public ResponseWrap<?> add(@RequestBody WeakRiskDto dto) {
        return weakRiskService.add(dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        return weakRiskService.delete(id) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @DeleteMapping
    public ResponseWrap<?> deleteAll(@RequestBody List<String> ids) {
        return weakRiskService.deleteAll(ids) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @PutMapping("/{id}")
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody WeakRiskDto dto) {
        return weakRiskService.update(id, dto) ? ResponseWrap.success("添加成功") : ResponseWrap.fail();
    }

    @GetMapping("/{id}")
    public ResponseWrap<?> getOne(@PathVariable("id") String id) {
        return ResponseWrap.success(weakRiskService.getOne(id));
    }

    @GetMapping("/list")
    public ResponseWrap<?> getPageList(WeakRiskSearchDto searchDto) {
        return ResponseWrap.success(weakRiskService.getPageList(searchDto));
    }

    @GetMapping("/labels")
    public ResponseWrap<?> getDistinctLabels() {
        return ResponseWrap.success(weakRiskService.getDistinctLabels());
    }

    @GetMapping("/ids")
    public ResponseWrap<?> getSimilarIds(@RequestParam(required = false) String term) {
        return ResponseWrap.success(weakRiskService.getSimilarIds(term));
    }

    @GetMapping("/usernames")
    public ResponseWrap<?> getSimilarUsernames(@RequestParam(required = false) String term) {
        return ResponseWrap.success(weakRiskService.getSimilarUsernames(term));
    }

    @GetMapping("/password-types")
    public ResponseWrap<?> getSimilarPasswordTypes(@RequestParam(required = false) String term) {
        return ResponseWrap.success(weakRiskService.getSimilarPasswordTypes(term));
    }

    @GetMapping("/statistics/total")
    public ResponseWrap<?> countTotal() {
        return ResponseWrap.success(weakRiskService.countTotal());
    }

    @GetMapping("/statistics/increase")
    public ResponseWrap<?> countIncrease() {
        return ResponseWrap.success(weakRiskService.countIncrease());
    }

    @GetMapping("/auto_complete/username")
    public ResponseWrap<List<String>> autoCompleteUsername(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(new ArrayList<>());
    }

    @GetMapping("/auto_complete/password")
    public ResponseWrap<List<String>> autoCompletePassword(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(new ArrayList<>());
    }

    @GetMapping("/auto_complete/password_type")
    public ResponseWrap<List<String>> autoCompletePasswordType(@RequestParam(value = "term", required = false) String term) {
        return ResponseWrap.success(new ArrayList<>());
    }
} 