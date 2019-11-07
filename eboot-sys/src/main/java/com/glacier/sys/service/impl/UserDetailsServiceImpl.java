package com.glacier.sys.service.impl;

import com.glacier.sys.entity.SysUser;
import com.glacier.sys.entity.User;
import com.glacier.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-09-30 10:15
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadUserByUsername(username);
        if (user == null || user.getId() == null || "".equals(user.getId().trim())) {
            throw new UsernameNotFoundException("用户不存在！");
        } else {
            //
        }
        return new SysUser(user);
    }
}
