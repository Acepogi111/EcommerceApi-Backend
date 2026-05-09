package com.ws101.ada.fajardo.EcommerceApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Para gumana POST sa Thunder Client
            .authorizeHttpRequests(auth -> auth
                // === PUBLIC ENDPOINTS - DAPAT MAUNA LAHAT ===
                .requestMatchers("/api/v1/auth/register").permitAll()
                .requestMatchers("/login", "/error").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
                
                // === PROTECTED ENDPOINTS ===
                .requestMatchers(HttpMethod.POST, "/api/products").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/products/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/products/**").authenticated()
                
                // === THE REST - DAPAT PINAKA HULI LAGI ===
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/api/products", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
