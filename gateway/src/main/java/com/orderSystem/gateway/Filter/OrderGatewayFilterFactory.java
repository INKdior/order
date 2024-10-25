package com.orderSystem.gateway.Filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class OrderGatewayFilterFactory extends AbstractGatewayFilterFactory<OrderGatewayFilterFactory.Config> {

    public OrderGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            // 在请求到达目标服务之前进行处理
            if (config.isLogEnabled()) {
                System.out.println("Request received: " + request.getMethod() + " " + request.getURI());
            }

            // 修改请求头
            ServerHttpRequest modifiedRequest = request.mutate()
                    .header("X-Custom-Header", "CustomValue")
                    .build();

            exchange=  exchange.mutate().request(modifiedRequest).build();
            // 菜单
            List<String> allowedRoutes = Arrays.asList("/blog", "/admin");
            String requestPath = request.getURI().getPath();

            if (allowedRoutes.stream().anyMatch(route -> requestPath.startsWith(route))) {
                // 路径在白名单中，继续处理请求
                return chain.filter(exchange);
            } else {
                // 路径不在白名单中，拒绝请求
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        };
    }

    public static class Config {
        private boolean logEnabled;

        public boolean isLogEnabled() {
            return logEnabled;
        }

        public void setLogEnabled(boolean logEnabled) {
            this.logEnabled = logEnabled;
        }
    }
}