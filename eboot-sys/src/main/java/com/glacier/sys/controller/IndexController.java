package com.glacier.sys.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-25 17:02
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }
}
