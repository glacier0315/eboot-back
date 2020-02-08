package com.glacier.sys.service.impl;

import com.glacier.common.core.utils.IdGen;
import com.glacier.sys.dao.RoleMenuDao;
import com.glacier.sys.entity.RoleMenu;
import com.glacier.sys.service.RoleMenuService;
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
 * @date 2019-12-05 20:57
 */
@Slf4j
@Transactional(readOnly = true)
@Service("RoleMenuService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleMenuServiceImpl implements RoleMenuService {
    private final RoleMenuDao roleMenuDao;

    @Transactional(rollbackFor = {})
    @Override
    public int deleteByMenuId(String menuId) {
        return roleMenuDao.deleteByMenuId(menuId);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int deleteByRoleId(String roleId) {
        return roleMenuDao.deleteByRoleId(roleId);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int insert(String roleId, List<String> menuList) {
        // 删除原数据
        int success = this.deleteByRoleId(roleId);
        // 重新保存
        if (menuList != null && !menuList.isEmpty()) {
            List<RoleMenu> roleMenus = new ArrayList<>(10);
            RoleMenu roleMenu = null;
            for (String menuId : menuList) {
                if (menuId == null || menuId.trim().length() == 0) {
                    continue;
                }
                roleMenu = new RoleMenu();
                // 生成id
                roleMenu.setId(IdGen.uuid());
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);

                roleMenus.add(roleMenu);
            }
            //
            success = roleMenuDao.insertBatch(roleMenus);
        }
        return success;
    }
}
