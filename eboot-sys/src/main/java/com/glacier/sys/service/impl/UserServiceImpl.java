package com.glacier.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.page.PageRequest;
import com.glacier.sys.entity.User;
import com.glacier.sys.entity.dto.IdDto;
import com.glacier.sys.mapper.UserMapper;
import com.glacier.sys.service.UserRoleService;
import com.glacier.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author glacier
 * @version 1.0
 * @description 用户业务类
 * @date 2019-08-04 21:50
 */
@Slf4j
@Transactional(readOnly = true)
@Service(value = "UserService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Override
    public User loadUserByUsername(String username) {
        return userMapper.loadUserByUsername(username);
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(User user) {
        int update = 0;
        if (user.getId() != null && !user.getId().isEmpty()) {
            update = userMapper.updateById(user);
            userRoleService.insert(user.getId(), user.getRoleIds());
        } else {
            // 对原始密码加密
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                // 加密密码
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            update = userMapper.insert(user);
        }
        return update;
    }

    /**
     * 分页查找
     * @param pageRequest
     * @return
     */
    @Override
    public Page<User> findPage(PageRequest<User> pageRequest) {
        return userMapper.selectPage(new Page<>(pageRequest.getCurrent(), pageRequest.getSize()),
                new QueryWrapper<>(pageRequest.getParams()));
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
            return userMapper.deleteBatchIds(list);
        }
        return 0;
    }
}
