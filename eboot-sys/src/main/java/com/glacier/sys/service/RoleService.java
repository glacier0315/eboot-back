package com.glacier.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.page.PageRequest;
import com.glacier.sys.entity.Role;
import com.glacier.sys.entity.dto.IdDto;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 角色业务层
 * @date 2019-08-11 21:20
 */
public interface RoleService {


    /**
     * 根据id 查询角色
     * @param id
     * @return
     */
    Role findById(Serializable id);

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    Page<Role> findPage(PageRequest<Role> pageRequest);

    /**
     * 根绝用户id 查询用户所拥有的角色
     *
     * @return
     */
    List<Role> findByUserId(String userId);

    /**
     * 查询所有
     *
     * @return
     */
    List<Role> findAllList();

    /**
     * 校验
     *
     * @param role
     * @return
     */
    boolean checkCode(Role role);

    /**
     * 保存操作
     *
     * @param record
     * @return
     */
    int save(Role record);

    /**
     * 根据Id批量删除
     *
     * @param idDtos
     * @return
     */
    int batchDelete(List<IdDto> idDtos);
}
