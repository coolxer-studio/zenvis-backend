package com.coolxer.service.business.operation.impl;

import com.coolxer.dao.mysql.entity.OperationBoard;
import com.coolxer.dao.mysql.repository.OperationBoardRepository;
import com.coolxer.model.business.operation.dto.OperationBoardDto;
import com.coolxer.model.business.operation.vo.OperationBoardVo;
import com.coolxer.service.business.operation.OperationBoardService;
import com.coolxer.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OperationBoardServiceImpl implements OperationBoardService {

    @Autowired
    private OperationBoardRepository operationBoardRepository;

    @Override
    @Transactional
    public Boolean add(OperationBoardDto operationBoardDto) {
        if (operationBoardDto == null) {
            log.warn("OperationBoardDto is null, cannot add OperationBoard.");
            return false;
        }

        try {
            OperationBoard operationBoard = new OperationBoard();
            // 设置默认icon
            switch (operationBoardDto.getPolicy()) {
                case "analysis_event":
                    operationBoard.setIcon("fa-solid fa-arrow-trend-up");
                    break;
                case "analysis_funnel":
                    operationBoard.setIcon("fa-solid fa-filter");
                    break;
                case "analysis_distribution":
                    operationBoard.setIcon("fa-solid fa-chart-column");
                    break;
                case "analysis_path":
                    operationBoard.setIcon("fa-solid fa-draw-polygon");
                    break;
                default:
                    break;

            }
            // 从DTO更新其他属性
            operationBoard.updateFromDto(operationBoardDto);

            // 先保存，获取ID
            operationBoardRepository.save(operationBoard);

            // 处理链表关系
            Long lastBoardId = operationBoard.getLastBoard();
            if (lastBoardId != null && lastBoardId > 0) {
                OperationBoard lastOperationBoard = operationBoardRepository.findById(lastBoardId).orElse(null);
                if (lastOperationBoard != null) {
                    // 新节点的nextBoard继承上一个节点的nextBoard
                    if (lastOperationBoard.getNextBoard() != null) {
                        operationBoard.setNextBoard(lastOperationBoard.getNextBoard());
                        operationBoardRepository.save(operationBoard);
                    }
                    // 上一个节点的nextBoard指向新节点
                    lastOperationBoard.setNextBoard((long) operationBoard.getId());
                    operationBoardRepository.save(lastOperationBoard);
                } else {
                    log.warn("Last OperationBoard (id={}) not found.", lastBoardId);
                }
            }

            return true;
        } catch (Exception e) {
            log.error("添加运营看板失败, dto={}, error={}", operationBoardDto, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void delete(Long id) {
        OperationBoard operationBoard = operationBoardRepository.findById(id).orElse(null);
        if (operationBoard.getLastBoard() != null) {
            OperationBoard lastOperationBoard = operationBoardRepository.findById(operationBoard.getLastBoard()).orElse(null);
            lastOperationBoard.setNextBoard(operationBoard.getNextBoard() == null ? null : operationBoard.getNextBoard());
            operationBoardRepository.save(lastOperationBoard);
        } else if (operationBoard.getNextBoard() != null) {
            OperationBoard nextOperationBoard = operationBoardRepository.findById(operationBoard.getNextBoard()).orElse(null);
            nextOperationBoard.setLastBoard(operationBoard.getLastBoard());
            operationBoardRepository.save(nextOperationBoard);
        }
        operationBoardRepository.deleteById(id);
    }

    @Override
    public List<List<OperationBoardVo>> getAll() {

        List<List<OperationBoardVo>> operationBoardVoResult = new ArrayList<>();
        List<OperationBoard> operationBoards = operationBoardRepository.findAll();
        for (OperationBoard operationBoard : operationBoards) {
            if (operationBoard.getLastBoard() == null && operationBoard.getNextBoard() == null) {
                // 单独头节点
                List<OperationBoardVo> operationBoardVos = new ArrayList<>();
                operationBoardVos.add(new OperationBoardVo(operationBoard));
                operationBoardVoResult.add(operationBoardVos);
            } else if (operationBoard.getLastBoard() == null && operationBoard.getNextBoard() != null) {
                // 带有后续的头节点
                List<OperationBoardVo> operationBoardVos = new ArrayList<>();
                operationBoardVos.add(new OperationBoardVo(operationBoard));
                // 添加后续节点
                Long index = operationBoard.getNextBoard();
                while (index != null) {
                    OperationBoard operationBoard2 = operationBoardRepository.findById(index).orElse(null);
                    if (operationBoard2 == null) {
                        break;
                    } else {
                        operationBoardVos.add(new OperationBoardVo(operationBoard2));
                        index = operationBoard2.getNextBoard();
                    }
                }
                operationBoardVoResult.add(operationBoardVos);
            }
        }
        return operationBoardVoResult;
    }

    @Override
    public Object getChartById(long id) {
        OperationBoard operationBoard = operationBoardRepository.findById(id).orElse(null);
        if (operationBoard == null) {
            return null;
        }
        String result = "{\"title\":{\"text\":\"销售情况\"},\"tooltip\":{},\"legend\":{\"data\":[\"销量\"]},\"xAxis\":{\"data\":[\"衬衫\",\"羊毛衫\",\"雪纺衫\",\"裤子\",\"高跟鞋\",\"袜子\"]},\"yAxis\":{},\"series\":[{\"name\":\"销量\",\"type\":\"bar\",\"data\":[35,25,2,86,63,56]}]}";
        switch (operationBoard.getView()) {
            case "地图分布":
                result = "{\"title\":{\"text\":\"全国分布\"},\"tooltip\":{\"trigger\":\"item\",\"formatter\":\"{b}<br/>{c}\"},\"toolbox\":{\"show\":true,\"orient\":\"vertical\",\"left\":\"right\",\"top\":\"center\",\"feature\":{\"dataView\":{\"readOnly\":false},\"restore\":{},\"saveAsImage\":{}}},\"visualMap\":{\"min\":800,\"max\":50000,\"text\":[\"High\",\"Low\"],\"realtime\":false,\"calculable\":true,\"inRange\":{\"color\":[\"lightskyblue\",\"yellow\",\"orangered\"]}},\"series\":[{\"name\":\"用户分布\",\"type\":\"map\",\"map\":\"china\",\"label\":{\"show\":true},\"data\":[{\"name\":\"北京\",\"value\":20057.34},{\"name\":\"上海\",\"value\":15477.48},{\"name\":\"广东\",\"value\":31686.1},{\"name\":\"河南\",\"value\":6992.6},{\"name\":\"河北\",\"value\":44045.49},{\"name\":\"天津\",\"value\":40689.64},{\"name\":\"四川\",\"value\":37659.78},{\"name\":\"湖北\",\"value\":45180.97},{\"name\":\"安徽\",\"value\":55204.26},{\"name\":\"吉林\",\"value\":21900.9},{\"name\":\"黑龙江\",\"value\":4918.26},{\"name\":\"福建\",\"value\":5881.84},{\"name\":\"广西\",\"value\":4178.01},{\"name\":\"西藏\",\"value\":2227.92},{\"name\":\"新疆\",\"value\":2180.98},{\"name\":\"湖南\",\"value\":9172.94},{\"name\":\"江西\",\"value\":3368},{\"name\":\"香港\",\"value\":806.98}]}]}";
                break;
            case "漏斗图":
                result = "{\"title\":{\"text\":\"营销转化漏斗\",\"left\":\"center\"},\"tooltip\":{\"trigger\":\"item\",\"formatter\":\"{a} <br/>{b} : {c}%\"},\"legend\":{\"orient\":\"vertical\",\"left\":\"left\",\"data\":[\"访问\",\"咨询\",\"下单\",\"支付\",\"完成\"]},\"series\":[{\"name\":\"转化率\",\"type\":\"funnel\",\"left\":\"10%\",\"top\":60,\"bottom\":60,\"width\":\"80%\",\"height\":\"50%\",\"size\":[\"60%\",\"80%\"],\"sort\":\"descending\",\"gap\":2,\"label\":{\"show\":true,\"position\":\"inside\"},\"labelLine\":{\"length\":10,\"lineStyle\":{\"width\":1,\"type\":\"solid\"}},\"itemStyle\":{\"borderColor\":\"#fff\",\"borderWidth\":1},\"emphasis\":{\"label\":{\"fontSize\":20}},\"data\":[{\"value\":100,\"name\":\"访问\"},{\"value\":80,\"name\":\"咨询\"},{\"value\":60,\"name\":\"下单\"},{\"value\":40,\"name\":\"支付\"},{\"value\":20,\"name\":\"完成\"}]}]}";
                break;
            case "折线图":
                result = "{\"title\":{\"text\":\"用户活跃度趋势\",\"left\":\"center\"},\"tooltip\":{\"trigger\":\"axis\"},\"legend\":{\"orient\":\"vertical\",\"left\":\"left\",\"data\":[\"日活跃用户\",\"周活跃用户\"]},\"xAxis\":{\"type\":\"category\",\"boundaryGap\":false,\"data\":[\"1月\",\"2月\",\"3月\",\"4月\",\"5月\",\"6月\",\"7月\",\"8月\",\"9月\",\"10月\",\"11月\",\"12月\"]},\"yAxis\":{\"type\":\"value\"},\"series\":[{\"name\":\"日活跃用户\",\"type\":\"line\",\"data\":[200,230,201,254,290,330,310,320,332,301,334,390],\"markLine\":{\"data\":[{\"type\":\"average\",\"name\":\"平均值\"}]}},{\"name\":\"周活跃用户\",\"type\":\"line\",\"data\":[150,230,200,235,270,310,300,310,320,330,340,350],\"markLine\":{\"data\":[{\"type\":\"average\",\"name\":\"平均值\"}]}}]}";
                break;
            case "柱状图":
                result = "{\"title\":{\"text\":\"月度营销活动效果\",\"left\":\"center\"},\"tooltip\":{\"trigger\":\"axis\",\"axisPointer\":{\"type\":\"shadow\"}},\"legend\":{\"orient\":\"vertical\",\"left\":\"left\",\"data\":[\"销售额\",\"参与人数\"]},\"xAxis\":{\"type\":\"category\",\"data\":[\"1月\",\"2月\",\"3月\",\"4月\",\"5月\",\"6月\",\"7月\",\"8月\",\"9月\",\"10月\",\"11月\",\"12月\"]},\"yAxis\":[{\"type\":\"value\",\"name\":\"销售额（万元）\"},{\"type\":\"value\",\"name\":\"参与人数（人）\"}],\"series\":[{\"name\":\"销售额\",\"type\":\"bar\",\"yAxisIndex\":0,\"data\":[230,340,450,560,670,780,890,900,1000,1100,1200,1300]},{\"name\":\"参与人数\",\"type\":\"bar\",\"yAxisIndex\":1,\"data\":[1200,1300,1400,1500,1600,1700,1800,1900,2000,2100,2200,2300]}]}";
                break;
            case "饼图":
                result = "{\"title\":{\"text\":\"营销渠道分布\",\"left\":\"center\"},\"tooltip\":{\"trigger\":\"item\",\"formatter\":\"{a} <br/>{b}: {c} ({d}%)\"},\"legend\":{\"orient\":\"vertical\",\"left\":\"left\",\"data\":[\"社交媒体\",\"搜索引擎\",\"电子邮件\",\"线下活动\",\"合作伙伴\"]},\"series\":[{\"name\":\"渠道\",\"type\":\"pie\",\"radius\":\"50%\",\"data\":[{\"value\":335,\"name\":\"社交媒体\"},{\"value\":310,\"name\":\"搜索引擎\"},{\"value\":234,\"name\":\"电子邮件\"},{\"value\":135,\"name\":\"线下活动\"},{\"value\":154,\"name\":\"合作伙伴\"}],\"emphasis\":{\"itemStyle\":{\"shadowBlur\":10,\"shadowOffsetX\":0,\"shadowColor\":\"rgba(0, 0, 0, 0.5)\"}}}]}";
                break;
            default:
                break;
        }
        Object jsonObject = JacksonUtil.toObject(result, Object.class);
        return jsonObject;
    }
}