package com.glacier.sys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-04-24 20:27
 */
@RestController
public class HelloController {

	@GetMapping(value = "hello")
	public String hello() {
		return "hello world";
	}
}
