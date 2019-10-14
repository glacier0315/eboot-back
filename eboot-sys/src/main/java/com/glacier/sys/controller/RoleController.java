package com.glacier.sys.controller;

import com.github.pagehelper.PageInfo;
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
     * 查找指定角色
     *
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    public Role get(@PathVariable("id") String id) {
        return roleService.findById(id);
    }

    /**
     * 查找所有角色
     *
     * @param role
     * @return
     */
    @GetMapping("list")
    public List<Role> list(Role role) {
        return roleService.findList(role);
    }

    /**
     * 分页查询角色
     *
     * @param role
     * @return
     */
    @GetMapping("page")
    public PageInfo<Role> page(Role role, @RequestParam int pageNum,
                               @RequestParam int pageSize) {
        return roleService.findPage(new PageRequest<>(pageNum, pageSize, role));
    }

    /**
     * 根据用户id 查找对应的角色
     *
     * @param userId
     * @return
     */
    @GetMapping("findRolesByUserId/{userId}")
    public List<Role> findRolesByUserId(@PathVariable("userId") String userId) {
        return roleService.findRolesByUserId(userId);
    }

    /**
     * 保存角色
     *
     * @param user
     * @return
     */
    @PutMapping("save")
    public int save(Role user) {
        return roleService.save(user);
    }

    /**
     * 删除指定角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("del/{id}")
    public int delete(@PathVariable("id") String id) {
        Role role = new Role();
        role.setId(id);
        return roleService.delete(role);
    }
}
