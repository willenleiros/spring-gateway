package com.example.springgateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        logger.info("request: {}",String.format("Host: {%s}, Uri: {%s}, Content-Type: {%s}, Method: {%s}, " +
                        "Response StatusCode: {%s}, Authorization: {%s}",
                request.getHeaders().getHost(),
                request.getURI(),
                request.getHeaders().getContentType(),
                request.getMethod().name(),
                response.getStatusCode().value(),
                request.getHeaders().get("Authorization")));
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
