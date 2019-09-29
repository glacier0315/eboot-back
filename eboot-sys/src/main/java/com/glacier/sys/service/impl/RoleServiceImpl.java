package com.glacier.sys.service.impl;

import com.glacier.sys.dao.RoleDao;
import com.glacier.sys.entity.Role;
import com.glacier.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 角色业务类
 * @date 2019-08-11 21:21
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Transactional(readOnly = true)
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public Role get(String id) {
        return roleDao.get(id);
    }

    @Override
    public int save(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public List<Role> findList(Role entity) {
        return roleDao.findList(entity);
    }

    @Override
    public int delete(Role entity) {
        return roleDao.delete(entity);
    }
}
