package com.glacier.auth.server.controller;

import com.glacier.auth.common.utils.jwt.IjwtInfo;
import com.glacier.auth.common.utils.jwt.JwtInfo;
import com.glacier.auth.common.utils.jwt.JwtUtils;
import com.glacier.common.core.http.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-23 09:42
 */
@Slf4j
@RestController
@RequestMapping("token")
public class TokenController {
    @Resource
    private JwtUtils jwtUtils;

    /**
     * 生成
     * @param jwtInfo
     * @return
     */
    @PostMapping(value = "create")
    public HttpResult<String> create(@RequestBody JwtInfo jwtInfo) {
        log.info(jwtInfo.getUsername() + " require logging...");
        String token = jwtUtils.generateToken(jwtInfo);
        return HttpResult.ok(token);
    }

    /**
     * 刷新
     * @param token
     * @return
     * @throws Exception
     */
    @PostMapping(value = "refresh")
    public HttpResult<String> refresh(String token) {
        String refreshedToken = null;
        try {
            refreshedToken = jwtUtils.refreshToken(token);
        } catch (Exception e) {
            log.error("刷新token出现异常", e);
            return HttpResult.error("刷新token出现异常");
        }
        return HttpResult.ok(refreshedToken);
    }

    /**
     * 校验
     * @param token
     * @return
     * @throws Exception
     */
    @PostMapping(value = "verify")
    public HttpResult<String> verify(String token) {
        IjwtInfo ijwtInfo = null;
        try {
            ijwtInfo = jwtUtils.getInfoFromToken(token);
        } catch (Exception e) {
            log.error("解析token出现异常", e);
        }
        if (ijwtInfo == null
                || ijwtInfo.getUserId() == null || ijwtInfo.getUserId().isEmpty()
                || ijwtInfo.getUsername() == null || ijwtInfo.getUsername().isEmpty()) {
            return HttpResult.error("校验不通过");
        }
        return HttpResult.ok("校验通过", null);
    }
}
