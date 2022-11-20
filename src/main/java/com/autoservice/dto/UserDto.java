package com.autoservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserDto {
    @NotBlank(message = "Field cant be empty")
    @Pattern(regexp = "\\b[\\w.]+@\\w+\\.\\w{2,3}" , message = "Wrong email pattern")
    private String email;
    @NotBlank(message = "Field cant be empty")
    private String password;
    @NotBlank(message = "Field cant be empty")
    private String firstName;
    @NotBlank(message = "Field cant be empty")
    private String lastName;

    public UserDto() {
    }

    public UserDto(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
