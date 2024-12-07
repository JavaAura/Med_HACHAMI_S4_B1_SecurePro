package com.yc.SecurePro.dao.interfaces;

import java.util.List;

import com.yc.SecurePro.dto.res.UserDto;


public interface IUserDao {
    List<UserDto> findAllUsers();
    void updateUserRoles(String username, List<String> roles);
    // String getUsernameById(Long userId);
}
