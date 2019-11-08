package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.utils.IdGen;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.dao.RoleDao;
import com.glacier.sys.entity.Role;
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
    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

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

    @Override
    public List<Role> findList(Role entity) {
        return roleDao.findList(entity);
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

    @Transactional(rollbackFor = {})
    @Override
    public int delete(Role entity) {
        return roleDao.delete(entity);
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
}
