package com.yc.SecurePro.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@Profile("test")
@EnableWebSecurity
public class SecurityTestConfig {

    @Bean
    public void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .anyRequest().permitAll()
        .and()
        .csrf().disable();

    }
}
