package com.yc.SecurePro.service.Interfaces;

import java.util.List;

import com.yc.SecurePro.dto.req.UpdateUserRoleRequestDto;
import com.yc.SecurePro.dto.res.UserDto;

public interface IUserService {
    List<UserDto> getAllUsers();
    void updateUserRoles(UpdateUserRoleRequestDto updateUserRoleRequestDto);
}
