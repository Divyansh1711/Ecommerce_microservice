package com.microService_project.paymentService.securityconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authrorizeRequests-> authrorizeRequests
                        .requestMatchers("/payments/**")
                        .hasAuthority("SCOPE internal")
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer((OAuth2ResourceServerConfigurer::jwt));

        return http.build();
    }
}
