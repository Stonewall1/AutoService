package com.autoservice.service.mapper;

import com.autoservice.dto.UserDto;
import com.autoservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User convertUserDtoToUser(UserDto dto){
        return new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword());
    }
}
