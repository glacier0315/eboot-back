package com.glacier.sys.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author hebin
 * @version 1.0
 * @description 角色菜单关联表
 * @date 2019-10-09 14:49
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -234428421997899712L;
    /**
     * id
     */
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;
}
