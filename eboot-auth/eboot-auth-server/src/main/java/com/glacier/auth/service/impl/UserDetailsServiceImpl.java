package com.glacier.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glacier.auth.entity.User;
import com.glacier.auth.entity.dto.UserDetailsDto;
import com.glacier.auth.mapper.RoleMapper;
import com.glacier.auth.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-09-30 10:15
 */
@Slf4j
@Service("UserDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<>(User
                .builder()
                .username(username)
                .build()));
        if (user != null) {
            // 查找角色
            List<String> authorityList = roleMapper.findRoleCodesByUser(user.getId());
            return UserDetailsDto.builder()
                    .userId(user.getId())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .nickname(user.getNickname())
                    .authorityList(authorityList)
                    .build();
        }
        throw new UsernameNotFoundException("用户不存在！");
    }
}
