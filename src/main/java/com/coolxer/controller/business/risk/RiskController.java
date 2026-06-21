package com.coolxer.controller.business.risk;

import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.service.business.operation.OperationBoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 看板管理
 */
@Tag(name = "看板管理")
@Slf4j
@RestController
@RequestMapping("/api/v1/risk")
public class RiskController extends BaseController {

    @Autowired
    private OperationBoardService operationBoardService;

    @GetMapping({"/timeline"})
    public ResponseWrap<?> count(@RequestParam(value = "name", required = false) String name) {
        try {
            String result = "";
// 当前时间
            LocalDateTime now = LocalDateTime.now();
            // 时间格式化器
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // 时间范围：从当前时间向前推3个月
            LocalDateTime threeMonthsAgo = now.minusMonths(3);

            // 生成数据结构
            Map<String, Object> dataStructure = new HashMap<>();

            Random random = new Random();
            LocalDateTime currentDate = now;

            // 创建子列表并添加到数据结构中
            List<Object> xAxis = new ArrayList<>();
            List<Object> series = new ArrayList<>();

            while (currentDate.isAfter(threeMonthsAgo)) {
                // 生成随机数据值
                int randomValue = random.nextInt(200); // 随机生成0到199之间的整数

                // 将时间格式化为字符串
                String formattedDate = currentDate.format(formatter);

                xAxis.add(formattedDate);
                series.add(randomValue);
                // 时间向前推1小时
                currentDate = currentDate.minusHours(1);
            }
            dataStructure.put("title_name", StringUtils.isNotEmpty(name) ? name : "全部风险");
            dataStructure.put("x_axis", xAxis);
            dataStructure.put("series", series);
            return ResponseWrap.success(dataStructure);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

}
