package com.glacier.auth.remote.service.impl;

import com.glacier.auth.entity.dto.JwtDto;
import com.glacier.auth.remote.service.AuthServiceClient;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-10 13:36
 */
public class AuthServiceHystrix implements AuthServiceClient {
//    @Override
//    public JwtDto getToken(AuthDto authDto) {
//        return null;
//    }

    @Override
    public JwtDto getToken(String grantType, String clientId, String clientSecret, String redirectUri, String username, String password) {
        return null;
    }
}