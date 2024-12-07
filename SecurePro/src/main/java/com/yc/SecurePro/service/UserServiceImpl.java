package com.yc.SecurePro.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import com.yc.SecurePro.dao.interfaces.IUserDao;
import com.yc.SecurePro.dto.req.UpdateUserRoleRequestDto;
import com.yc.SecurePro.dto.res.UserDto;
import com.yc.SecurePro.service.Interfaces.IUserService;


@Service
public class UserServiceImpl implements IUserService {

    private JdbcUserDetailsManager jdbcUserDetailsManager;

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);


    private final IUserDao userDao;

    public UserServiceImpl(IUserDao userDao, JdbcUserDetailsManager jdbcUserDetailsManager){
        this.userDao = userDao;
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    }

    @Override
    public List<UserDto> getAllUsers() {
       return  userDao.findAllUsers();
    }

    @Override
    public void updateUserRoles(UpdateUserRoleRequestDto updateUserRoleRequestDto) {
        log.info("Username"+updateUserRoleRequestDto.getUsername());

        if (!jdbcUserDetailsManager.userExists(updateUserRoleRequestDto.getUsername())) {
            throw new IllegalArgumentException("User  not found with ");
        }

        userDao.updateUserRoles(updateUserRoleRequestDto.getUsername(), updateUserRoleRequestDto.getRoles());
    }
    
}
