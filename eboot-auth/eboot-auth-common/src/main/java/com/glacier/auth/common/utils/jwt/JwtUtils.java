package com.glacier.auth.common.utils.jwt;

import com.glacier.auth.common.constant.CommonConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-20 15:55
 */
@Setter
@Getter
@AllArgsConstructor
public class JwtUtils {

    private String secret;
    private long expireTime;

    /**
     * 生成令牌
     *
     * @param ijwtInfo
     * @return 令牌
     */
    public String generateToken(IjwtInfo ijwtInfo) {
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
    public String refreshToken(String token) throws Exception {
        Claims claims = parserToken(token);
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
    public Claims parserToken(String token) throws Exception {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public IjwtInfo getInfoFromToken(String token) throws Exception {
        Claims claims = parserToken(token);
        return new JwtInfo(claims.getSubject(), String.valueOf(claims.get(CommonConstants.JWT_KEY_USER_ID)));
    }
}
