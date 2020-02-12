package com.glacier.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-09 08:28
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(value = "/current")
    public Object current() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(principal.toString());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }
}