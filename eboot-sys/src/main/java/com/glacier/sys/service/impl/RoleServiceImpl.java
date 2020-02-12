package com.glacier.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.page.PageRequest;
import com.glacier.sys.entity.Role;
import com.glacier.sys.entity.dto.IdDto;
import com.glacier.sys.mapper.RoleMapper;
import com.glacier.sys.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author glacier
 * @version 1.0
 * @description 角色业务类
 * @date 2019-08-11 21:21
 */
@Slf4j
@Transactional(readOnly = true)
@Service("RoleService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    /**
     * 根据Id 查询
     * @param id
     * @return
     */
    @Override
    public Role findById(Serializable id) {
        return roleMapper.selectById(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Role> findAllList() {
        return roleMapper.selectList(null);
    }

    /**
     * 根据用户id 查询角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> findByUserId(String userId) {
        return roleMapper.findByUserId(userId);
    }

    @Override
    public boolean checkCode(Role role) {
        if (role != null && role.getCode() != null && role.getCode().trim().length() > 0) {
            Role role1 = null;
            if (role.getId() != null && role.getId().trim().length() > 0) {
                role1 = roleMapper.selectById(role.getId());
                if (role1 != null && role1.getCode() != null  && role1.getCode().equals(role.getCode())) {
                    return false;
                }
            }
            List<Role> list =roleMapper.selectList(new QueryWrapper<>(role));
            if (list != null && !list.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 这个方法中用到了开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     *
     * @param pageRequest
     * @return
     */
    @Override
    public Page<Role> findPage(PageRequest<Role> pageRequest) {
        return roleMapper.selectPage(new Page<>(pageRequest.getCurrent(), pageRequest.getSize()),
                new QueryWrapper<>(pageRequest.getParams()));
    }

    /**
     * 保存
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Role record) {
        int update = 0;
        if (record.getId() != null && !record.getId().isEmpty()) {
            update = roleMapper.updateById(record);
        } else {
            update = roleMapper.insert(record);
        }
        return update;
    }

    /**
     * 根据id批量删除
     *
     * @param idDtos
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<IdDto> idDtos) {
        if (idDtos != null && !idDtos.isEmpty()) {
            List<String> list = idDtos.stream()
                    .map(IdDto::getId)
                    .collect(Collectors.toList());
            return roleMapper.deleteBatchIds(list);
        }
        return 0;
    }
}
