package com.glacier.auth.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description  角色Mapper测试
 * @date 2020-02-12 11:15
 */
@Transactional()
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {

    @Resource
    private RoleMapper roleMapper;

    @Test
    public void findRoleCodesByUser() {
        String userId = "1";
        List<String> codes = roleMapper.findRoleCodesByUser(userId);
        Assert.assertNotNull(codes);
        Assert.assertFalse(codes.isEmpty());
    }
}