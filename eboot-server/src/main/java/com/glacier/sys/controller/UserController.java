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
 * @date 2019-08-04 22:13
 */
@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

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
    public PageInfo<User> page(User user,@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "1") int pageSize) {
        if (pageSize <= 0) {
            pageSize = 1;
        }
        return userService.findPage(user, pageNum, pageSize);
    }

    @PutMapping("save")
    public int save(User user) {
        return userService.save(user);
    }

    @DeleteMapping("del")
    public int delete(User user) {
        return userService.delete(user);
    }
}
