package com.coolxer.controller.system;

import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.service.system.PushTaskService;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 推送任务
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/v1/system/push-task")
public class PushTaskController {

    @Autowired
    private PushTaskService pushTaskService;

    @RequestMapping("/**")
    public ResponseWrap<?> proxy(HttpServletRequest request) {
        return pushTaskService.proxy(request);
    }

}
