package com.glacier.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-23 10:31
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EbootAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbootAuthServerApplication.class, args);
    }

}
