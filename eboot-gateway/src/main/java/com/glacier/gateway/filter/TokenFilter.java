package com.glacier.gateway.filter;

import com.glacier.core.http.HttpResult;
import com.glacier.gateway.utils.JwtTokenUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Setter
@Component
@ConfigurationProperties(prefix = "gateway.token")
public class TokenFilter implements GlobalFilter, Ordered {

    private List<String> ignoredPath;
    private String header;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst(header);
        if (!this.ignoreMath(request.getURI().getPath())) {
            HttpResult httpResult = jwtTokenUtils.validateToken(token);
            if (httpResult.getCode() != HttpStatus.OK.value()) {
                log.info("无效TOKEN: {}", token);
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        }
        // 刷新token
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 验证是否是忽略验证
     * @param path
     * @return
     */
    private boolean ignoreMath(String path) {
        boolean ignore = false;
        if (ignoredPath !=null && !ignoredPath.isEmpty()) {
            for (String prifx : ignoredPath) {
                if (path.startsWith(prifx)) {
                    ignore = true;
                    break;
                }
            }
        }
        return ignore;
    }
}