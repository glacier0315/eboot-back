package com.glacier.sys.service.impl;

import com.glacier.common.utils.IdGen;
import com.glacier.sys.dao.UserRoleDao;
import com.glacier.sys.entity.Role;
import com.glacier.sys.entity.UserRole;
import com.glacier.sys.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-05 16:37
 */
@Service("UserRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;
    @Override
    public int deleteByUserId(String userId) {
        return userRoleDao.deleteByUserId(userId);
    }

    @Override
    public int deleteByRoleId(String roleId) {
        return userRoleDao.deleteByRoleId(roleId);
    }

    @Override
    public int insertUserRole(String userId, List<Role> roleList) {
        // 删除原 用户与角色关联
        int success = userRoleDao.deleteByUserId(userId);
        // 重新保存
        if (roleList != null && !roleList.isEmpty()) {
            List<UserRole> userRoles = new ArrayList<>(10);
            UserRole userRole = null;
            Calendar calendar = Calendar.getInstance();
            for (Role role : roleList) {
                userRole = new UserRole();
                // 生成id
                userRole.setId(IdGen.uuid());
                userRole.setUserId(userId);
                userRole.setRoleId(role.getId());
                userRole.setCreateDate(calendar.getTime());

                userRoles.add(userRole);
            }
            //
            success = userRoleDao.insertBatch(userRoles);
        }
        return success;
    }
}
