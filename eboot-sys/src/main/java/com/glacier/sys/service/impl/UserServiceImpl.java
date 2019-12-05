package com.glacier.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.utils.IdGen;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.dao.UserDao;
import com.glacier.sys.entity.User;
import com.glacier.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@Service(value = "UserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 根据用户id 查找用户
     *
     * @param id
     * @return
     */
    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Override
    public User loadUserByUsername(String username) {
        return userDao.loadUserByUsername(username);
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(User user) {
        if (user.newRecord()) {
            if (!user.isNewRecord()) {
                user.setId(IdGen.uuid());
            }
            if (user.getPassword() != null && user.getPassword().trim().length() > 0) {
                // 加密密码
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return userDao.insert(user);
        } else {
            return userDao.update(user);
        }
    }

    /**
     * 查找用户
     * @param user
     * @return
     */
    @Override
    public List<User> findList(User user) {
        return userDao.findList(user);
    }

    /**
     * 分页查找
     * @param pageRequest
     * @return
     */
    @Override
    public PageInfo<User> findPage(PageRequest<User> pageRequest) {
        //将参数传给这个方法就可实现物理分页.
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<User> list = userDao.findList(pageRequest.getParams());
        return new PageInfo<>(list);
    }

    /**
     * 删除
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(User user) {
        return userDao.delete(user);
    }

    /**
     * 批量删除
     *
     * @param users
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<User> users) {
        int delCount = 0;
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                delCount += userDao.delete(user);
            }
        }
        return delCount;
    }
}
