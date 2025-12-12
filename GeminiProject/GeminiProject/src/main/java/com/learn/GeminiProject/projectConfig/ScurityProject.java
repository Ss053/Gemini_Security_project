package com.learn.GeminiProject.projectConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ScurityProject {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**","/").permitAll()
                        .anyRequest().authenticated()// All requests need authentication
                )
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .httpBasic(Customizer.withDefaults()); // Enable Basic Auth

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User.builder()
                .username("admin")
                .password(encoder.encode("admin123"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder().upgradeEncoding()
    }
}
