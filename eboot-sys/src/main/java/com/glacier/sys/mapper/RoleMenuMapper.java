package com.glacier.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glacier.sys.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 角色菜单关联dao层
 * @date 2019-10-09 14:51
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 插入操作
     *
     * @param list
     * @return
     */
    int insertBatch(List<RoleMenu> list);


    /**
     * 根据菜单id 删除
     *
     * @param menuId
     * @return
     */
    int deleteByMenuId(@Param("menuId") String menuId);


    /**
     * 根据角色id 删除
     *
     * @param roleId
     * @return
     */
    int deleteByRoleId(@Param("roleId") String roleId);
}
