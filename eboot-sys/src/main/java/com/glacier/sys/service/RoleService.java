package com.glacier.sys.service;

import com.github.pagehelper.PageInfo;
import com.glacier.core.page.PageRequest;
import com.glacier.core.service.CurdService;
import com.glacier.sys.entity.Role;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 角色业务层
 * @date 2019-08-11 21:20
 */
public interface RoleService extends CurdService<Role> {

    /**
     * 分页查询
     * @param pageRequest
     * @return
     */
    PageInfo<Role> findPage(PageRequest<Role> pageRequest);

    /**
     * 根据用户id 查询对应的角色
     *
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(String userId);

    /**
     * 查询所有
     *
     * @return
     */
    List<Role> findAllList();


    /**
     * 保存用户角色
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    int saveRoleMenus(String roleId, List<String> menuIds);
}
