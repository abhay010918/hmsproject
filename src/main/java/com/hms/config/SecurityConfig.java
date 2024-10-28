package com.hms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JWTfilter jwTfilter;

    public SecurityConfig(JWTfilter jwTfilter) {
        this.jwTfilter = jwTfilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests
                (auth -> auth.requestMatchers
                        ("/api/v1/users/login",
                                "/api/v1/users/signup",
                                "/api/v1/users/signup_propertyowner")
                        .permitAll()
                        .requestMatchers("/api/v1/country/addcountry")
                        .hasAnyRole("OWNER")
                        .anyRequest().authenticated());
         http.addFilterBefore(jwTfilter, AuthorizationFilter.class);
        return http.build();
    }
}
