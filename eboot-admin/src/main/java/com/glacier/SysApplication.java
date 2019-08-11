package com.glacier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author hebin
 * @version 1.0
 * @description 主程序入口
 * @date 2019-04-24 20:27
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.glacier.**.dao")
public class SysApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysApplication.class, args);
	}

}
