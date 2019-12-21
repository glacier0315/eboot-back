package com.glacier.auth.common.utils.jwt;

import com.glacier.auth.common.constant.CommonConstants;
import io.jsonwebtoken.Claims;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-20 17:05
 */
public class JwtUtilsTest {

    public static String userId = "admin123";
    public static String username = "admin";
    public static String secret = "abcdef";
    public static String old_token = "";
    public static long expireTime = 30 * 60 * 1000L;

    @BeforeClass
    public static void beforeClass() throws Exception {
        IjwtInfo ijwtInfo = new JwtInfo(username, userId);
        old_token = JwtUtils.generateToken(ijwtInfo, secret, 60 * 1000L);
        System.out.println("old_token:\t" + old_token);
    }

    @Test
    public void generateToken() throws Exception {
        IjwtInfo ijwtInfo = new JwtInfo(username, userId);
        String token = JwtUtils.generateToken(ijwtInfo, secret, expireTime);
        System.out.println("token:\t" + token);

        // 解析
        Claims claims = JwtUtils.parserToken(token, secret);
        System.out.println(claims.getSubject());
        System.out.println(claims.get(CommonConstants.JWT_KEY_USER_ID));
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
    }

    @Test
    public void refreshToken() throws Exception {
        String refreshToken = JwtUtils.refreshToken(old_token, secret, expireTime);
        System.out.println("refreshToken:\t" + refreshToken);

        // 解析
        Claims claims = JwtUtils.parserToken(refreshToken, secret);
        System.out.println(claims.getSubject());
        System.out.println(claims.get(CommonConstants.JWT_KEY_USER_ID));
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
    }

    @Test
    public void getInfoFromToken() throws Exception {
        // 解析
        IjwtInfo infoFromToken = JwtUtils.getInfoFromToken(old_token, secret);
        System.out.println(infoFromToken);
    }
}