package com.yc.SecurePro.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
@EnableWebSecurity
public class SecurityTestConfig {


    private final PasswordEncoder passwordEncoder;

    public SecurityTestConfig(PasswordEncoder passwordEncoder){
      this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
      return new InMemoryUserDetailsManager(
        User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
        User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
        User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
      );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  
      http
      .formLogin()
      .and()
      .authorizeRequests()
      .anyRequest().authenticated();
       return http.build();
    }

  
}
