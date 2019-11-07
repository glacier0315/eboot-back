package com.glacier.sys.controller;

import com.glacier.core.http.HttpResult;
import com.glacier.security.JwtAuthenticatioToken;
import com.glacier.security.util.SecurityUtils;
import com.glacier.security.vo.LoginBean;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-25 17:02
 */
@RestController
public class IndexController {

    @Autowired
    private AuthenticationManager authenticationManager;


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
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (kaptcha == null) {
            return HttpResult.error("验证码已失效！");
        } else if (!String.valueOf(kaptcha).equals(loginBean.getCaptcha())) {
            return HttpResult.error("验证码不正确！");
        }
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, loginBean.getAccount(), loginBean.getPassword(), authenticationManager);
        return HttpResult.ok(token);
    }
}
