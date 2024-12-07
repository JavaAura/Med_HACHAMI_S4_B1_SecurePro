package com.yc.SecurePro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.SecurePro.dto.req.UpdateUserRoleRequestDto;
import com.yc.SecurePro.dto.res.UserDto;
import com.yc.SecurePro.service.Interfaces.IUserService;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

   @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Restricts access to ADMIN role
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/roles")
    public  ResponseEntity<String>  updateUserRoles(@RequestBody UpdateUserRoleRequestDto updateUserRoleRequestDto) {
        try {
            userService.updateUserRoles(updateUserRoleRequestDto);
            return ResponseEntity.ok("User roles updated successfully.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
}
