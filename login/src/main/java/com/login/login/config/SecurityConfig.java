package com.login.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // private final JpaUserDetailsservice jpaUserDetailservice;

    // public SecurityConfig(JpaUserDetailsservice jpaUserDetailsservice) {
    // this.jpaUserDetailsservice = jpaUserDetailsservice;
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth

                .requestMatchers("/").permitAll()
                .requestMatchers("/register").permitAll()
                // .requestMatchers(HttpMethod.POST, "/register-account").permitAll()
                .requestMatchers("/product/**").permitAll()
                .requestMatchers("/register-account").permitAll()
                .requestMatchers("/order").authenticated()
        // ).anyRequest().authenticated()
        )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}