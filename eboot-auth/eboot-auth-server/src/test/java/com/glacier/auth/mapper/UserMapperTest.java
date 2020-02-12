package com.glacier.auth.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.auth.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author glacier
 * @version 1.0
 * @description 用户Mapper测试
 * @date 2020-02-12 10:37
 */
@Transactional()
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void selectOne() {
        String admin = "admin";
        User user = userMapper.selectOne(new QueryWrapper<>(User
                .builder()
                .username(admin)
                .build()));
        Assert.assertNotNull(user);
        Assert.assertEquals(admin, user.getUsername());
    }

    @Test
    public void selectPage() {
        Page<User> userPage = userMapper.selectPage(new Page<>(1, 3), new QueryWrapper<>(null));
        Assert.assertNotNull(userPage);
        Assert.assertTrue(userPage.getTotal() > 0);
    }

    @Test
    public void insert() {
        User user = User
                .builder()
                .username("test123")
                .password("test1234")
                .nickname("test12")
                .build();
        int insert = userMapper.insert(user);
        Assert.assertNotNull("用户ID不能为空", user.getId());
        Assert.assertEquals("插入不成功", 1, insert);
    }
}