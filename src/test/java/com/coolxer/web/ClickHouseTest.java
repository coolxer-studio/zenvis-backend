package com.coolxer.web;

import com.coolxer.dao.clickhouse.entity.Msg;
import com.coolxer.dao.clickhouse.repository.MsgRepository;
import com.coolxer.model.base.vo.PageView;
import com.coolxer.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * desc
 */
@SpringBootTest
@Slf4j
public class ClickHouseTest {
    @Autowired
    private MsgRepository msgRepository;

    @Test
    public void riskGroupTest() {
        Date startTime = DateUtils.addDays(new Date(), -30);
        Date endTime = new Date();
        List<Map<String, Object>> riskGroups = msgRepository.countOfManufactureSystem(startTime, endTime);
        if (CollectionUtils.isNotEmpty(riskGroups)) {
            System.out.println(JacksonUtil.toJson(riskGroups));
        }
    }


    @Test
    public void test4() {
        Date yestaday = DateUtils.addDays(new Date(), -1);
        Date d1 = DateUtils.addHours(yestaday, -5);
        Date d2 = DateUtils.addHours(yestaday, 12);
        log.info(" {},{}", d1, d2);
        List<Map<String, Object>> maps = msgRepository.countByDay(d1, d2);


        log.info(" {}", JacksonUtil.toJson(maps));
    }


    @Test
    public void findBypage() {

        int page = 1;
        int size = 10;

        String id = "002a7dac-f316-4b5f-bd52-dbf5f974eb04";
        Page<Msg> msgPage = msgRepository.findByPage(PageRequest.of(page - 1, size), id);

        PageView pageView = new PageView();
        pageView.setItems(msgPage.toList());
        pageView.setTotal(msgPage.getTotalElements());
        pageView.setSize(size);
        pageView.setPage(page);
        System.out.println(JacksonUtil.toJson(pageView));


    }

    @Test
    public void findBypageType() {

        int page = 3;
        int size = 10;

        String type = "system.info";
        String type2 = "detection.result";
        Page<Msg> msgPage = msgRepository.findByPageByType(PageRequest.of(page - 1, size),
                type2);

        PageView pageView = new PageView();
        pageView.setItems(msgPage.toList());
        pageView.setTotal(msgPage.getTotalElements());
        pageView.setSize(size);
        pageView.setPage(page);
        System.out.println(JacksonUtil.toJson(pageView));


    }


    private static String buildDeviceId() {
        String deviceId;
        double r = Math.random();
        if (r < 0.3) {
            deviceId = "00000000-0000-0000-0000-bdd7a177088e";
        } else if (r > 0.3 && r < 0.7) {
            deviceId = "00000000-0000-0000-0000-bdd7a177088f";
        } else {
            deviceId = "00000000-0000-0000-0000-bdd7a177088g";
        }
        return deviceId;
    }

    private static String buildLevel(int score) {
        String level;
        if (score < 100 / 3) {
            level = "high";
        } else if (score > 100 / 3 && score < 100 * 2 / 3) {
            level = "medium";
        } else if (score < 100) {
            level = "low";
        } else {
            level = "safe";
        }
        return level;
    }


}
