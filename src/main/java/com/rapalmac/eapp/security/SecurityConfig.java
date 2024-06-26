package com.rapalmac.eapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration

public class SecurityConfig {
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, KeyclockLogoutHandler logoutHandler) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(new AntPathRequestMatcher("/associate/*"))
                                .hasRole("user")
                                .requestMatchers(new AntPathRequestMatcher("/"))
                                .permitAll()
                                .anyRequest()
                                .authenticated());

        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults()));

        http.oauth2Login(Customizer.withDefaults()).logout(lo -> lo
                .addLogoutHandler(logoutHandler)
                .logoutSuccessUrl("/"));
        return http.build();
    }
}
