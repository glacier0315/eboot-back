package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.dao.RoleDao;
import com.glacier.sys.dao.UserDao;
import com.glacier.sys.entity.User;
import com.glacier.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 用户业务类
 * @date 2019-08-04 21:50
 */
@Slf4j
@Transactional(readOnly = true)
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int save(User user) {
        if (user.isNewRecord()) {
            return userDao.insert(user);
        } else {
            return userDao.update(user);
        }
    }

    @Override
    public List<User> findList(User user) {
        return userDao.findList(user);
    }

    @Override
    public PageInfo<User> findPage(PageRequest<User> pageRequest) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<User> list = userDao.findList(pageRequest.getData());
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(User user) {
        return userDao.delete(user);
    }
}
