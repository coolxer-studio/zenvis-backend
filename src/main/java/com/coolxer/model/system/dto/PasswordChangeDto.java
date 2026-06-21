package com.coolxer.model.system.dto;

import lombok.Data;

/**
 * 密码修改传输对象
 */
@Data
public class PasswordChangeDto {

    /**
     * 密码
     */
    private String password;
    /**
     * 旧密码
     */
    private String oldPassword;

}
