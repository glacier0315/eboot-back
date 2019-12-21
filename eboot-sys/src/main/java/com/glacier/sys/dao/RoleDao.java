package com.glacier.sys.dao;

import com.glacier.common.core.dao.CurdDao;
import com.glacier.sys.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description  角色dao层
 * @date 2019-08-04 21:53
 */
public interface RoleDao extends CurdDao<Role> {
    /**
     * 根绝用户id 查询用户所拥有的角色
     * @return
     */
    List<Role> findByUserId(@Param("userId") String userId);
}