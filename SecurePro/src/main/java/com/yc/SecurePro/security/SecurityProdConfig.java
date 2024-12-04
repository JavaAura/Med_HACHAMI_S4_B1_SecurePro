package com.yc.SecurePro.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("prod")
@EnableWebSecurity
public class SecurityProdConfig {

   private final DataSource dataSource;
  private final PasswordEncoder passwordEncoder;



  public SecurityProdConfig(DataSource dataSource, PasswordEncoder passwordEncoder) {
    this.dataSource = dataSource;
    this.passwordEncoder = passwordEncoder;
  }



  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
        http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
        .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?")
        .passwordEncoder(this.passwordEncoder);
    return authenticationManagerBuilder.build();
  }

  // Configure Security Filter Chain
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
    .authorizeRequests()
        .requestMatchers("/login", "/register").permitAll()
        .anyRequest().authenticated() 
        .and()
    .formLogin()
        .permitAll()
    .and()
    .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logout")
        .permitAll()
    .and()
    .sessionManagement()
        .maximumSessions(1) 
        .expiredUrl("/login?expired"); 
    return http.build();
  }

  // JdbcUserDetailsManager to manage users
  @Bean
  public JdbcUserDetailsManager jdbcUserDetailsManager() {
    return new JdbcUserDetailsManager(this.dataSource);
  }
  

  
}