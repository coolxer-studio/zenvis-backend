package com.coolxer.controller.retrieval;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.retrieval.dto.RetrievalRequestDto;
import com.coolxer.model.retrieval.vo.DataAttributeResultVo;
import com.coolxer.model.retrieval.vo.DataEntityResultVo;
import com.coolxer.model.retrieval.vo.DataListVo;
import com.coolxer.service.retrieval.RetrievalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 检索
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/v1/retrieval")
public class RetrievalController extends BaseController {

    @Autowired
    RetrievalService retrievalService;

    // 条件检索请求
    // /data/search/criteria

    @PostMapping(value = "/do")
    @ApiOperation(value = "数据检索", notes = "")
    public ResponseWrap<DataListVo> searchByCriteria(@RequestBody RetrievalRequestDto retrievalRequestDTO) {
        if (retrievalRequestDTO.getDisplayList().get(0).getAttributeList().size() < 2) {
            return ResponseWrap.fail(ResultCodeEnum.DISPLAY_LIMIT_ERROR);
        }
        DataListVo dataList = retrievalService.retrievalByCriteria(retrievalRequestDTO);
        return ResponseWrap.success(dataList);
    }

    // 创建检索规则
    // /data/search/rule/create

    @PostMapping(value = "/rule/create")
    @ApiOperation(value = "检索规则创建", notes = "检索规则创建")
    public ResponseWrap createSearchRule(@RequestBody RetrievalRequestDto retrievalRequestDto) {
        Boolean ret = retrievalService.createRule(retrievalRequestDto);
        return ret ? ResponseWrap.success() : ResponseWrap.fail();
    }

    // 更新检索规则
    // /data/search/rule/update
    @PostMapping(value = "/rule/update")
    @ApiOperation(value = "检索规则更新", notes = "检索规则更新")
    public ResponseWrap updateSearchRule(@RequestBody RetrievalRequestDto retrievalRequestDto) {
        Boolean ret = retrievalService.updateRule(retrievalRequestDto);
        return ret ? ResponseWrap.success() : ResponseWrap.fail();
    }

    // 更新检索规则
    // /data/search/rule/update
    @PostMapping(value = "/rule/delete")
    @ApiOperation(value = "检索规则删除", notes = "检索规则删除")
    public ResponseWrap deleteSearchRule(@RequestBody RetrievalRequestDto retrievalRequestDto) {
        Boolean ret = retrievalService.deleteRule(retrievalRequestDto);
        return ret ? ResponseWrap.success() : ResponseWrap.fail();
    }

    // 检索规则列表
    @GetMapping(value = "/rule/list")
    @ApiOperation(value = "检索规则列表", notes = "检索规则列表")
    public ResponseWrap<DataListVo> listSearchRule() {
        DataListVo dataList = retrievalService.listRule();
        return ResponseWrap.success(dataList);
    }

    // 获取指定检索规则
    @GetMapping(value = "/rule/get")
    @ApiOperation(value = "获取指定检索规则", notes = "获取指定检索规则")
    public void getSearchRule(@RequestParam(value = "id") Integer id) {
        retrievalService.getRule(id);
    }

    // 根据检索规则id请求检索
    public void searchByRuleId(Integer id) {
        retrievalService.retrievalByRuleId(id);
    }

    // 数据实体列表
    @GetMapping(value = "/entity/list")
    @ApiOperation(value = "获取实体列表", notes = "获取实体列表")
    public ResponseWrap<DataEntityResultVo> listEntity(
            @RequestParam(value = "rule_id", required = false) Integer ruleId) {
        DataEntityResultVo dataEntityResultVo = retrievalService.listEntity(ruleId);
        return ResponseWrap.success(dataEntityResultVo);
    }

    // 数据属性列表
    @GetMapping(value = "/attribute/list")
    @ApiOperation(value = "获取属性列表", notes = "获取属性列表")
    public ResponseWrap<DataAttributeResultVo> listAttribute(
            @RequestParam(value = "entity", required = false) String entity,
            @RequestParam(value = "rule_id", required = false) Integer ruleId) {
        DataAttributeResultVo dataAttributeResultVo = retrievalService.listAttribute(entity, ruleId);
        return ResponseWrap.success(dataAttributeResultVo);
    }

    // 获取指定属性值列表
    @GetMapping(value = "/candidate/list")
    @ApiOperation(value = "获取指定字段备选信息", notes = "获取指定字段备选信息")
    public ResponseWrap<DataListVo> listCandidateValue(Integer attributeId, String text) {
        retrievalService.listCandidate(attributeId, text);
        DataListVo dataList = retrievalService.listCandidate(attributeId, text);
        return ResponseWrap.success(dataList);
    }

    @GetMapping(value = "/display/entity/list")
    @ApiOperation(value = "获取展示实体列表", notes = "获取展示实体列表")
    public ResponseWrap<DataEntityResultVo> listDisplayEntity(
            @RequestParam(value = "rule_id", required = false) Integer ruleId) {
        DataEntityResultVo dataEntityResultVo = retrievalService.listEntity(ruleId);
        return ResponseWrap.success(dataEntityResultVo);
    }

    @GetMapping(value = "/display/attribute/list")
    @ApiOperation(value = "获取展示属性列表", notes = "获取展示属性列表")
    public ResponseWrap<DataAttributeResultVo> listDisplayAttribute(
            @RequestParam(value = "entity", required = false) String entity,
            @RequestParam(value = "rule_id", required = false) Integer ruleId) {
        DataAttributeResultVo dataAttributeResultVo = retrievalService.listAttributeForDisplay(entity, ruleId);
        return ResponseWrap.success(dataAttributeResultVo);
    }

}
