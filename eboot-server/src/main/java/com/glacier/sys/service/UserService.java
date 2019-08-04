package com.glacier.sys.service;

import com.glacier.sys.entity.User;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-04 21:50
 */
public interface UserService {
    int save(User user);

    List<User> findList(User user);

    int delete(User user);
}
