package com.glacier.sys.service;

import com.glacier.common.service.CrudService;
import com.glacier.sys.entity.Menu;
import com.glacier.sys.entity.Role;

import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-09 15:45
 */
public interface MenuService extends CrudService<Menu, String> {

    /**
     * 根据角色id 查询对应的菜单
     *
     * @param roleId
     * @return
     */
    List<Role> findMenusByRoleId(String roleId);
}
