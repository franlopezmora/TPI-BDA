package com.tpi.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/notificaciones/**").hasAnyRole("ADMIN", "EMPLEADO")
                        .pathMatchers("/pruebas/**").hasRole("EMPLEADO")
                        .pathMatchers("/admin/**").hasRole("ADMIN")
                        .pathMatchers("/vehiculos/**").hasRole("USUARIO_VEHICULO")
                        .pathMatchers("/reportes/**").hasAnyRole("ADMIN", "EMPLEADO")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt())
                .build();
    }
}

