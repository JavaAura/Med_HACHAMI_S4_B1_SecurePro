package com.yc.SecurePro.dto.req;

import java.util.List;

public class UpdateUserRoleRequestDto {
    private  List<String> roles;
    private String username;

    public UpdateUserRoleRequestDto() {
    }

    public UpdateUserRoleRequestDto(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
