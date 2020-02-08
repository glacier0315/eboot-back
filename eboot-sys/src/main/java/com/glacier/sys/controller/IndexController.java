package com.glacier.sys.controller;

import com.glacier.auth.common.utils.jwt.JwtUtils;
import com.glacier.common.core.http.HttpResult;
import com.glacier.sys.entity.dto.LoginUserDto;
import com.glacier.sys.entity.dto.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-25 17:02
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @GetMapping("/")
    public String index() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

    /**
     * @param loginUserDto
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    public HttpResult<String> login(@RequestBody LoginUserDto loginUserDto, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticatioToken = new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(), loginUserDto.getPassword());
        authenticatioToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(authenticatioToken);
        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成JWT
        String token = jwtUtils.generateToken((SysUser) authentication.getPrincipal());
        HttpResult<String> result = HttpResult.ok();
        result.setData(token);
        return result;
    }
}
