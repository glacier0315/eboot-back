package com.glacier.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-08 09:30
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EbootGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbootGatewayApplication.class, args);
    }
}
