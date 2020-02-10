package com.glacier.auth.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-10 18:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto implements Serializable {
    private static final long serialVersionUID = 5132857740767639437L;
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private String jti;
}
