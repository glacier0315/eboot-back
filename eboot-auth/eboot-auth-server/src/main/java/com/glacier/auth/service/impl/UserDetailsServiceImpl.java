package com.glacier.auth.service.impl;


import com.glacier.auth.dao.UserDao;
import com.glacier.auth.entity.User;
import com.glacier.auth.entity.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-09-30 10:15
 */
@Slf4j
@Service("userDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

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
            List<String> authorityList = new ArrayList<>(1);
            authorityList.add("ADMIN");
            authorityList.add("USER");
            return new UserDetailsDto(user, authorityList);
        }
        throw new UsernameNotFoundException("用户不存在！");
    }
}
