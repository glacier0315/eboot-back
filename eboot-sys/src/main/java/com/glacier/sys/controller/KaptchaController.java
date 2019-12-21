package com.glacier.sys.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.glacier.common.core.http.HttpResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author glacier
 * @version 1.0
 * @description 验证码
 * @date 2019-09-29 16:39
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Resource
    private Kaptcha kaptcha;

    /**
     * 获取验证码
     */
    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    /**
     * 校验验证码
     *
     * @param code
     */
    @PostMapping("/valid")
    public HttpResult<Boolean> validDefaultTime(@RequestParam String code) {
        //default timeout 900 seconds
        return HttpResult.ok(kaptcha.validate(code));
    }

    /**
     * 校验验证码
     *
     * @param code
     */
    @PostMapping("/validTime")
    public HttpResult<Boolean> validWithTime(@RequestParam String code) {
        return HttpResult.ok(kaptcha.validate(code, 60));
    }
}
