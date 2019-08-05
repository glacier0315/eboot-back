package com.glacier.sys.service;

import com.github.pagehelper.PageInfo;
import com.glacier.sys.entity.User;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-04 21:50
 */
public interface UserService {
    /**
     *
     * @param user
     * @return
     */
    int save(User user);

    /**
     *
     * @param user
     * @return
     */
    List<User> findList(User user);

    /**
     *
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<User> findPage(User user, int pageNum, int pageSize);

    /**
     *
     * @param user
     * @return
     */
    int delete(User user);
}
