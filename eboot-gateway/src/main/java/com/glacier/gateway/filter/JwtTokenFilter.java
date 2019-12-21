package com.glacier.gateway.filter;

import com.glacier.auth.common.utils.jwt.IjwtInfo;
import com.glacier.auth.common.utils.jwt.JwtUtils;
import com.glacier.gateway.config.JwtConfig;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author glacier
 * @version 1.0
 * @description token过滤器
 * @date 2019-12-11 12:38
 */
@Slf4j
@Setter
@Component
@ConfigurationProperties(prefix = "gateway.token")
public class JwtTokenFilter implements GlobalFilter, Ordered {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private JwtConfig jwtConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst(jwtConfig.getHeader());
        ServerHttpResponse response = exchange.getResponse();
        if (!jwtConfig.ignoreMath(request.getURI().getPath())) {
            if (token == null || token.trim().isEmpty()) {
                log.info("无效TOKEN: {}", token);
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();
            }
            IjwtInfo ijwtInfo = null;
            try {
                ijwtInfo = jwtUtils.getInfoFromToken(token);
            } catch (Exception e) {
                log.error("请求路径: {}, token异常, 原因 {}", request.getURI().getPath(), e);
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();
            }
            if (ijwtInfo != null && ijwtInfo.getUsername() != null && !ijwtInfo.getUsername().isEmpty()) {
                // 刷新token
                String refreshToken = null;
                try {
                    refreshToken = jwtUtils.refreshToken(token);
                } catch (Exception e) {
                    log.error("请求路径: {}, token异常, 原因 {}", request.getURI().getPath(), e);
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    return response.setComplete();
                }
                response.getHeaders().add(jwtConfig.getHeader(), refreshToken);
            } else {
                log.info("TOKEN无效,请求路径: {}", request.getURI().getPath());
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}