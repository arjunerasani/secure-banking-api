package com.securebank.secure_banking_api.config;

import com.securebank.secure_banking_api.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    // this method checks credentials -> telling spring security that when someone tries to log in, use
    // the CustomUserDetailsService to look up the user, and the PasswordEncoder to check the password.

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // this is essentially the coordinator that calls provides such as the DAO whenever an authentication attempt
    // is made.

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

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
                        .loginProcessingUrl("/login") // POST endpoint that spring security handles automatically
                        .defaultSuccessUrl("/dashboard", true) // after login users get
                        // directed here
                        .permitAll() // allows every user to see the login page
                        .failureUrl("/login?error")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                ); // directs logged out users back to the login page

        return http.build();
    }
}
