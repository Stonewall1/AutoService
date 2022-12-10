package com.autoservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MasterDto {
    @NotBlank(message = "Field cant be empty")
    private String firstName;
    @NotBlank(message = "Field cant be empty")
    private String lastName;
    @NotBlank(message = "Field cant be empty")
    @Pattern(regexp = "\\+\\d{12}", message = "Wrong phone number pattern")
    private String phoneNumber;

    public MasterDto() {
    }

    public MasterDto(String firstName, String lastName, String phoneNumber) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
