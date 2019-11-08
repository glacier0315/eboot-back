package com.glacier.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author glacier
 * @version 1.0
 * @description 权限封装
 * @date 2019-10-28 09:13
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private static final long serialVersionUID = -5251491855417434812L;
    private String authority;

    public GrantedAuthorityImpl() {
    }

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
