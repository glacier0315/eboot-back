package com.glacier.sys.service;

import com.glacier.EbootSysApplication;
import com.glacier.sys.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-25 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EbootSysApplication.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Rollback(false)
    // @Test
    public void save() {
        User user = User.builder()
                .username("admin")
                .nickname("超级管理员")
                .idCard("11111111111111111111")
                .password("admin")
                .createDate(Calendar.getInstance().getTime())
                .build();
        userService.save(user);
    }

    @Test
    public void findPage() {
    }
}
