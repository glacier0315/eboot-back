package com.glacier.sys.controller;

import com.glacier.sys.entity.Menu;
import com.glacier.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description 菜单控制层
 * @date 2019-10-09 15:59
 */
@RestController
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 根据用户id 查询菜单
     *
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    public Menu get(@PathVariable("id") String id) {
        return menuService.get(id);
    }

    /**
     * 查询所有菜单
     *
     * @param menu
     * @return
     */
    @GetMapping("list")
    public List<Menu> list(Menu menu) {
        return menuService.findList(menu);
    }


    /**
     * 保存菜单
     *
     * @param menu
     * @return
     */
    @PutMapping("save")
    public int save(Menu menu) {
        return menuService.save(menu);
    }

    /**
     * 删除指定菜单
     *
     * @param id
     * @return
     */
    @DeleteMapping("del/{id}")
    public int delete(@PathVariable("id") String id) {
        Menu menu = new Menu();
        menu.setId(id);
        return menuService.delete(menu);
    }
}
