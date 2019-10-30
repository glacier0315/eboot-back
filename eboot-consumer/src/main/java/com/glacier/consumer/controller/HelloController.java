package com.glacier.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-29 17:44
 */
@RestController
public class HelloController {

    @GetMapping("")
    public String hello() {
        return "hello world";
    }
}
