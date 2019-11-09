package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.utils.IdGen;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.dao.RoleDao;
import com.glacier.sys.dao.RoleMenuDao;
import com.glacier.sys.entity.Role;
import com.glacier.sys.entity.RoleMenu;
import com.glacier.sys.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 角色业务类
 * @date 2019-08-11 21:21
 */
@Slf4j
@Transactional(readOnly = true)
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleMenuDao roleMenuDao;

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    /**
     * 保存
     *
     * @param role
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Role role) {
        if (role.newRecord()) {
            if (!role.isNewRecord()) {
                role.setId(IdGen.uuid());
            }
            return roleDao.insert(role);
        } else {
            return roleDao.update(role);
        }
    }

    /**
     * 删除
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(Role entity) {
        return roleDao.delete(entity);
    }

    /**
     * 批量删除
     *
     * @param roles
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<Role> roles) {
        int delCount = 0;
        if (roles != null && !roles.isEmpty()) {
            for (Role role : roles) {
                delCount += roleDao.delete(role);
            }
        }
        return delCount;
    }

    /**
     * 查找
     *
     * @param entity
     * @return
     */
    @Override
    public List<Role> findList(Role entity) {
        return roleDao.findList(entity);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Role> findAllList() {
        Role role = new Role();
        return roleDao.findList(role);
    }

    /**
     * 保存角色菜单关系
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int saveRoleMenus(String roleId, List<String> menuIds) {
        int insert = 0;
        if (roleId != null && roleId.trim().length() > 0 && menuIds != null && !menuIds.isEmpty()) {
            // 删除原角色和菜单关系
            roleMenuDao.deleteByRole(roleId);
            // 保存新的菜单角色
            RoleMenu roleMenu = null;
            for (String menuId : menuIds) {
                roleMenu = new RoleMenu();
                roleMenu.setNewRecord(true);
                roleMenu.setId(IdGen.uuid());
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuDao.insert(roleMenu);
                insert++;
            }
        }
        return insert;
    }

    /**
     * 这个方法中用到了开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageInfo<Role> findPage(PageRequest<Role> pageRequest) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Role> list = roleDao.findList(pageRequest.getParams());
        return new PageInfo<>(list);
    }

    /**
     * 查询用户及用户拥有的角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> findRolesByUserId(String userId) {
        return roleDao.findRolesByUserId(userId);
    }
}
