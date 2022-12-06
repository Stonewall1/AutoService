package com.autoservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ProfileEditDto {
    @NotBlank(message = "Field cant be empty")
    @Pattern(regexp = "\\b[\\w.]+@\\w+\\.\\w{2,3}", message = "Wrong email pattern")
    private String email;
    @NotBlank(message = "Field cant be empty")
    private String password;
    @NotBlank(message = "Field cant be empty")
    private String firstName;
    @NotBlank(message = "Field cant be empty")
    private String lastName;
    @NotBlank(message = "Field cant be empty")
    @Pattern(regexp = "\\+\\d{12}", message = "Wrong phone number pattern")
    private String phoneNumber;

    public ProfileEditDto() {
    }

    public ProfileEditDto(String email, String password, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ProfileEditDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
