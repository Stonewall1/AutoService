package com.autoservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginDto {
    @NotBlank(message = "Field cant be empty")
    @Pattern(regexp = "\\b[\\w.]+@\\w+\\.\\w{2,3}" , message = "Wrong email pattern")
    private String email;
    @NotBlank(message = "Field cant be empty")
    private String password;

    public LoginDto() {
    }

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "LoginDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
