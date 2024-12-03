package com.yc.SecurePro.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("prod") 
@EnableWebSecurity
public class SecurityProdConfig {

     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .requestMatchers("/admin/**").hasRole("ADMIN")  
            .anyRequest().permitAll()
            .and()
            .formLogin() 
            .and()
            .httpBasic();  
        return http.build();
    }

  
}