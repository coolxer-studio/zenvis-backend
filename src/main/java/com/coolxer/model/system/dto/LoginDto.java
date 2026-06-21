package com.coolxer.model.system.dto;

import lombok.Data;

/**
 * 登录入参
 */
@Data
public class LoginDto {

    /**
     * 登录邮箱
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String authCode;

}
