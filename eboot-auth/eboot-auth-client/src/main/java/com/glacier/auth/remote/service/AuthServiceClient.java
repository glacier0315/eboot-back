package com.glacier.auth.remote.service;

import com.glacier.auth.entity.dto.JwtDto;
import com.glacier.auth.remote.service.impl.AuthServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-10 13:36
 */
@FeignClient(value = "eboot-auth-server", fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {

//    /**
//     * 获取token
//     * @param authDto
//     * @return
//     */
//    @PostMapping(value = "/oauth/token", consumes = "application/x-www-form-urlencoded")
//    JwtDto getToken(AuthDto authDto);

    /**
     * 获取token
     * @param grantType
     * @param clientId
     * @param clientSecret
     * @param redirectUri
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/oauth/token")
    JwtDto getToken(@RequestParam("grant_type") String grantType,
                    @RequestParam("client_id") String clientId,
                    @RequestParam("client_secret") String clientSecret,
                    @RequestParam("redirect_uri") String redirectUri,
                    @RequestParam("username") String username,
                    @RequestParam("password") String password);
}