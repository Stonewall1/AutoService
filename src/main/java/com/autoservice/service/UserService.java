package com.autoservice.service;

import com.autoservice.dto.ProfileEditDto;
import com.autoservice.dto.RegistrationDto;
import com.autoservice.entity.Car;
import com.autoservice.entity.User;
import com.autoservice.exception.UserNotFoundException;
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

    public User update(User u) {
        return userRepository.save(u);
    }

    public boolean userExists(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.isPresent();
    }

    private User mapToUser(RegistrationDto dto) {
        return userMapper.convertUserDtoToUser(dto);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public User findById(long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else throw new UserNotFoundException();
    }

    public User editProfileInfo(User user, ProfileEditDto profileEditDto) {
        User editedUser = userMapper.editUserProfile(user, profileEditDto);
        update(editedUser);
        return editedUser;
    }

    public ProfileEditDto prepareUserInfo(User user) {
        return userMapper.prepareUserInfo(user);
    }

    public void addInfoAboutCar(long id, Car car) {
        User user = findById(id);
        user.getCars().add(car);
        update(user);
    }
}
