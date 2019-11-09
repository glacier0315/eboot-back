package com.glacier.sys.dao;

import com.glacier.core.dao.CurdDao;
import com.glacier.sys.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

/**
 * @author glacier
 * @version 1.0
 * @description 角色菜单关联dao层
 * @date 2019-10-09 14:51
 */
public interface RoleMenuDao extends CurdDao<RoleMenu> {

    /**
     * 根据角色删除 角色菜单关系
     *
     * @param roleId
     * @return
     */
    int deleteByRole(@Param("roleId") String roleId);
}
