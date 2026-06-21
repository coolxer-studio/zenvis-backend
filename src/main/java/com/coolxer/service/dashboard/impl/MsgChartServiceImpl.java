package com.coolxer.service.dashboard.impl;

import com.coolxer.dao.clickhouse.repository.MsgRepository;
import com.coolxer.model.base.dto.BaseQueryDto;
import com.coolxer.model.dashboard.vo.*;
import com.coolxer.service.dashboard.MsgChartService;
import com.coolxer.utils.DateUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 首页图表接口
 * <p>
 * 8
 */
@Service
public class MsgChartServiceImpl implements MsgChartService {

    @Autowired
    private MsgRepository msgRepository;

    @Override
    public StackedBarChartVo findProvinceCityGroup(BaseQueryDto baseQueryDto) {
        List<Map<String, Object>> countOfProvinceCity = msgRepository.countOfProvinceCity(baseQueryDto.getStartTime(), baseQueryDto.getEndTime());
        return generalStatForTwoDimensional(countOfProvinceCity);
    }

    @Override
    public StackedBarChartVo findManufactureSystemGroup(BaseQueryDto baseQueryDto) {
        List<Map<String, Object>> countOfManufactureSystem = msgRepository.countOfManufactureSystem(baseQueryDto.getStartTime(), baseQueryDto.getEndTime());
        return generalStatForTwoDimensional(countOfManufactureSystem);
    }

    private StackedBarChartVo generalStatForTwoDimensional(List<Map<String, Object>> countOfTwoDimensional) {
        Set<String> yAxisNameSet = new TreeSet<>();
        countOfTwoDimensional.forEach(countModel -> {
            String yAxisName = (String) countModel.get("y_axis_name");
            yAxisNameSet.add(yAxisName);
        });

        Map<String, ResultModel> resultModelHashMap = new HashMap<>();
        countOfTwoDimensional.forEach(countModel -> {
            String xAxis = (String) countModel.get("x_axis");
            String yAxisName = (String) countModel.get("y_axis_name");
            BigDecimal yAxisValue = (BigDecimal) countModel.get("y_axis_value");
            ResultModel resultModel = resultModelHashMap.getOrDefault(xAxis, new ResultModel());
            resultModel.setKey(xAxis);
            resultModel.getMap().put(yAxisName, yAxisValue.longValue());
            resultModelHashMap.put(xAxis, resultModel);
        });

        List<String> xAxisList = new ArrayList<>();
        resultModelHashMap.entrySet().stream().map(entry -> entry.getValue()).sorted(Comparator.comparing(ResultModel::sumValue).reversed()).toList().forEach(resultModel -> {
            xAxisList.add(resultModel.getKey());
        });

        List<List<Long>> yAxisValueList = new ArrayList<>();
        for (String system : yAxisNameSet) {
            List<Long> currentKeyValueList = new ArrayList<>();
            for (String x : xAxisList) {
                ResultModel resultModel = resultModelHashMap.get(x);
                currentKeyValueList.add(resultModel.getMap().getOrDefault(system, 0L));
            }
            yAxisValueList.add(currentKeyValueList);
        }

        return StackedBarChartVo
                .builder()
                .yAxisName(yAxisNameSet)
                .yAxisData(yAxisValueList)
                .xAxis(xAxisList)
                .build();
    }

