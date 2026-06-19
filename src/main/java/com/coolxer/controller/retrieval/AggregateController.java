package com.coolxer.controller.retrieval;

import com.coolxer.configuration.CustomWebConfig;
import com.coolxer.controller.BaseController;
import com.coolxer.dao.clickhouse.repository.MsgRepository;
import com.coolxer.model.base.dto.BaseQueryDto;
import com.coolxer.model.base.vo.PageView;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.dashboard.vo.StackedLineChartVo;
import com.coolxer.model.retrieval.vo.AggregateMsgInfoVo;
import com.coolxer.model.retrieval.vo.DataMsgVo;
import com.coolxer.service.retrieval.AggregateService;
import com.coolxer.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 数据检索
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/v1/retrieval/aggregate")
public class AggregateController extends BaseController {


    @Autowired
    private CustomWebConfig customWebConfig;

    @Autowired
    private MsgRepository msgRepository;

    @Autowired
    private AggregateService aggregateService;

    /**
     * 设备详情
     *
     * @return 结果
     */
    @GetMapping(value = "/msg/tag")
    @ApiOperation(value = "msg基础数据标签", notes = "msg基础数据标签")
    public ResponseWrap<AggregateMsgInfoVo> msgTag(@RequestParam Map<String, String> params) {
        // 验证必需参数
        if (params == null || params.isEmpty()) {
            throw new IllegalArgumentException("缺少必需参数");
        }
        //返回数据
        return ResponseWrap.success(aggregateService.findAgendaTagsByParams(params));
    }

    /**
     * 数据分布
     *
     * @return 结果
     */
    @GetMapping(value = "/msg/trend")
    @ApiOperation(value = "数据分布", notes = "数据分布")
    public ResponseWrap<StackedLineChartVo> msgTrend(@RequestParam Map<String, String> params) {
        // 验证必需参数
        if (params == null || params.isEmpty()) {
            throw new IllegalArgumentException("缺少必需参数");
        }

        //返回数据
        return ResponseWrap.success(aggregateService.findMsgTrend(params));
    }


//    /**
//     * 设备详情
//     *
//     * @return 结果
//     */
//    @GetMapping(value = "/info")
//    @ApiOperation(value = "设备详情", notes = "设备详情")
//    public ResponseWrap<DeviceInfoVo> info(@RequestParam(value = "id") String id, @RequestParam(value = "start_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime, @RequestParam(value = "end_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {
//
//        // 1
//        DeviceInfoVo.Info deviceInfo = null;
//        Map<String, String> msgDevice = msgDeviceRepository.findOneByDeviceId(id);
//        if (msgDevice != null) {
//            deviceInfo = new DeviceInfoVo.Info(msgDevice.get("manufacturer"), msgDevice.get("model"),
//                    msgDevice.get("system_name") + msgDevice.get("system_version"), msgDevice.get("net_type"),
//                    msgDevice.get("lan_ip"), msgDevice.get("wan_ip"),
//                    msgDevice.get("platform"), msgDevice.get("detail"));
//        }
//
//        // 2
//        Map<String, Object> agendaTagsMap = msgRepository.findAgendaTagsByDeviceId(id, startTime, endTime);
//        ArrayList<DeviceInfoVo.Tag> tags = new ArrayList<>();
//
//        if (Objects.nonNull(agendaTagsMap) && agendaTagsMap.containsKey("agenda_tags_array")) {
//            String agendaTagsArrayString = (String) agendaTagsMap.get("agenda_tags_array");
//            // 使用flatMap将二维集合展平成一维
//            List<String> agendaTagsList = List.of(agendaTagsArrayString.split(","));
//            // 使用Collectors.groupingBy和Collectors.counting来统计元素出现次数
//            Map<String, Long> agendaTagsCountMap = agendaTagsList.stream()
//                    .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
//
//
//            Map<String, List<String>> tagMap = new HashMap<>();
//
//            agendaTagsCountMap.entrySet().forEach(entry -> {
//                String key = entry.getKey();
//                String label = key;
//                String level = "other";
//                if (key.contains(":")) {
//                    label = StringUtils.substringBeforeLast(entry.getKey(), ":");
//                    level = StringUtils.substringAfterLast(entry.getKey(), ":").toLowerCase();
//                }
//                String value = label + "(" + entry.getValue() + ")";
//                List valueList = tagMap.computeIfAbsent(level, v -> new ArrayList<String>());
//                valueList.add(value);
//            });
//
//
//            for (Map.Entry<String, List<String>> entry : tagMap.entrySet()) {
//                tags.add(new DeviceInfoVo.Tag(entry.getKey(), entry.getValue()));
//            }
//        }
//
//        //返回数据
//        return ResponseWrap.success(new DeviceInfoVo(tags, deviceInfo));
//    }

    /**
     * 数据链列表
     *
     * @param baseQueryDto 分页传输实体
     * @return 结果
     */
    @PostMapping(value = "/detail/{id}")
    @ApiOperation(value = "数据链列表", notes = "数据链列表")
    public ResponseWrap<PageView<DataMsgVo>> searchDetailByPage(@PathVariable("id") String id, @RequestBody BaseQueryDto baseQueryDto) {

        Page<Map<String, Object>> msgPage = msgRepository.findByPage(PageRequest.of(baseQueryDto.getPage() - 1, baseQueryDto.getSize()), id, baseQueryDto.getStartTime(), baseQueryDto.getEndTime());
        PageView<DataMsgVo> pageView = new PageView<>();
        pageView.setPage(baseQueryDto.getPage());
        pageView.setSize(baseQueryDto.getSize());
        pageView.setTotal(msgPage.getTotalElements());
        pageView.setItems(msgPage.stream().map(
                msgMap -> {
                    return new DataMsgVo((BigDecimal) msgMap.get("start_id"), (String) msgMap.get("app_name"), (String) msgMap.get("app_version"),
                            (String) msgMap.get("platform"), (String) msgMap.get("lan_ip"), (String) msgMap.get("wan_ip"),
                            (String) msgMap.get("fact_type"), (String) msgMap.get("agenda_tags"), (String) msgMap.get("punish_types"),
                            (String) msgMap.get("country") + (String) msgMap.get("province") + (String) msgMap.get("city") + (String) msgMap.get("county") + (String) msgMap.get("thoroughfare"),
                            DateUtil.SIMPLE_DATE_FORMAT.format((Date) msgMap.get("client_time")),
                            DateUtil.SIMPLE_DATE_FORMAT.format((Date) msgMap.get("server_time")),
                            (String) msgMap.get("data"));
                }
        ).toList());

        return ResponseWrap.success(pageView);
    }


}
