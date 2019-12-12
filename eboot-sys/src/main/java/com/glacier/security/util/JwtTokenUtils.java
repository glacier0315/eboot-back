package com.glacier.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-28 09:11
 */
@Slf4j
@Setter
@Component
@ConfigurationProperties(prefix = "config.token")
public class JwtTokenUtils {

    /**
     * 权限列表
     */
    private static final String AUTHORITIES = "authorities";
    private String header;
    private String secret;
    private long expireTime;

    /**
     * 生成令牌
     *
     * @param user 用户
     * @return 令牌
     */
    public String generateToken(UserDetails user) {
        long currentTimeMillis = System.currentTimeMillis();
        Claims claims = new DefaultClaims();
        claims.setSubject(user.getUsername())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + expireTime));
        claims.put(AUTHORITIES, user.getAuthorities());
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

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
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 验证令牌
     *
     * @param token
     * @param username
     * @return
     */
    public Boolean validateToken(String token, String username) {
        String userName = getUsernameFromToken(token);
        return (userName.equals(username) && !isTokenExpired(token));
    }



    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        if (expiration == null) {
            return true;
        }
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String tokenHead = "Bearer ";
        if (token == null) {
            token = request.getHeader(header);
        } else if (token.contains(tokenHead)) {
            token = token.substring(tokenHead.length());
        }
        if ("".equals(token)) {
            token = null;
        }
        return token;
    }
}
