package com.glacier.auth.config;

import com.glacier.auth.entity.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-09 08:28
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenStoreConfig {
    private final UserDetailsService userDetailsService;

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
        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        accessTokenConverter.setSigningKey("123");
        return accessTokenConverter;
    }

    /**
     * jwt内容转换
     *
     * @return
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>(2);
        delegates.add(jwtAccessTokenConverter());
        // jwt 内容增强
        delegates.add((accessToken, authentication) -> {
            Map<String, Object> additionalInformation = accessToken.getAdditionalInformation();
            Object principal = authentication.getUserAuthentication().getPrincipal();
            if (principal instanceof UserDetailsDto) {
                UserDetailsDto userDetailsDto = (UserDetailsDto) principal;
                additionalInformation.put("userId", userDetailsDto.getUserId());
            } else {
                // 解决refresh token 时 内容增强
                UserDetailsDto userDetailsDto = (UserDetailsDto) userDetailsService.loadUserByUsername((String) principal);
                additionalInformation.put("userId", userDetailsDto.getUserId());
            }
            if (accessToken instanceof DefaultOAuth2AccessToken) {
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        });
        tokenEnhancerChain.setTokenEnhancers(delegates);
        return tokenEnhancerChain;
    }
}
