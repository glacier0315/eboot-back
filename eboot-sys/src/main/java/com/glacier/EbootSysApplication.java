package com.glacier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author glacier
 * @version 1.0
 * @description 主程序入口
 * @date 2019-04-24 20:27
 */
@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
public class EbootSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbootSysApplication.class, args);
	}

}
