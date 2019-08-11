package com.glacier.sys.controller;

import com.github.pagehelper.PageInfo;
import com.glacier.sys.entity.User;
import com.glacier.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-11 21:24
 */
@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Autowired
    private UserService userService;

    @GetMapping("get/{id}")
    public User get(@PathVariable("id") String id) {
        return userService.get(id);
    }

    /**
     * @param user
     * @return
     */
    @GetMapping("list")
    public List<User> list(User user) {
        return userService.findList(user);
    }

    /**
     * @param user
     * @return
     */
    @GetMapping("page")
    public PageInfo<User> page(User user, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "1") int pageSize) {
        return userService.findPage(user, pageNum, pageSize);
    }

    @PutMapping("save")
    public int save(User user) {
        return userService.save(user);
    }

    @DeleteMapping("del/{id}")
    public int delete(@PathVariable("id") String id) {
        User user = new User();
        user.setId(id);
        return userService.delete(user);
    }
}
