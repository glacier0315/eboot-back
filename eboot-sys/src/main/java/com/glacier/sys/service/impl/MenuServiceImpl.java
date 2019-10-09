package com.glacier.sys.service.impl;

import com.glacier.sys.dao.MenuDao;
import com.glacier.sys.entity.Menu;
import com.glacier.sys.entity.Role;
import com.glacier.sys.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description 菜单业务层
 * @date 2019-10-09 15:45
 */
@Slf4j
@Transactional(readOnly = true)
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    @Override
    public List<Role> findMenusByRoleId(String roleId) {
        return menuDao.findMenusByRoleId(roleId);
    }

    @Override
    public Menu get(String id) {
        return menuDao.get(id);
    }

    @Override
    public List<Menu> findList(Menu menu) {
        return menuDao.findList(menu);
    }

    @Override
    public int save(Menu menu) {
        if (menu.isNewRecord()) {
            return menuDao.insert(menu);
        } else {
            return menuDao.update(menu);
        }
    }

    @Override
    public int delete(Menu menu) {
        return 0;
    }
}
