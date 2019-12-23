package com.glacier.auth.server.config;


import com.glacier.auth.common.utils.jwt.JwtUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-21 21:22
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "config.jwt-token")
public class JwtConfig {
    private String header;
    private String secret;
    private long expireTime;

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils(secret, expireTime);
    }
}