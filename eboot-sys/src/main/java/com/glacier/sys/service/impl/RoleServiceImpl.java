package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public Role get(String id) {
        return roleDao.get(id);
    }

    @Override
    public int save(Role role) {
        if (role.isNewRecord()) {
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
     * @param role
     * @param pageNum  开始页数
     * @param pageSize 每页显示的数据条数
     * @return
     */
    @Override
    public PageInfo<Role> findPage(Role role, int pageNum, int pageSize) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleDao.findList(role);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return pageInfo;
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

    @Override
    public int delete(Role entity) {
        return roleDao.delete(entity);
    }
}
