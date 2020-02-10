package com.glacier.security.filter;

import com.glacier.auth.common.utils.jwt.IjwtInfo;
import com.glacier.auth.common.utils.jwt.JwtUtils;
import com.glacier.config.JwtConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author glacier
 * @version 1.0
 * @description token 过滤器
 * @date 2019-12-12 12:43
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final JwtConfig jwtConfig;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("TOKEN过滤器,请求路径: {}", request.getServletPath());
        if (jwtConfig.ignoreMath(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(jwtConfig.getHeader());
        if (token == null || token.trim().isEmpty()) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "用户token为空");
            return;
        }
        IjwtInfo ijwtInfo = null;
        try {
            ijwtInfo = jwtUtils.getInfoFromToken(token);
        } catch (Exception e) {
            log.error("请求路径: {}, token异常, 原因 {}", request.getServletPath(), e);
            response.sendError(HttpStatus.FORBIDDEN.value(), "用户token异常");
            return;
        }
        if (ijwtInfo != null && ijwtInfo.getUsername() != null && !ijwtInfo.getUsername().isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() != null) {
                log.info("会话上下文authentication: {}", authentication);
            } else {
                UserDetails userDetails = userDetailsService.loadUserByUsername(ijwtInfo.getUsername());
                //加载用户、角色、权限信息，Spring Security根据这些信息判断接口的访问权限
                UsernamePasswordAuthenticationToken authenticatioToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                authenticatioToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 处理上下文
                SecurityContextHolder.getContext().setAuthentication(authenticatioToken);
                log.info("重建会话上下文authentication: {}", authenticatioToken);
            }
        } else {
            log.info("TOKEN无效,请求路径: {}", request.getServletPath());
            response.sendError(HttpStatus.FORBIDDEN.value(), "TOKEN无效，请重新登录！");
            return;
        }
        filterChain.doFilter(request, response);
    }


}
