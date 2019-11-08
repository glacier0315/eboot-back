package com.glacier.sys.controller;

import com.glacier.core.http.HttpResult;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.entity.User;
import com.glacier.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author glacier
 * @version 1.0
 * @description 用户管理
 * @date 2019-08-04 22:13
 */
@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("findPage")
    public HttpResult findPage(@RequestBody PageRequest<User> pageRequest) {
        return HttpResult.ok(userService.findPage(pageRequest));
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping("save")
    public HttpResult save(@RequestBody User user) {
        return HttpResult.ok(userService.save(user));
    }

    /**
     * 删除指定用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("delete")
    public HttpResult delete(@RequestBody String id) {
        User user = new User();
        user.setId(id);
        return HttpResult.ok(userService.delete(user));
    }
}
