package com.glacier.sys.dao;

import com.glacier.common.dao.CrudDao;
import com.glacier.sys.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description  角色dao层
 * @date 2019-08-04 21:53
 */
public interface RoleDao extends CrudDao<Role, String> {
    /**
     * 根绝用户id 查询用户所拥有的角色
     * @return
     */
    List<Role> findListByUser(@Param("userId") String userId);
}