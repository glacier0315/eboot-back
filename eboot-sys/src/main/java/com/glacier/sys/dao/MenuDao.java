package com.glacier.sys.dao;

import com.glacier.core.dao.CurdDao;
import com.glacier.sys.entity.Menu;
import com.glacier.sys.entity.Role;

import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description 菜单dao层
 * @date 2019-10-09 14:50
 */
public interface MenuDao extends CurdDao<Menu> {

    /**
     * 根据角色id 查询所有菜单
     *
     * @param roleId
     * @return
     */
    List<Role> findMenusByRoleId(String roleId);
}
