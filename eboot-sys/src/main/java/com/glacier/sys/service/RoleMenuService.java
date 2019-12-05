package com.glacier.sys.service;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-05 20:54
 */
public interface RoleMenuService {
    /**
     * 根据菜单id 删除
     *
     * @param menuId
     * @return
     */
    int deleteByMenuId(String menuId);


    /**
     * 根据角色id 删除
     *
     * @param roleId
     * @return
     */
    int deleteByRoleId(String roleId);

    /**
     * 保存
     * @param roleId
     * @param menuList
     * @return
     */
    int insert(String roleId, List<String> menuList);
}
