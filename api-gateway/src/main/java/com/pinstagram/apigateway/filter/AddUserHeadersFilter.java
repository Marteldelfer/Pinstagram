package com.pinstagram.apigateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class AddUserHeadersFilter implements GlobalFilter {

    private final WebClient webClient;

    public AddUserHeadersFilter(
            WebClient.Builder webClientBuilder,
            @Value("${auth.service.url:}") String authServiceUrl
    ) {
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (token == null) {
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User-Name", "unknown")
                    .header("X-User-Username", "unknown")
                    .header("X-User-Id", "unknown")
                    .header("X-User-Role", "unknown")
                    .build();
            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
            return chain.filter(mutatedExchange);
        }

        return webClient.get()
                .uri("/claims")
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .onStatus(HttpStatus.UNAUTHORIZED::equals, response -> Mono.empty())
                .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {})
                .flatMap(claims -> {
                    ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                            .header("X-User-Name", claims.getOrDefault("name", "unknown"))
                            .header("X-User-Username", claims.getOrDefault("username", "unknown"))
                            .header("X-User-Id", claims.getOrDefault("id", "unknown"))
                            .header("X-User-Role", claims.getOrDefault("role", "unknown"))
                            .build();
                    ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
                    return chain.filter(mutatedExchange);
                });
    }

}
