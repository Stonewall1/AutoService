package com.autoservice.dto;

import com.autoservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @Lombok only while in development
 */
public class OrderDto {
    private User carOwner;
    private long carID;
    private long masterID;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime repairStart;
    private LocalDateTime orderCreation;
}
