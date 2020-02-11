package com.glacier.sys.service.impl;

import com.glacier.sys.dao.RoleDao;
import com.glacier.sys.dao.UserDao;
import com.glacier.sys.entity.Role;
import com.glacier.sys.entity.User;
import com.glacier.sys.entity.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final UserDao userDao;
    private final RoleDao roleDao;

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.loadUserByUsername(username);
        if (user != null) {
            // 查找角色
            List<Role> roles = roleDao.findByUserId(user.getId());
            List<String> authorityList = new ArrayList<>(5);
            if (roles != null && !roles.isEmpty()) {
                authorityList = roles.stream().map(Role::getCode).collect(Collectors.toList());
            }
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
