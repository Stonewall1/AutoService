package com.autoservice.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public String profile(){
        return "userProfile";
    }

//    @PostMapping("/login")
//    public String login(){
//
//    }
}