    @Override
    public StackedLineChartVo findMsgTrend(BaseQueryDto baseQueryDto) {

        LocalDate localDateStart = baseQueryDto.getStartTime().toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
        LocalDate localDateEnd = baseQueryDto.getEndTime().toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();

        List<String> dateRange;
        SimpleDateFormat simpleDateFormat = DateUtil.SIMPLE_DATE_FORMAT_01;
        Set<String> groupKeySet = new TreeSet<>();
        Map<String, Long> groupValueMap = new HashMap<>();
        if (Math.abs(localDateEnd.toEpochDay() - localDateStart.toEpochDay()) < 1) {
            // 如果是一天之内，返回24小时分组的数据
            dateRange = DateUtil.HOUR_LIST;
            List<Map<String, Object>> riskTrendMap = msgRepository.countByHour(baseQueryDto.getStartTime(), baseQueryDto.getEndTime());
            riskTrendMap.forEach(map -> {
                String groupKey = (String) map.get("group_key");
                int time = (short) map.get("time");
                String timeString = (time > 9 ? time : "0" + time) + ":00";
                BigDecimal count = (BigDecimal) map.get("msg_count");
                groupKeySet.add(groupKey);
                groupValueMap.put(groupKey + ":" + timeString, count.longValue());
            });
        } else {
            // 小于当月的一个月时间
            dateRange = DateUtil.getDateRangeWithFormat01(baseQueryDto.getStartTime(), baseQueryDto.getEndTime());
            List<Map<String, Object>> riskTrendMap = msgRepository.countByDay(baseQueryDto.getStartTime(), baseQueryDto.getEndTime());
            riskTrendMap.forEach(map -> {
                String groupKey = (String) map.get("group_key");
                String time = simpleDateFormat.format((Date) map.get("time"));
                BigDecimal count = (BigDecimal) map.get("msg_count");
                groupKeySet.add(groupKey);
                groupValueMap.put(groupKey + ":" + time, count.longValue());
            });
        }


        List<List<Long>> yAxisList = new ArrayList<>();
        for (String groupKey : groupKeySet) {
            List<Long> currentKeyValueList = new ArrayList<>();
            for (String time : dateRange) {
                currentKeyValueList.add(groupValueMap.getOrDefault(groupKey + ":" + time, 0L));
            }
            yAxisList.add(currentKeyValueList);
        }
        return StackedLineChartVo
                .builder()
                .yAxisName(groupKeySet)
                .yAxisData(yAxisList)
                .xAxis(dateRange)
                .build();

    }

    private static Double sublimeAverage(Double averageValue) {
        if (Double.isNaN(averageValue) || averageValue < 0) {
            return 0.0;
        } else {
            double remainderFor5 = averageValue % 5;
            if (remainderFor5 > 0) {
                return averageValue - remainderFor5;
            }
        }
        return averageValue;
    }

    private static Double sublimeCurrent(Double currentValue) {
        if (Double.isNaN(currentValue) || currentValue < 0) {
            return 0.0;
        }
        return currentValue;
    }

    @Data
    public class ResultModel {
        private String key;
        private Map<String, Long> map = new HashMap<>();

        public Long sumValue() {
            AtomicLong sum = new AtomicLong(0L);
            map.forEach((key, value) -> {
                sum.addAndGet(value);
            });
            return sum.get();
        }

    }


    @Override
    public SummaryVo summary(BaseQueryDto baseQueryDto) {
        return SummaryVo.builder()
                .msgTotal(msgRepository.count())
                .startTotal(msgRepository.countByStart())
                .deviceTotal(msgRepository.countByGuid())
                .build();
    }

    @Override
    public StatusVo status() {
        // TODO 补充逻辑
        return StatusVo.builder()
                .status("正常运行")
                .serviceStatus(new StatusVo.Notice(0, "没有异常服务"))
                .msgStatus(new StatusVo.Notice(0, "没有数据积压"))
                .toDo(new Random().nextBoolean() ? new StatusVo.Notice(0, "没有代办事项") : new StatusVo.Notice(new Random().nextInt(99), "内测代办事项"))
                .build();
    }

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###");

