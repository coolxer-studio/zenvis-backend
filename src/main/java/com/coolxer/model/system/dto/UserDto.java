package com.coolxer.model.system.dto;

import lombok.Data;

/**
 * 用户传输对象
 */
@Data
public class UserDto {
    /**
     * ID
     */
    private Integer id;

    /**
     * 登录邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色id
     */
    private Integer roleId;
}
