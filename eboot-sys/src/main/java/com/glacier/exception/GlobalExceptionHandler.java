package com.glacier.exception;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.glacier.core.http.HttpResult;
import org.springframework.security.core.AuthenticationException;
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
     * 处理验证码异常
     *
     * @param kaptchaException
     * @return
     */
    @ExceptionHandler(value = KaptchaException.class)
    public HttpResult kaptchaExceptionHandler(KaptchaException kaptchaException) {
        if (kaptchaException instanceof KaptchaIncorrectException) {
            return HttpResult.error("验证码不正确！");
        } else if (kaptchaException instanceof KaptchaNotFoundException) {
            return HttpResult.error("验证码已失效！");
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
            return HttpResult.error("验证码过期！");
        } else {
            return HttpResult.error("验证码渲染失败！");
        }
    }

    /**
     * 处理 认证异常
     * @return
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public HttpResult authenticationException(AuthenticationException e) {
        return HttpResult.error("用户名或者密码错误！");
    }
}
