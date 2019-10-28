package com.glacier.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author hebin
 * @version 1.0
 * @description 自定义令牌对象
 * @date 2019-10-28 09:09
 */
public class JwtAuthenticatioToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = -6230277957096834940L;
    private String token;

    public JwtAuthenticatioToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtAuthenticatioToken(Object principal, Object credentials, String token) {
        super(principal, credentials);
        this.token = token;
    }

    public JwtAuthenticatioToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String token) {
        super(principal, credentials, authorities);
        this.token = token;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
