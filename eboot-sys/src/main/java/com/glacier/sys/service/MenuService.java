package com.glacier.sys.service;

import com.github.pagehelper.PageInfo;
import com.glacier.core.page.PageRequest;
import com.glacier.core.service.CurdService;
import com.glacier.sys.entity.Menu;
import com.glacier.sys.entity.Role;

import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-09 15:45
 */
public interface MenuService extends CurdService<Menu> {

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageInfo<Menu> findPage(PageRequest<Menu> pageRequest);

    /**
     * 根据角色id 查询对应的菜单
     *
     * @param roleId
     * @return
     */
    List<Role> findMenusByRoleId(String roleId);
}
