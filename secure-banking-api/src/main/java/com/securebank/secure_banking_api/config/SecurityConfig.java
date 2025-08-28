package com.securebank.secure_banking_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // security filter chain defines how the security works for the HTTP requests

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/css/**").permitAll() // anyone can go to /register
                        .anyRequest().authenticated() // all others need to login
                )
                .formLogin(form -> form
                        .loginPage("/login") // this tells spring to use the custom login page instead of default
                        .defaultSuccessUrl("/dashboard", true) // after login users get
                        // directed here
                        .permitAll() // allows every user to see the login page
                )
                .logout(logout -> logout.permitAll()); // makes the /logout endpoint visible to all users

        return http.build();
    }
}
