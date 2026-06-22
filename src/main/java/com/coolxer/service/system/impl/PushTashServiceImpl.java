package com.coolxer.service.system.impl;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.commons.exception.ApiException;
import com.coolxer.configuration.CustomWebConfig;
import com.coolxer.configuration.JacksonConfig;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.system.dto.PushTaskDto;
import com.coolxer.model.system.vo.PushTaskVo;
import com.coolxer.service.system.PushTaskService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 *
 */
@Slf4j
@Service
public class PushTashServiceImpl implements PushTaskService {


    @Autowired
    private CustomWebConfig customWebConfig;

    @Autowired
    private RestTemplate restTemplate;

    public Object proxy(HttpServletRequest request) {
        try {
            String queryString = request.getQueryString();
            String targetUrl = customWebConfig.getDataServiceUrl() + request.getRequestURI().replace("/api/v1/system/push-task", "/vectum/api/v1/task");
            if (queryString != null && !queryString.isEmpty()) {
                targetUrl += "?" + queryString;
            }

            HttpMethod httpMethod = HttpMethod.valueOf(request.getMethod());

            HttpHeaders headers = new HttpHeaders();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headers.set(headerName, request.getHeader(headerName));
            }

            HttpEntity<byte[]> entity = new HttpEntity<>(request.getInputStream().readAllBytes(), headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    targetUrl,
                    httpMethod,
                    entity,
                    String.class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }

        } catch (Exception e) {
            log.error("代理转发失败: {}", e.getMessage(), e);
            throw new ApiException(ResultCodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public boolean createAndStart(PushTaskDto pushTaskDto) {
        ResponseModel responseForCreate = restTemplate.postForObject(customWebConfig.getDataServiceUrl() + "/vectum/api/v1/task/add", pushTaskDto, ResponseModel.class);
        if (responseForCreate.succeed()) {
            PushTaskVo createdTask = JacksonConfig.OBJECT_MAPPER.convertValue(responseForCreate.getData(), PushTaskVo.class);
            if (Objects.nonNull(createdTask.getId())) {
                ResponseModel responseForStart = restTemplate.postForObject(customWebConfig.getDataServiceUrl() + "/vectum/api/v1/task/" + createdTask.getId() + "/toggle", null, ResponseModel.class);
                if (responseForStart.succeed()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<PushTaskVo> findBySourceMark(String sourceMark) {
        ResponseModel response = restTemplate.getForObject(customWebConfig.getDataServiceUrl() + "/vectum/api/v1/task/all", ResponseModel.class);
        if (response.succeed()) {
            List<PushTaskVo> pushTaskList = JacksonConfig.OBJECT_MAPPER.convertValue(response.getData(),
                    JacksonConfig.OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, PushTaskVo.class));
            return pushTaskList.stream().filter(pushTaskVo -> pushTaskVo.getSource().equals("SYSTEM") && pushTaskVo.getMark() != null && pushTaskVo.getMark().equals(sourceMark)).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean deleteBySourceMark(String sourceMark) {
        List<PushTaskVo> pushTaskList = findBySourceMark(sourceMark);
        pushTaskList.forEach(pushTaskVo -> {
            String url = customWebConfig.getDataServiceUrl() + "/vectum/api/v1/task/{id}";
            ResponseEntity<ResponseModel> response = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    null,
                    ResponseModel.class,
                    pushTaskVo.getId()
            );
            if (!response.getBody().succeed()) {
                // TODO 删除失败处理
            }
        });
        return true;
    }

    @Override
    public String detectFormat(String content) {
        if (content == null || content.isEmpty()) {
            return "yaml";
        }

        String trimmed = content.trim();

        if (trimmed.startsWith("{")) {
            return "json";
        }

        if (trimmed.startsWith("---")) {
            return "yaml";
        }

        int colonCount = countOccurrences(trimmed, ':');
        int equalsCount = countOccurrences(trimmed, '=');
        int bracketCount = countOccurrences(trimmed, '[');

        if (equalsCount > colonCount) {
            return "toml";
        }

        if (colonCount > equalsCount && colonCount > bracketCount) {
            return "yaml";
        }

        return "yaml";
    }

    private int countOccurrences(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    @Data
    static class RequestPushTask {

        private String path;
        private String config;
        private ArrayList<LuaFile> luaFiles;

        @Data
        @AllArgsConstructor
        static public class LuaFile {

            private String fileName;
            private String context;

        }
    }

    @Data
    static class ResponseModel {

        private static final Integer CODE_SUCCEED = 0;
        private static final Integer CODE_FAILED = 1;

        /**
         * 响应结果代码
         */
        private Integer status;

        /**
         * 提示消息(msg 是 message 的缩写，使用缩写是为了兼容原来的代码)
         */
        private String msg;

        /**
         * 数据
         */
        private Object data;

        public ResponseModel() {
        }

        public ResponseModel(Integer status, String msg, Object data) {
            this.status = status;
            this.msg = msg;
            this.data = data;
        }

        public boolean succeed() {
            return (CODE_SUCCEED.equals(this.status));
        }

        public boolean failed() {
            return !succeed();
        }

    }

}
