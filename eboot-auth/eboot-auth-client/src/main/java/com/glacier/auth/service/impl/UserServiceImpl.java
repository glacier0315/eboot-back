package com.glacier.auth.service.impl;

import com.glacier.auth.entity.dto.JwtDto;
import com.glacier.auth.entity.dto.LoginUserDto;
import com.glacier.auth.remote.service.AuthServiceClient;
import com.glacier.auth.service.UserService;
import com.glacier.common.core.http.HttpResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Service;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-10 18:40
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final AuthServiceClient authServiceClient;
    private final ClientCredentialsResourceDetails clientCredentialsResourceDetails;
    @Override
    public HttpResult<JwtDto> login(LoginUserDto loginUserDto) {
        JwtDto jwtDto = authServiceClient.getToken("password",
                clientCredentialsResourceDetails.getClientId(),
                clientCredentialsResourceDetails.getClientSecret(),
                "",
                loginUserDto.getUsername(),
                loginUserDto.getPassword());
        return HttpResult.ok(jwtDto);
    }
}
