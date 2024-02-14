package com.example.springgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {

    private AuthFilter filter;


    public RoutesConfig(AuthFilter authFilter){
        this.filter = authFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("congressoModule", r -> r.path("/atom-congresso/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ATOM-CONGRESSO"))

                .route("fichaModule", r -> r.path("/atom-ficha/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ATOM-FICHA"))

                .build();
    }
}
