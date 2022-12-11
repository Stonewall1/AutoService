package com.autoservice.dto;

import com.autoservice.entity.Car;
import com.autoservice.entity.Master;
import com.autoservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @Lombok only while in development
 */
public class OrderDto {
    private User carOwner;
    private Car car;
    private Master master;
    @NotBlank(message = "Field cant be empty")
    private LocalDateTime repairStart;
    private LocalDateTime orderCreation;
}
