package com.autoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    @NotBlank(message = "Field cant be empty")
    private String brand;
    @NotBlank(message = "Field cant be empty")
    private String model;
    @NotBlank(message = "Field cant be empty")
    private String colour;
    @NotBlank(message = "Field cant be empty")
    private String imageUrl;
    private double engineVolume;
    @NotBlank(message = "Field cant be empty")
    private String VIN;
}
