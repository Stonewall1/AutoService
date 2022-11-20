package com.autoservice.service;

import com.autoservice.dto.RegistrationDto;
import com.autoservice.entity.User;
import com.autoservice.repository.UserRepository;
import com.autoservice.service.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User save(RegistrationDto dto) {
        User user = mapToUser(dto);
        userRepository.save(user);
        return user;
    }

    public boolean userExists(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.isPresent();
    }

    private User mapToUser(RegistrationDto dto) {
        return userMapper.convertUserDtoToUser(dto);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
