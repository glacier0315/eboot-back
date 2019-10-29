package com.glacier.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-28 16:38
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class EbootMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbootMonitorApplication.class, args);
    }
}