    @Override
    public EfficiencyVo efficiency() {
        Map<String, Object> lastMinuteMetrics = msgRepository.findLastMinuteMetrics();
        Map<String, Object> lastWeekMetrics = msgRepository.findLastWeekMetrics();
        double receiveTimeAverage = (Double) lastWeekMetrics.get("receive_interval");
        double processTimeAverage = (Double) lastWeekMetrics.get("process_interval");
        BigDecimal countOfWeek = (BigDecimal) lastWeekMetrics.get("msg_count");
        // 10080分钟（一周）的平均
        BigDecimal countOfMinuteAverage = countOfWeek.divide(new BigDecimal(10080), 10, RoundingMode.HALF_UP);

        double receiveTimeCurrent = (Double) lastMinuteMetrics.get("receive_interval");
        double processTimeCurrent = (Double) lastMinuteMetrics.get("process_interval");
        BigDecimal countOfMinuteCurrent = (BigDecimal) lastMinuteMetrics.get("msg_count");

        String msgCountForMinute = DECIMAL_FORMAT.format(countOfMinuteCurrent);
        double ratioMsgCount;
        if (countOfMinuteAverage.compareTo(BigDecimal.ZERO) == 0) {
            // 如果 countOfMinuteAverage 为零，设置一个默认值
            ratioMsgCount = 0.0; // 或者其他合理的默认值
        } else {
            ratioMsgCount = countOfMinuteCurrent.divide(countOfMinuteAverage, 10, RoundingMode.HALF_UP).doubleValue();
        }
        String msgCountForMinuteAverage = null;
        if (ratioMsgCount < 0.6 || ratioMsgCount > 1.4) {
            msgCountForMinuteAverage = "(%s)".formatted(DECIMAL_FORMAT.format(countOfMinuteAverage));
        }
        String receiveDelay = DECIMAL_FORMAT.format(receiveTimeCurrent);
        double ratioReceive = receiveTimeCurrent / receiveTimeAverage;
        String receiveDelayAverage = null;
        if (Double.isNaN(receiveTimeCurrent) || ratioReceive < 0.6 || ratioReceive > 1.4) {
            receiveDelayAverage = "(%s)".formatted(DECIMAL_FORMAT.format(receiveTimeAverage));
        }
        String processDelay = DECIMAL_FORMAT.format(processTimeCurrent);
        double ratioProcess = processTimeCurrent / processTimeAverage;
        String processDelayAverage = null;
        if (Double.isNaN(processTimeCurrent) || ratioProcess < 0.6 || ratioProcess > 1.4) {
            processDelayAverage = "(%s)".formatted(DECIMAL_FORMAT.format(processTimeAverage));
        }

        int ratio = (int) Math.round(100 * (ratioMsgCount + ratioReceive + ratioProcess) / 3);
        return EfficiencyVo.builder()
                .ratio(ratio)
                .msgCountForMinute(msgCountForMinute)
                .msgCountForMinuteAverage(msgCountForMinuteAverage)
                .receiveDelay(receiveDelay)
                .receiveDelayAverage(receiveDelayAverage)
                .processDelay(processDelay)
                .processDelayAverage(processDelayAverage)
                .build();
    }

    @Override
    public RealInfoVo realInfo() {
        List<Map<String, Object>> msgStartList = msgRepository.findTopByStart(10);
        List<String> infoList = new ArrayList<>();
        for (Map<String, Object> msgStart : msgStartList) {
            String guid = (String) msgStart.get("guid");
            String platform = (String) msgStart.get("platform");
            String clientTime = DateUtil.formatDate((Timestamp) msgStart.get("client_time"));
            infoList.add("%s平台设备%s....%s:%s启动".formatted(platform, guid.substring(0, 4), guid.substring(guid.length() - 4), clientTime));
        }
        return RealInfoVo.builder()
                .infoList(infoList)
                .build();
    }

    @Override
    public SpeedVo findSpeed() {

        Map<String, Object> lastMinuteMetrics = msgRepository.findLastMinuteMetrics();
        Map<String, Object> lastWeekMetrics = msgRepository.findLastWeekMetrics();

        double receiveTimeAverage = (Double) lastWeekMetrics.get("receive_interval");
        double receiveTimeCurrent = (Double) lastMinuteMetrics.get("receive_interval");
        double processTimeAverage = (Double) lastWeekMetrics.get("process_interval");
        double processTimeCurrent = (Double) lastMinuteMetrics.get("process_interval");
        BigDecimal countOfSecondAverage = (BigDecimal) lastWeekMetrics.get("msg_count");
        BigDecimal countOfSecondCurrent = (BigDecimal) lastMinuteMetrics.get("msg_count");

        return SpeedVo.builder()
                .receiveTimeAverage(sublimeAverage(receiveTimeAverage))
                .processTimeAverage(sublimeAverage(processTimeAverage))
                .countOfSecondAverage(sublimeAverage(countOfSecondAverage.longValue() / (60.0 * 60 * 24 * 7)))
                .receiveTimeCurrent(sublimeCurrent(receiveTimeCurrent))
                .processTimeCurrent(sublimeCurrent(processTimeCurrent))
                .countOfSecondCurrent(sublimeCurrent(countOfSecondCurrent.longValue() / 60.0))
                .build();
    }
}
