package com.glacier.sys.service;

import com.github.pagehelper.PageInfo;
import com.glacier.common.service.CrudService;
import com.glacier.sys.entity.Role;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 角色业务层
 * @date 2019-08-11 21:20
 */
public interface RoleService extends CrudService<Role, String> {

    /**
     * 分页查询角色
     *
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Role> findPage(Role role, int pageNum, int pageSize);

    /**
     * 根据用户id 查询对应的角色
     *
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(String userId);
}
