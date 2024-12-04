package com.yc.SecurePro.dto.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDto {
    private String userName;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterRequestDto(String userName, String email,String password,String confirmPassword){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword  = confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
