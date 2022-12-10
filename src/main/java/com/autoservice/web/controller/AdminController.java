package com.autoservice.web.controller;

import com.autoservice.dto.AdminLoginDto;
import com.autoservice.entity.Admin;
import com.autoservice.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/profile")
    public String adminProfile() {
        return "admin/adminProfile";
    }

    @GetMapping("/login")
    public String adminLogin(@ModelAttribute("adminLoginDto") AdminLoginDto adminLoginDto) {
        return "admin/login";
    }

    @PostMapping("/login")
    public String adminLogin(@ModelAttribute("adminLoginDto") AdminLoginDto adminLoginDto, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/login";
        }
        Optional<Admin> admin = adminService.checkCredentials(adminLoginDto);
        if (admin.isPresent()) {
            session.setAttribute("currentAdmin", admin.get());
            return "redirect:/";
        } else {
            model.addAttribute("message", "Wrong credentials");
            return "admin/login";
        }
    }
}
