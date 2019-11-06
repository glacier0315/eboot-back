package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.constant.Constant;
import com.glacier.common.utils.IdGen;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.dao.MenuDao;
import com.glacier.sys.entity.Menu;
import com.glacier.sys.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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

    /**
     * @param pageRequest
     * @return
     */
    @Override
    public PageInfo<Menu> findPage(PageRequest<Menu> pageRequest) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Menu> list = menuDao.findList(pageRequest.getData());
        return new PageInfo<>(list);
    }

    @Override
    public List<Menu> findMenusByRoleId(String roleId) {
        return menuDao.findMenusByRoleId(roleId);
    }

    @Override
    public List<Menu> findTree(String username) {
        List<Menu> menus = this.findMenusByUsername(username);
        List<Menu> menuList = new ArrayList<>(10);
        //
        if (menus != null && !menus.isEmpty()) {
            Iterator<Menu> iterator = menus.iterator();
            while (iterator.hasNext()) {
                Menu menu1 = iterator.next();
                if (menu1.getParentId() == null || "0".equals(menu1.getParentId())) {
                    menuList.add(menu1);
                    // 删除
                    iterator.remove();
                }
            }
        }
        // 排序
        menuList.sort((o1, o2) -> o1.getOrderNum() - o2.getOrderNum());
        // 组装子类菜单
        findChildren(menuList, menus);
        return menuList;
    }

    @Override
    public Set<String> findPermissions(String username) {
        Set<String> permissions = new HashSet<>(10);
        if (username == null) {
            return permissions;
        }
        if (Constant.ADMIN.equals(username)) {
            permissions = menuDao.findAllPermissions();
        } else {
            permissions = menuDao.findPermissions(username);
        }
        if (permissions == null) {
            permissions = new HashSet<>(1);
        }
        return permissions;
    }

    @Override
    public Menu findById(String id) {
        return menuDao.findById(id);
    }

    @Override
    public List<Menu> findList(Menu menu) {
        return menuDao.findList(menu);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int save(Menu menu) {
        if (menu.isNewRecord()) {
            menu.setId(IdGen.uuid());
            return menuDao.insert(menu);
        } else {
            return menuDao.update(menu);
        }
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(Menu menu) {
        return menuDao.delete(menu);
    }

    /**
     * 递归组装菜单
     *
     * @param menuList 当前父级菜单
     * @param menus    待查询菜单
     */
    private void findChildren(List<Menu> menuList, List<Menu> menus) {
        // 为空则返回
        if (menuList == null || menuList.isEmpty() || menus == null || menus.isEmpty()) {
            return;
        }
        for (Menu parent : menuList) {
            // 非目录 则跳过
            if (parent.getType() != 1) {
                continue;
            }
            List<Menu> children = new ArrayList<>(10);
            Iterator<Menu> iterator = menus.iterator();
            while (iterator.hasNext()) {
                Menu menu = iterator.next();
                if (menu.getType() == 3) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    iterator.remove();
                    continue;
                }
                if (parent.getId() != null && parent.getId().equals(menu.getParentId())) {
                    menu.setParentName(parent.getName());
                    menu.setLevel(parent.getLevel() + 1);
                    children.add(menu);
                    iterator.remove();
                }
            }
            parent.setChildren(children);
            children.sort((o1, o2) -> o1.getOrderNum() - o2.getOrderNum());
            findChildren(children, menus);
        }
    }

    /**
     * 根据用户名查找所有可见菜单
     *
     * @param username
     * @return
     */
    private List<Menu> findMenusByUsername(String username) {
        List<Menu> menuList = new ArrayList<>(10);
        if (username == null) {
            return menuList;
        }
        if (Constant.ADMIN.equals(username)) {
            Menu condition = new Menu();
            menuList = menuDao.findList(condition);
        } else {
            menuList = menuDao.findMenusByUsername(username);
        }
        return menuList;
    }
}
