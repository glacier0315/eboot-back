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
 * @date 2020-02-10 18:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDto implements Serializable {
    private static final long serialVersionUID = -461080378054752369L;
    private String client_id;
    private String client_secret;
    private String grant_type;
    private String username;
    private String password;
    private String redirect_uri;
}
