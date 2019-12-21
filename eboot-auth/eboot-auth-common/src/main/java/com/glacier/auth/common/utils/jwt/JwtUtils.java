package com.glacier.auth.common.utils.jwt;

import com.glacier.auth.common.constant.CommonConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-20 15:55
 */
public class JwtUtils {

    private JwtUtils() {
    }

    /**
     * 生成令牌
     *
     * @param ijwtInfo
     * @return 令牌
     */
    public static String generateToken(IjwtInfo ijwtInfo, String secret, long expireTime) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(ijwtInfo.getUsername())
                .claim(CommonConstants.JWT_KEY_USER_ID, ijwtInfo.getUserId())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + expireTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 刷新令牌
     *
     * @param token
     * @return
     */
    public static String refreshToken(String token, String secret, long expireTime) throws Exception {
        Claims claims = parserToken(token, secret);
        return Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims parserToken(String token, String secret) throws Exception {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static IjwtInfo getInfoFromToken(String token, String secret) throws Exception {
        Claims claims = parserToken(token, secret);
        return new JwtInfo(claims.getSubject(), String.valueOf(claims.get(CommonConstants.JWT_KEY_USER_ID)));
    }
}
