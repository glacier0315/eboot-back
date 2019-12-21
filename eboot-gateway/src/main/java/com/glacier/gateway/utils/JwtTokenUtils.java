package com.glacier.gateway.utils;

import com.glacier.common.core.http.HttpResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-28 09:11
 */
@Slf4j
@Component
public class JwtTokenUtils {

    private static final int SC_FORBIDDEN = 403;

    @Value("${gateway.token.header}")
    private String header;
    @Value("${gateway.token.secret}")
    private String secret;
    @Value("${gateway.token.expire_time}")
    private long expireTime;

    /**
     * 刷新令牌
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.setExpiration(new Date(System.currentTimeMillis() + expireTime));
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            log.error("解析token失败，token: {}, 原因是： {}", token, ex);
        }
        return claims;
    }

    /**
     * 验证令牌
     *
     * @param token
     * @return
     */
    public HttpResult<Claims> validateToken(String token) {
        HttpResult<Claims> httpResult = HttpResult.ok();
        if (token != null) {
            Claims claims = getClaimsFromToken(token);
            if (claims != null && claims.getExpiration() != null
                    && claims.getSubject() != null && !claims.getSubject().isEmpty()) {
                if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
                    httpResult.setCode(SC_FORBIDDEN);
                    httpResult.setMsg("TOKEN已过期,请重新登录！");
                    return httpResult;
                } else {
                    httpResult.setData(claims);
                }
            } else {
                httpResult.setCode(SC_FORBIDDEN);
                httpResult.setMsg("TOKEN无效,请重新登录！");
                return httpResult;
            }
        } else {
            httpResult.setCode(SC_FORBIDDEN);
            httpResult.setMsg("TOKEN无效,请重新登录！");
            return httpResult;
        }
        httpResult.setMsg("TOKEN验证通过");
        return httpResult;
    }
}
