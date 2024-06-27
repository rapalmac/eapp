package com.rapalmac.eapp.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JwtAuthorityConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @SuppressWarnings("unchecked")
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Map<String, Object> realmAccess = source.getClaim("realm_access");
        var optional = Optional.ofNullable((List<String>) realmAccess.get("roles"));

        var authorities = optional
                .map(roles ->
                        roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).toList())
                .orElse(List.of());

        return new JwtAuthenticationToken(source, authorities);
    }
}
