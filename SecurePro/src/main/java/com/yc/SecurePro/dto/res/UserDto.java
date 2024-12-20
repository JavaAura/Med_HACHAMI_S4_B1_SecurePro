package com.yc.SecurePro.dto.res;

import java.util.List;


public class UserDto {
     private String username;
     private List<String> roles;

     public UserDto(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    // Setter for roles
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
