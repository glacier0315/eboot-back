package com.glacier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author glacier
 * @version 1.0
 * @description 主程序入口
 * @date 2019-04-24 20:27
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.glacier.**.dao")
public class EbootSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbootSysApplication.class, args);
	}

}
