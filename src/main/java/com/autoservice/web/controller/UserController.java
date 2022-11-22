package com.autoservice.web.controller;

import com.autoservice.dto.LoginDto;
import com.autoservice.dto.RegistrationDto;
import com.autoservice.entity.User;
import com.autoservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("newUser") RegistrationDto registrationDto) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("newUser") RegistrationDto registrationDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userService.userExists(registrationDto.getEmail())) {
            model.addAttribute("message", "User already exists");
            return "registration";
        }
        userService.save(registrationDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("login") LoginDto loginDto) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("login") LoginDto loginDto, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        Optional<User> byEmail = userService.findByEmail(loginDto.getEmail());
        if (byEmail.isPresent()) {
            if (byEmail.get().getPassword().equals(loginDto.getPassword())) {
                session.setAttribute("currentUser", byEmail.get());
                return "redirect:/";
            } else {
                model.addAttribute("message", "Wrong password");
                return "login";
            }
        }
        model.addAttribute("message", "No such user");
        return "login";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/user/login";
        }
        Optional<User> byEmail = userService.findByEmail(currentUser.getEmail());
        if (byEmail.isPresent()) {
            model.addAttribute("currentUser", byEmail.get());
            return "userProfile";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/profile/addCar")
    public String addCar() {
        return "addCar";
    }

    @PostMapping("/profile/addCar")
    public String addCar(Model model) {
        return "redirect:/user/profile";
    }
}
