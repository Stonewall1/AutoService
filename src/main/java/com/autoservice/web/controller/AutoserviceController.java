package com.autoservice.web.controller;

import com.autoservice.entity.User;
import com.autoservice.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class AutoserviceController {
    private final AdminService adminService;

    public AutoserviceController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String homepage(Model model, HttpSession session) {
        if(!adminService.isCreated()){
            adminService.createAdmin();
        }
        model.addAttribute("currentUser", (User) session.getAttribute("currentUser"));
        return "homepage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
