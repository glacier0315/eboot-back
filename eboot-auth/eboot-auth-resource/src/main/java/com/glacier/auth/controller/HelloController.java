package com.glacier.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-11 13:50
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello(String name) {
        return "hello " + name;
    }
}
