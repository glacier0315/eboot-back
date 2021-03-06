package com.glacier.sys.controller;

import com.glacier.common.core.http.HttpResult;
import com.glacier.security.util.SecurityUtils;
import com.glacier.sys.entity.Menu;
import com.glacier.sys.entity.dto.IdDto;
import com.glacier.sys.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author glacier
 * @version 1.0
 * @description 菜单控制层
 * @date 2019-10-09 15:59
 */
@Slf4j
@RestController
@RequestMapping(value = "menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuController {

    private final MenuService menuService;

    /**
     * 保存菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("save")
    public HttpResult<Integer> save(@RequestBody Menu menu) {
        return HttpResult.ok(menuService.save(menu));
    }

    /**
     * 删除指定菜单
     *
     * @param idDtos
     * @return
     */
    @PostMapping("delete")
    public HttpResult<Integer> delete(@RequestBody List<IdDto> idDtos) {
        return HttpResult.ok(menuService.batchDelete(idDtos));
    }

    /**
     * 查询所有菜单 树
     *
     * @return
     */
    @GetMapping("findMenuTree")
    public HttpResult<List<Menu>> findMenuTree() {
        List<Menu> tree = menuService.findMenuTree();
        return HttpResult.ok(tree);
    }

    /**
     * 查询所有菜单 树
     *
     * @return
     */
    @GetMapping("findNavTree")
    public HttpResult<List<Menu>> findNavTree() {
        String userId = SecurityUtils.geUserId();
        log.debug("userId: {}", userId);
        List<Menu> tree = menuService.findTree(userId);
        return HttpResult.ok(tree);
    }

    /**
     * 查询所有权限标识
     *
     * @return
     */
    @GetMapping(value = "/findPermissions")
    public HttpResult<Set<String>> findPermissions() {
        String userId = SecurityUtils.geUserId();
        log.debug("userId: {}", userId);
        return HttpResult.ok(menuService.findPermissions(userId));
    }

    /**
     * 查询所有菜单 树
     *
     * @return
     */
    @GetMapping("findRoleMenus")
    public HttpResult<List<Menu>> findRoleMenus(String roleId) {
        List<Menu> tree = menuService.findMenusByRoleId(roleId);
        return HttpResult.ok(tree);
    }
}
