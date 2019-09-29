package com.glacier.sys.controller;

import com.baomidou.kaptcha.Kaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hebin
 * @version 1.0
 * @description 验证码
 * @date 2019-09-29 16:39
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    @PostMapping("/valid")
    public void validDefaultTime(@RequestParam String code) {
        //default timeout 900 seconds
        kaptcha.validate(code);
    }

    @PostMapping("/validTime")
    public void validWithTime(@RequestParam String code) {
        kaptcha.validate(code, 60);
    }
}
