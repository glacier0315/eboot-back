package com.glacier.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description  token过滤器
 * @date 2019-12-11 12:38
 */
@Slf4j
@Component
@ConfigurationProperties(prefix="gateway.token")
public class TokenFilter implements GlobalFilter, Ordered {

    private List<String> ignoredPath;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst("token");
        log.info("ignoredPath: {}", ignoredPath);
        log.info("path: {}", request.getURI().getPath());
        log.info("token: {}", token);
        if (!ignoredPath.contains(request.getURI().getPath())) {
            if (token == null || token.isEmpty()) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public void setIgnoredPath(List<String> ignoredPath) {
        this.ignoredPath = ignoredPath;
    }
}