package com.glacier.security.filter;

import com.glacier.core.http.HttpResult;
import com.glacier.security.util.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description token 过滤器
 * @date 2019-12-12 12:43
 */
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${config.authenticate.ignored-path}")
    private List<String> ignoredPath;
    @Value("${config.token.header}")
    private String header;

    @Resource
    private JwtTokenUtils jwtTokenUtils;
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("TOKEN过滤器,请求路径: {}", request.getServletPath());
        if (ignoreMath(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = jwtTokenUtils.getToken(request);
        HttpResult httpResult = jwtTokenUtils.validateToken(token);
        if (httpResult.getCode() != HttpStatus.OK.value()) {
            log.info("{},请求路径: {}", httpResult.getMsg(), request.getServletPath());
            response.sendError(httpResult.getCode(), httpResult.getMsg());
            return;
        }
        String username = ((Claims) httpResult.getData()).getSubject();
        if (username != null && !username.isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() != null) {
                log.info("会话上下文authentication: {}", authentication);
            } else {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //加载用户、角色、权限信息，Spring Security根据这些信息判断接口的访问权限
                UsernamePasswordAuthenticationToken authenticatioToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                authenticatioToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 处理上下文
                SecurityContextHolder.getContext().setAuthentication(authenticatioToken);
                log.info("重建会话上下文authentication: {}", authentication);
            }
        } else {
            log.info("TOKEN无效,请求路径: {}", request.getServletPath());
            response.sendError(HttpStatus.FORBIDDEN.value(), "TOKEN无效，请重新登录！");
            return;
        }
        // 刷新token
        String refreshToken = jwtTokenUtils.refreshToken(token);
        response.addHeader(header, refreshToken);
        filterChain.doFilter(request, response);
    }

    /**
     * 验证是否是忽略验证
     *
     * @param path
     * @return
     */
    private boolean ignoreMath(String path) {
        boolean ignore = false;
        if (ignoredPath != null && !ignoredPath.isEmpty()) {
            for (String prifx : ignoredPath) {
                if (path.startsWith(prifx)) {
                    ignore = true;
                    break;
                }
            }
        }
        return ignore;
    }
}
