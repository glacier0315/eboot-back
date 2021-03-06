package com.glacier.security.util;

import com.glacier.sys.entity.User;
import com.glacier.sys.service.UserService;
import com.glacier.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-28 09:08
 */
@Slf4j
public class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * 获取当前用户ID
     *
     * @return
     */
    public static String geUserId() {
        String userId = null;
        User user = geUser();
        if (user != null) {
            userId = user.getId();
        }
        return userId;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static User geUser() {
        User user = null;
        String username = getUsername();
        if (username != null) {
            UserService userService = SpringContextUtil.getBean(UserService.class);
            user = userService.loadUserByUsername(username);
        }
        return user;
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    public static String getUsername() {
        return getUsername(getAuthentication());
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            log.info("principal: {}", principal);
            if (principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        log.info("username: {}", username);
        return username;
    }

    /**
     * 获取当前登录信息
     *
     * @return
     */
    public static Authentication getAuthentication() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);
        return authentication;
    }
}
