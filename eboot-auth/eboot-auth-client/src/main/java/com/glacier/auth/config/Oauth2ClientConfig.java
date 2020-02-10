package com.glacier.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-10 13:36
 */
@Configuration
@EnableOAuth2Client
@EnableConfigurationProperties
public class Oauth2ClientConfig {
    /**
     * 受保护资源的配置信息（来源于application.properties）
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

//    /**
//     * 在feign调用的时候，也加入认证信息
//     * @return
//     */
//    @Bean
//    public RequestInterceptor oauth2FeignRequestlnterceptor() {
//        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
//    }

    /**
     * 在Request域内，创建AccessTokenRequest类型的Bean
     * @return
     */
    @Bean
    public OAuth2RestTemplate clientCredentialsRestTemplate() {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }
}
