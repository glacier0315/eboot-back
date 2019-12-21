package com.glacier.sys.dao;

import com.glacier.common.core.dao.CurdDao;
import com.glacier.sys.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author glacier
 * @version 1.0
 * @description  用户dao层
 * @date 2019-08-04 21:53
 */
public interface UserDao extends CurdDao<User> {

    /**
     * 更新用户密码
     * @param user
     * @return
     */
    int updatePassword(User user);

    /**
     * 根据用户名查找对应用户
     *
     * @param username
     * @return
     */
    User loadUserByUsername(@Param("username") String username);
}
