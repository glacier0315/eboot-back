package com.glacier.sys.controller;

import com.glacier.core.http.HttpResult;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.entity.Role;
import com.glacier.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 角色管理
 * @date 2019-08-11 21:24
 */
@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查找所有角色
     *
     * @return
     */
    @GetMapping("findAll")
    public HttpResult findAll() {
        return HttpResult.ok(roleService.findAllList());
    }

    /**
     * 分页查询角色
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("page")
    public HttpResult page(@RequestBody PageRequest<Role> pageRequest) {
        return HttpResult.ok(roleService.findPage(pageRequest));
    }

    /**
     * 保存角色
     *
     * @param user
     * @return
     */
    @PostMapping("save")
    public HttpResult save(@RequestBody Role user) {
        return HttpResult.ok(roleService.save(user));
    }

    /**
     * 删除指定角色
     *
     * @param roles
     * @return
     */
    @DeleteMapping("delete")
    public HttpResult delete(@RequestBody List<Role> roles) {
        return HttpResult.ok(roleService.batchDelete(roles));
    }
}
