package com.coolxer.model.system.dto;

import lombok.Data;

/**
 * 角色
 */
@Data
public class RoleDto {

    /**
     * ID
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;

    /**
     * 菜单权限列表(x,x,x形式)
     */
    private String menuIds;


}
