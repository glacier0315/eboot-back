package com.glacier.auth.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author glacier
 * @version 1.0
 * @description Feign配置
 * @date 2020-02-10 19:33
 */
@Configuration
@EnableFeignClients("com.glacier.auth.remote.service")
public class FeignConfig {

    /**
     * 修改Feign日志输出级别
     * @return
     */
    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }
}
