package com.yc.SecurePro.service.Interfaces;

import com.yc.SecurePro.dto.req.RegisterRequestDto;
import com.yc.SecurePro.entity.AppRole;
import com.yc.SecurePro.entity.AppUser;



public interface IAccountService {
    AppUser register(RegisterRequestDto registerRequestDto);
    AppRole addNewRole(String role);
    void addRoleToUser(String userName, String role);
    void removeRoleToUser(String userName, String role);
    AppUser loadUserByUsername(String userName);

}
