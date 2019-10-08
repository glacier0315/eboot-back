package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public User get(String id) {
        return userDao.get(id);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int save(User user) {
        return userDao.insert(user);
    }

    @Override
    public List<User> findList(User user) {
        return userDao.findList(user);
    }

    /**
     * 这个方法中用到了开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * @param user
     * @param pageNum  开始页数
     * @param pageSize 每页显示的数据条数
     * @return
     */
    @Override
    public PageInfo<User> findPage(User user, int pageNum, int pageSize) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userDao.findList(user);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(User user) {
        return userDao.delete(user);
    }
}
