package com.yc.SecurePro.security;


import javax.sql.DataSource;

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

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     
      http
      .authorizeRequests(authz -> authz
          .requestMatchers("/api/v1/auth/**").permitAll()
          .requestMatchers("/api/admin/**").hasRole("ADMIN")  // Restrict access   to admin endpoints

          .requestMatchers("/api/user/**").hasRole("USER")  // Restrict access to user endpoints
          .anyRequest().authenticated()  // All other requests must be authenticated
      )
      .csrf(csrf -> csrf.disable())
      .formLogin(form -> form.permitAll())
      .logout(logout -> logout
          .logoutUrl("/logout")  // Define logout URL
          .logoutSuccessUrl("/login?logout")  // Redirect to login page with logout message
          .permitAll()  // Allow all users to log out
      )
      .sessionManagement(session -> session
          .maximumSessions(1)  // Limit to one active session per user
          .expiredUrl("/login?expired")  // Redirect to login page when session expires
      );
  return http.build();
  }
  

  // JdbcUserDetailsManager to manage users
  @Bean
  public JdbcUserDetailsManager jdbcUserDetailsManager() {
    return new JdbcUserDetailsManager(this.dataSource);
  }
  

  
}