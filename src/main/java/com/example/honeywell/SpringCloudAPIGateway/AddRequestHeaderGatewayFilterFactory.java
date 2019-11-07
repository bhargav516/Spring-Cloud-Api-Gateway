package com.example.honeywell.SpringCloudAPIGateway;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Consumer;

public class AddRequestHeaderGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override public GatewayFilter apply(NameValueConfig config) {
        return ((exchange, chain) -> {
            HttpServletRequest request = (HttpServletRequest) exchange.getRequest();
                    //.mutate()
                    //.header("x-jwt-assertion", jwt)
                    //.build();
            KeycloakSecurityContext session = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
            if (session != null)
                ("", BEARER + session.getTokenString());
            }
            ServerWebExchange exchange1 = exchange.mutate().request((Consumer<ServerHttpRequest.Builder>) request).build();
            return chain.filter(exchange1);
        });
    }
}
