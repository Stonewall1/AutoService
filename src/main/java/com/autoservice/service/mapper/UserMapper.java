package com.autoservice.service.mapper;

import com.autoservice.dto.RegistrationDto;
import com.autoservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User convertUserDtoToUser(RegistrationDto dto){
        return new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword());
    }
}
