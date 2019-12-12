package com.glacier.security.filter;

import com.glacier.core.http.HttpStatus;
import com.glacier.security.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Value;
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
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${config.authenticate.ignore-path}")
    private List<String> ignorePath;

    @Resource
    private JwtTokenUtils jwtTokenUtils;
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (ignoreMath(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = jwtTokenUtils.getToken(request);
        if (token != null) {
            if (jwtTokenUtils.isTokenExpired(token)) {
                response.sendError(HttpStatus.SC_FORBIDDEN, "TOKEN已过期，请重新登录！");
                return;
            } else {
                String username = jwtTokenUtils.getUsernameFromToken(token);
                if (username != null && !username.isEmpty()) {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    if (authentication != null && authentication.getPrincipal() != null) {

                    } else {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        //加载用户、角色、权限信息，Spring Security根据这些信息判断接口的访问权限
                        UsernamePasswordAuthenticationToken authenticatioToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                        authenticatioToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // 处理上下文
                        SecurityContextHolder.getContext().setAuthentication(authenticatioToken);
                    }
                } else {
                    response.sendError(HttpStatus.SC_FORBIDDEN, "TOKEN无效，请重新登录！");
                    return;
                }
            }
        } else {
            response.sendError(HttpStatus.SC_FORBIDDEN, "TOKEN无效，请重新登录！");
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 验证是否是忽略验证
     * @param path
     * @return
     */
    private boolean ignoreMath(String path) {
        boolean ignore = false;
        if (ignorePath !=null && !ignorePath.isEmpty()) {
            for (String prifx : ignorePath) {
                if (path.startsWith(prifx)) {
                    ignore = true;
                    break;
                }
            }
        }
        return ignore;
    }
}
