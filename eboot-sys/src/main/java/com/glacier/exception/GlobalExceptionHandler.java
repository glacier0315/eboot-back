package com.glacier.exception;

import com.glacier.common.core.http.HttpResult;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author glacier
 * @version 1.0
 * @description 验证码异常处理
 * @date 2019-09-29 17:06
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 认证异常
     *
     * @return
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public HttpResult<?> authenticationException(AuthenticationException e) {
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            return HttpResult.error("用户名或者密码错误！");
        }
        return HttpResult.error("登陆错误，请稍后重试！");
    }

}
