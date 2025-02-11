package com.spring_cloud.study.client.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;

@Slf4j
@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    @Value("${service.jwt.secret-key}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // 인증이 필요 없는 경로 예외 처리
        if (path.equals("/auth/signIn") || path.equals("/auth/signUp")) {
            return chain.filter(exchange);
        }

        // Authorization 헤더에서 토큰 추출
        String token = extractToken(exchange);
        log.info("token : {}", token);

        if (token == null || !validateToken(token, exchange)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 요청에 헤더 추가
        Claims claims = parseToken(token);
        if (claims != null) {
            String userId = claims.get("user_id").toString();
            String role = claims.get("role").toString();

            // 헤더 추가를 위한 요청 수정
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User-Id", userId)
                    .header("X-Role", role)
                    .build();

            // 변경된 요청으로 새로운 exchange 생성
            ServerWebExchange mutatedExchange = exchange.mutate()
                    .request(mutatedRequest)
                    .build();

            log.info("Request after mutation: {}", mutatedRequest.getURI());
            return chain.filter(mutatedExchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    // 토큰에서 Bearer 제거 및 반환
    private String extractToken(ServerWebExchange exchange) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    // 토큰 유효성 검증 및 클레임 반환
    private Claims parseToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token);

            log.info("Jwt payload: {}", claimsJws.getPayload().toString());
            return claimsJws.getPayload();
        } catch (Exception e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return null;
        }
    }

    private Boolean validateToken(String token, ServerWebExchange exchange) {
        return parseToken(token) != null;
    }
}

