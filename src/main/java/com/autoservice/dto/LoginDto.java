package com.autoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "Field cant be empty")
    @Pattern(regexp = "\\b[\\w.]+@\\w+\\.\\w{2,3}", message = "Wrong email pattern")
    private String email;
    @NotBlank(message = "Field cant be empty")
    private String password;
}
