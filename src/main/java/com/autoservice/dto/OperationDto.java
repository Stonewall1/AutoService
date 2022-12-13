package com.autoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {
    @NotBlank(message = "Field cant be empty")
    private String operationDescription;
    @NotNull(message = "Field cant be null")
    private BigDecimal price;

}
