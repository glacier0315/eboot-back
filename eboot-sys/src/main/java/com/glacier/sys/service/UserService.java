package com.glacier.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.page.PageRequest;
import com.glacier.sys.entity.User;
import com.glacier.sys.entity.dto.IdDto;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 用户业务层
 * @date 2019-08-04 21:50
 */
public interface UserService {

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    Page<User> findPage(PageRequest<User> pageRequest);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User loadUserByUsername(String username);

    /**
     * 保存操作
     *
     * @param record
     * @return
     */
    int save(User record);

    /**
     * 根据Id批量删除
     *
     * @param idDtos
     * @return
     */
    int batchDelete(List<IdDto> idDtos);
}
