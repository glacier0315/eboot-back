package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.utils.IdGen;
import com.glacier.core.page.PageRequest;
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

    /**
     * @param pageRequest
     * @return
     */
    @Override
    public PageInfo<Menu> findPage(PageRequest<Menu> pageRequest) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Menu> list = menuDao.findList(pageRequest.getData());
        PageInfo<Menu> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Role> findMenusByRoleId(String roleId) {
        return menuDao.findMenusByRoleId(roleId);
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
}
