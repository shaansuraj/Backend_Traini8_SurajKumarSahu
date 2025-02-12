package com.traini8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the Traini8 application.
 * <p>
 * In this configuration, CSRF is disabled for simplicity (MVP) and all requests are permitted.
 * In production, we should configure proper authentication/authorization, I am keeping things simple.
 * </p>
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the HTTP security for the application.
     *
     * @param http HttpSecurity instance to configure
     * @return configured SecurityFilterChain
     * @throws Exception in case of configuration errors
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disabling CSRF for simplicity in the MVP
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
