package com.glacier.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-09 08:28
 */
@Configuration
public class JwtTokenStoreConfig {

    /**
     * 设置jwt 存储token
     *
     * @return
     */
    @Bean
    @Primary
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * jwt转换
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        String publicKey;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(new ClassPathResource("publickey.txt").getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        accessTokenConverter.setVerifierKey(publicKey);
        return accessTokenConverter;
    }
}
