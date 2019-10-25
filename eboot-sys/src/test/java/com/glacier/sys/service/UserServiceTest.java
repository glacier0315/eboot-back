package com.glacier.sys.service;

import com.glacier.EbootSysApplication;
import com.glacier.sys.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Rollback(false)
    // @Test
    public void save() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        Date time = Calendar.getInstance().getTime();
        user.setCreateDate(time);
        userService.save(user);
    }

    @Test
    public void findPage() {
    }
}
