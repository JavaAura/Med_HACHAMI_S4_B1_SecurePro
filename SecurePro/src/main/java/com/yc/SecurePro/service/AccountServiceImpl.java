package com.yc.SecurePro.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.SecurePro.dto.req.RegisterRequestDto;
import com.yc.SecurePro.entity.AppRole;
import com.yc.SecurePro.entity.AppUser;
import com.yc.SecurePro.repository.AppRoleRepository;
import com.yc.SecurePro.repository.AppUserRepository;
import com.yc.SecurePro.service.Interfaces.IAccountService;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository , AppRoleRepository appRoleRepository , PasswordEncoder passwordEncoder){
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

   

    @Override
    public AppUser register(RegisterRequestDto registerRequestDto) {
        AppUser appUser = appUserRepository.findByUserName(registerRequestDto.getUserName());
        if(appUser !=null){
            throw new RuntimeException("User already exist");
        }

        if(!registerRequestDto.getPassword().equals(registerRequestDto.getConfirmPassword())){
            throw new RuntimeException("Passwords not match");
        }

        AppUser user = new AppUser();
         user.setUserId(UUID.randomUUID().toString());
         user.setUserName(registerRequestDto.getUserName());
         user.setEmail(registerRequestDto.getEmail());
         user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

         user = appUserRepository.save(user);
        return user;
    }


    @Override
    public AppRole addNewRole(String role) {
        AppRole existRole = appRoleRepository.findByRole(role);
        if(existRole != null){
            throw new RuntimeException("This role already exist");
        }

        AppRole newROle = new AppRole(role);

      return appRoleRepository.save(newROle);
    }

    @Override
    public void addRoleToUser(String userName, String role) {
        AppUser appUser = appUserRepository.findByUserName(userName);
        if (appUser == null) {
            throw new IllegalArgumentException("User with username '" + userName + "' not found");
        }
    
        AppRole appRole = appRoleRepository.findByRole(role);
        if (appRole == null) {
            throw new IllegalArgumentException("Role '" + role + "' not found");
        }


        List<AppRole> roles = appUser.getRoles();
        if (roles.contains(appRole)) {
            throw new IllegalArgumentException("User already has the role '" + role + "'");
        }
    
        roles.add(appRole);
        appUser.setRoles(roles);
    

        // appUserRepository.save(appUser);


    }

    @Override
    public void removeRoleToUser(String userName, String role) {
        AppUser appUser = appUserRepository.findByUserName(userName);
        if (appUser == null) {
            throw new IllegalArgumentException("User with username '" + userName + "' not found");
        }


        AppRole appRole = appRoleRepository.findByRole(role);
        if (appRole == null) {
            throw new IllegalArgumentException("Role '" + role + "' not found");
        }

        List<AppRole> roles = appUser.getRoles();
        if (roles.contains(appRole)) {
            throw new IllegalArgumentException("User already has the role '" + role + "'");
        }

        roles.remove(appRole);
        appUser.setRoles(roles);

        // appUserRepository.save(appUser);

    }


    @Override
    public AppUser loadUserByUsername(String userName) {
       return appUserRepository.findByUserName(userName);
    }

    
}
