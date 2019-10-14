package com.glacier.sys.service;

import com.github.pagehelper.PageInfo;
import com.glacier.core.page.PageRequest;
import com.glacier.core.service.CurdService;
import com.glacier.sys.entity.User;

/**
 * @author glacier
 * @version 1.0
 * @description  用户业务层
 * @date 2019-08-04 21:50
 */
public interface UserService extends CurdService<User> {

    /**
     * 分页查询
     * @param pageRequest
     * @return
     */
    PageInfo<User> findPage(PageRequest<User> pageRequest);

}
