package com.glacier.sys.service;

import com.github.pagehelper.PageInfo;
import com.glacier.common.service.CrudService;
import com.glacier.sys.entity.User;

/**
 * @author glacier
 * @version 1.0
 * @description  用户业务层
 * @date 2019-08-04 21:50
 */
public interface UserService extends CrudService<User, String> {


    /**
     * 分页查询用户
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<User> findPage(User user, int pageNum, int pageSize);

}
