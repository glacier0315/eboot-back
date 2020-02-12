package com.glacier.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glacier.sys.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author glacier
 * @version 1.0
 * @description  用户dao层
 * @date 2019-08-04 21:53
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查找对应用户
     *
     * @param username
     * @return
     */
    User loadUserByUsername(@Param("username") String username);
}
