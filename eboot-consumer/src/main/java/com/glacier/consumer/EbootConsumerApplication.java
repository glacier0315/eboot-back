package com.glacier.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-29 17:40
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EbootConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbootConsumerApplication.class, args);
    }
}
