package com.glacier.sys.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.glacier.core.http.HttpResult;
import com.glacier.security.util.JwtTokenUtils;
import com.glacier.security.vo.LoginBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-25 17:02
 */
@RestController
public class IndexController {

    @Resource
    private Kaptcha kaptcha;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtTokenUtils jwtTokenUtils;


    @GetMapping("/")
    public String index() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

    /**
     * @param loginBean
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) {
        boolean validate = kaptcha.validate(loginBean.getCaptcha(), 60);
        if (!validate) {
            return HttpResult.error("验证码不正确！");
        }
        UsernamePasswordAuthenticationToken authenticatioToken = new UsernamePasswordAuthenticationToken(loginBean.getAccount(), loginBean.getPassword());
        authenticatioToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(authenticatioToken);
        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成JWT
        String token = jwtTokenUtils.generateToken(((UserDetails) authentication.getPrincipal()).getUsername());
        HttpResult result = HttpResult.ok();
        result.setData(token);
        return result;
    }
}
