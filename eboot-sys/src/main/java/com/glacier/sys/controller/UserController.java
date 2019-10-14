package com.glacier.sys.controller;

import com.github.pagehelper.PageInfo;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.entity.User;
import com.glacier.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 根据用户id 查询用户
     *
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    public User get(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    /**
     * 查询所有用户
     *
     * @param user
     * @return
     */
    @GetMapping("list")
    public List<User> list(User user) {
        return userService.findList(user);
    }

    /**
     * 分页查询用户
     *
     * @param user
     * @return
     */
    @GetMapping("page")
    public PageInfo<User> page(User user, @RequestParam int pageNum,
                               @RequestParam int pageSize) {
        return userService.findPage(new PageRequest<>(pageNum, pageSize, user));
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PutMapping("save")
    public int save(User user) {
        return userService.save(user);
    }

    /**
     * 删除指定用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("del/{id}")
    public int delete(@PathVariable("id") String id) {
        User user = new User();
        user.setId(id);
        return userService.delete(user);
    }
}
