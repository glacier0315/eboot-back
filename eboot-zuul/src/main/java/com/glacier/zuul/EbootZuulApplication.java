package com.glacier.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-30 15:05
 */
@EnableZuulProxy
@SpringBootApplication
public class EbootZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbootZuulApplication.class, args);
    }
}
