package com.glacier.sys.service;

import com.glacier.sys.entity.Role;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-05 16:35
 */
public interface UserRoleService {

    /**
     * 根据用户id 删除
     *
     * @param userId
     * @return
     */
    int deleteByUserId(String userId);


    /**
     * 根据角色id 删除
     *
     * @param roleId
     * @return
     */
    int deleteByRoleId(String roleId);

    /**
     * 保存用户角色
     * @param userId
     * @param roleList
     * @return
     */
    int insertUserRole(String userId, List<Role> roleList);
}
