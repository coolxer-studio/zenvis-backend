package com.coolxer.controller.business.operation;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.operation.dto.OperationBoardDto;
import com.coolxer.service.business.operation.OperationBoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 看板管理
 */
@Tag(name = "看板管理")
@Slf4j
@RestController
@RequestMapping("/api/v1/operation")
public class OperationController extends BaseController {

    @Autowired
    private OperationBoardService operationBoardService;

    @GetMapping({"/dashboard"})
    public ResponseWrap<?> dashboard() {
        Map<String, Object> map = new HashMap<>();
        map.put("panel_list", operationBoardService.getAll());
        return ResponseWrap.success(map);
    }

    @GetMapping({"/dashboard/{id}/chart"})
    public ResponseWrap<?> dashboardChart(@PathVariable("id") long id) {
        try {
            return ResponseWrap.success(operationBoardService.getChartById(id));
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/dashboard/add"})
    public ResponseWrap<?> add(@RequestBody OperationBoardDto operationBoardDto) {

        try {
            if (operationBoardService.add(operationBoardDto)) {
                return ResponseWrap.success("创建成功");
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseWrap<?> delete(@PathVariable("id") long id) {
        try {
            operationBoardService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

}
