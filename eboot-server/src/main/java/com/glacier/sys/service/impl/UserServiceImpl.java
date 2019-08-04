package com.glacier.sys.service.impl;

import com.glacier.sys.dao.UserDao;
import com.glacier.sys.entity.User;
import com.glacier.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-04 21:50
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public int save(User user) {
        return userDao.insert(user);
    }

    @Override
    public List<User> findList(User user) {
        return userDao.findList(user);
    }

    @Override
    public int delete(User user) {
        return userDao.delete(user);
    }
}
