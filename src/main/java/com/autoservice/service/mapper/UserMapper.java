package com.autoservice.service.mapper;

import com.autoservice.dto.ProfileEditDto;
import com.autoservice.dto.RegistrationDto;
import com.autoservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User convertUserDtoToUser(RegistrationDto dto) {
        return new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(), dto.getPhoneNumber());
    }

    public User editUserProfile(User user, ProfileEditDto profileEditDto) {
        user.setEmail(profileEditDto.getEmail());
        user.setPassword(profileEditDto.getPassword());
        user.setFirstName(profileEditDto.getFirstName());
        user.setLastName(profileEditDto.getLastName());
        user.setPhoneNumber(profileEditDto.getPhoneNumber());
        return user;
    }

    public ProfileEditDto prepareUserInfo(User user) {
        return new ProfileEditDto(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber());
    }
}
