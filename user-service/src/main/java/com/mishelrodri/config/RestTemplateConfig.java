package com.mishelrodri.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

//    @Bean
//    @LoadBalanced// 👈 Necesario para que se integre con Eureka
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restBuilder) {
        return restBuilder
                // interceptor que añade automáticamente “Bearer <token>”
                .additionalInterceptors((request, body, execution) -> {
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    if (auth != null && auth.getPrincipal() instanceof Jwt) {
                        String token = ((Jwt) auth.getPrincipal()).getTokenValue();
                        request.getHeaders().setBearerAuth(token);
                    }
                    return execution.execute(request, body);
                })
                .build();
    }
}
