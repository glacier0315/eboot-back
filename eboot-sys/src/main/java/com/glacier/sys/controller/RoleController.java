package com.glacier.sys.controller;

import com.glacier.common.constant.Constant;
import com.glacier.core.http.HttpResult;
import com.glacier.core.page.PageRequest;
import com.glacier.core.vo.OneToManyVo;
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
    @PostMapping("findPage")
    public HttpResult findPage(@RequestBody PageRequest<Role> pageRequest) {
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

    /**
     * 保存角色菜单
     *
     * @param oneToManyVo
     * @return
     */
    @PostMapping("saveRoleMenus")
    public HttpResult saveRoleMenus(@RequestBody OneToManyVo oneToManyVo) {
        if (oneToManyVo != null && oneToManyVo.getParentId() != null
                && oneToManyVo.getParentId().trim().length() > 0) {
            // 判断超级管理员
            Role role = roleService.findById(oneToManyVo.getParentId());
            if (Constant.ADMIN.equals(role.getCode())) {
                // 如果是超级管理员，不允许修改
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        return HttpResult.ok(roleService.saveRoleMenus(oneToManyVo.getParentId(), oneToManyVo.getChildrenIds()));
    }
}
