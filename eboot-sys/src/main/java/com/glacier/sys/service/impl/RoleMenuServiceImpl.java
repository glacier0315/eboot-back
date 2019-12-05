package com.glacier.sys.service.impl;

import com.glacier.common.utils.IdGen;
import com.glacier.sys.dao.RoleMenuDao;
import com.glacier.sys.entity.RoleMenu;
import com.glacier.sys.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-05 20:57
 */
@Slf4j
@Transactional(readOnly = false)
@Service("RoleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {
    @Resource
    private RoleMenuDao roleMenuDao;
    @Override
    public int deleteByMenuId(String menuId) {
        return roleMenuDao.deleteByMenuId(menuId);
    }

    @Override
    public int deleteByRoleId(String roleId) {
        return roleMenuDao.deleteByRoleId(roleId);
    }

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
