package com.glacier.sys.service.impl;

import com.glacier.sys.dao.RoleDao;
import com.glacier.sys.dao.UserDao;
import com.glacier.sys.entity.Role;
import com.glacier.sys.entity.User;
import com.glacier.sys.entity.dto.SysUser;
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
        if (user == null || user.getId() == null || "".equals(user.getId().trim())) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 查找角色
        List<Role> roles = roleDao.findByUserId(user.getId());
        return new SysUser(user, roles);
    }
}
