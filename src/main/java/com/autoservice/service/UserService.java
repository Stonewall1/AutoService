package com.autoservice.service;

import com.autoservice.dto.ProfileEditDto;
import com.autoservice.dto.RegistrationDto;
import com.autoservice.entity.Car;
import com.autoservice.entity.Role;
import com.autoservice.entity.User;
import com.autoservice.exception.UserNotFoundException;
import com.autoservice.repository.UserRepository;
import com.autoservice.service.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger log = LogManager.getLogger(UserService.class);

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User save(RegistrationDto dto) {
        User user = mapToUser(dto);
        userRepository.save(user);
        log.info("User registered : " + dto.getFirstName() + " " + dto.getLastName());
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
        User user = userMapper.convertUserDtoToUser(dto);
        user.setRole(Role.CLIENT);
        return user;
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
        log.info("User profile edited" + " , user id : " + user.getId());
        return editedUser;
    }

    public ProfileEditDto prepareUserInfo(User user) {
        return userMapper.prepareUserInfo(user);
    }

    public void addInfoAboutCar(long id, Car car) {
        User user = findById(id);
        user.getCars().add(car);
        update(user);
        log.info("User car added");
    }

    public void deleteUsersCar(Car car, User user) {
        user.getCars().remove(car);
        log.info("User car deleted");
    }
}
