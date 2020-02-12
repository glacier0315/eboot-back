package com.glacier.auth.config;

import com.glacier.auth.entity.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
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
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("eboot-jwt.jks"), "eboot-secret".toCharArray())
                .getKeyPair("eboot-jwt", "jwt-eboot".toCharArray());
        // 使用非对称加密
        accessTokenConverter.setKeyPair(keyPair);
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
        // jwt令牌内容增强
        TokenEnhancer tokenEnhancer = (accessToken, authentication) -> {
            Map<String, Object> additionalInformation = accessToken.getAdditionalInformation();
            String grantType = authentication.getOAuth2Request().getGrantType();
            // 授权类型 authorization_code、password、client_credentials、refresh_token、implicit
            // 客户端模式
            String clientCredentials = "client_credentials";
            if (!StringUtils.equals(grantType, clientCredentials)) {
                Object principal = authentication.getUserAuthentication().getPrincipal();
                if (principal instanceof UserDetailsDto) {
                    UserDetailsDto userDetailsDto = (UserDetailsDto) principal;
                    additionalInformation.put("userId", userDetailsDto.getUserId());
                } else {
                    // 解决refresh token 时 内容增强
                    UserDetailsDto userDetailsDto = (UserDetailsDto) userDetailsService.loadUserByUsername((String) principal);
                    additionalInformation.put("userId", userDetailsDto.getUserId());
                }
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };
        List<TokenEnhancer> delegates = new ArrayList<>(2);
        delegates.add(jwtAccessTokenConverter());
        delegates.add(tokenEnhancer);
        tokenEnhancerChain.setTokenEnhancers(delegates);
        return tokenEnhancerChain;
    }
}
