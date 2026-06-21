package com.coolxer.aop;

import com.coolxer.commons.exception.ApiException;
import com.coolxer.model.base.vo.ResponseWrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 统一异常处理
 */
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * 统一参数异常处理
     *
     * @return 请求结果
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseWrap<String> processMethod(MissingServletRequestParameterException ex) {
        log.error("", ex);
        return ResponseWrap.fail();
    }

    /**
     * 统一异常处理
     *
     * @return 结果
     */
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrap<String> processMethod2(Throwable ex) {
        log.error("", ex);
        return ResponseWrap.fail();
    }

    /**
     * 自定义异常处理
     *
     * @param apiException 自定义异常
     * @return 请求结果
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = ApiException.class)
    public ResponseWrap<String> customApiExceptionHandler(ApiException apiException) {
        return ResponseWrap.fail(apiException);
    }


}
