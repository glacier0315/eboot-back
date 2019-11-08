package com.glacier.sys.controller;

import com.glacier.core.http.HttpResult;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.entity.Role;
import com.glacier.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 查找指定角色
     *
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    public HttpResult get(@PathVariable("id") String id) {
        return HttpResult.ok(roleService.findById(id));
    }

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
    @GetMapping("page")
    public HttpResult page(@RequestBody PageRequest<Role> pageRequest) {
        return HttpResult.ok(roleService.findPage(pageRequest));
    }

    /**
     * 根据用户id 查找对应的角色
     *
     * @param userId
     * @return
     */
    @GetMapping("findByUser/{userId}")
    public HttpResult findByUser(@PathVariable("userId") String userId) {
        return HttpResult.ok(roleService.findRolesByUserId(userId));
    }

    /**
     * 保存角色
     *
     * @param user
     * @return
     */
    @PostMapping("save")
    public HttpResult save(Role user) {
        return HttpResult.ok(roleService.save(user));
    }

    /**
     * 删除指定角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("del/{id}")
    public HttpResult delete(@PathVariable("id") String id) {
        Role role = new Role();
        role.setId(id);
        return HttpResult.ok(roleService.delete(role));
    }
}
