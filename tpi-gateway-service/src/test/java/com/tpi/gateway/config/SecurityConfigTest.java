package com.tpi.gateway.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    @Test
    void testExtractRolesFromRealmAccessWithRoles() {
        SecurityConfig config = new SecurityConfig();

        // Simula un JWT con realm_access y roles
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("realm_access", Map.of("roles", List.of("ADMIN", "EMPLEADO")))
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        // Usá reflexión si es private, o mejor: hacelo protected/package-private
        Collection<? extends GrantedAuthority> authorities =
                invokeExtractRoles(config, jwt);

        assertTrue(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        assertTrue(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_EMPLEADO")));
    }

    @Test
    void testExtractRolesFromRealmAccessEmptyRoles() {
        SecurityConfig config = new SecurityConfig();

        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("realm_access", Map.of("roles", List.of()))
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        Collection<? extends GrantedAuthority> authorities =
                invokeExtractRoles(config, jwt);

        assertTrue(authorities.isEmpty());
    }

    @Test
    void testExtractRolesFromRealmAccessNoRealmAccess() {
        SecurityConfig config = new SecurityConfig();

        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("other_claim", "value")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        Collection<? extends GrantedAuthority> authorities =
                invokeExtractRoles(config, jwt);

        assertTrue(authorities.isEmpty());
    }

    // Método de ayuda: invoca por reflexión extractRolesFromRealmAccess
    @SuppressWarnings("unchecked")
    private Collection<GrantedAuthority> invokeExtractRoles(SecurityConfig config, Jwt jwt) {
        try {
            var method = SecurityConfig.class.getDeclaredMethod("extractRolesFromRealmAccess", Jwt.class);
            method.setAccessible(true);
            return (Collection<GrantedAuthority>) method.invoke(config, jwt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
