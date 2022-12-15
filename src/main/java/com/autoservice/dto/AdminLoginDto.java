package com.autoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginDto {
    @NotBlank(message = "Field cant be empty")
    private String username;
    @NotBlank(message = "Field cant be empty")
    private String password;
}
