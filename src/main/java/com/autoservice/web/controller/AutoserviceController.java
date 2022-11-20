package com.autoservice.web.controller;

import com.autoservice.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class AutoserviceController {

    @GetMapping
    public String homepage(Model model , HttpSession session){
        model.addAttribute("currentUser" , (User) session.getAttribute("currentUser"));
        return "homepage";
    }
}
