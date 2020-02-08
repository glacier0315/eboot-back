package com.glacier.sys.service.impl;

import com.glacier.common.core.utils.IdGen;
import com.glacier.sys.dao.UserRoleDao;
import com.glacier.sys.entity.UserRole;
import com.glacier.sys.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-05 16:37
 */
@Slf4j
@Transactional(readOnly = true)
@Service("UserRoleService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleDao userRoleDao;

    @Override
    public List<String> findByUserId(String userId) {
        return userRoleDao.findByUserId(userId);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int deleteByUserId(String userId) {
        return userRoleDao.deleteByUserId(userId);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int deleteByRoleId(String roleId) {
        return userRoleDao.deleteByRoleId(roleId);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int insert(String userId, List<String> roleList) {
        // 删除原数据
        int success = this.deleteByUserId(userId);
        // 重新保存
        if (roleList != null && !roleList.isEmpty()) {
            List<UserRole> userRoles = new ArrayList<>(10);
            UserRole userRole = null;
            for (String roleId : roleList) {
                if (roleId == null || roleId.trim().length() == 0) {
                    continue;
                }
                userRole = new UserRole();
                // 生成id
                userRole.setId(IdGen.uuid());
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);

                userRoles.add(userRole);
            }
            //
            success = userRoleDao.insertBatch(userRoles);
        }
        return success;
    }
}
