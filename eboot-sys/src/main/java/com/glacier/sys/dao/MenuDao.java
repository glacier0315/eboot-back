package com.glacier.sys.dao;

import com.glacier.core.dao.CurdDao;
import com.glacier.sys.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

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
    List<Menu> findMenusByRoleId(@Param("roleId") String roleId);

    /**
     * 查询所有
     *
     * @return
     */
    List<Menu> findAllList();

    /**
     * 根据用户名 查询所有菜单
     *
     * @param userId
     * @return
     */
    List<Menu> findMenusByUserId(@Param("userId") String userId);

    /**
     * 根据用户名 查询所有按钮权限
     *
     * @param userId
     * @return
     */
    Set<String> findPermissionsByUserId(@Param("userId") String userId);

    /**
     * 根据查询所有按钮权限
     *
     * @return
     */
    Set<String> findAllPermissions();
}
