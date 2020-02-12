package com.glacier.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-23 10:31
 */
@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication
public class EbootAuthClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbootAuthClientApplication.class, args);
    }

}
