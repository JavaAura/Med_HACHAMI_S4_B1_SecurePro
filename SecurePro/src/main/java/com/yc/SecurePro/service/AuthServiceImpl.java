package com.yc.SecurePro.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import com.yc.SecurePro.dto.req.RegisterRequestDto;
import com.yc.SecurePro.entity.enums.Role;
import com.yc.SecurePro.exception.ValidationException;
import com.yc.SecurePro.service.Interfaces.IAuthService;
@Service
public class AuthServiceImpl implements IAuthService {

    private JdbcUserDetailsManager jdbcUserDetailsManager;

    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(JdbcUserDetailsManager jdbcUserDetailsManager , PasswordEncoder passwordEncoder){
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void registerUser(RegisterRequestDto request) {
        if (jdbcUserDetailsManager.userExists(request.getUserName())) {
            throw new IllegalArgumentException("User already exists!");
        }

        if(request.getPassword().equals(request.getConfirmPassword())){
            throw new ValidationException("Password and Confirm Password must match.");
        }

        User.UserBuilder userBuilder = User.builder()
                                           .username(request.getUserName())
                                           .password(passwordEncoder.encode(request.getPassword()))
                                           .roles(Role.USER.name());

        jdbcUserDetailsManager.createUser(userBuilder.build());

        
    }
}
