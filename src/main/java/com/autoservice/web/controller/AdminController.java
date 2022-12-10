package com.autoservice.web.controller;

import com.autoservice.dto.AdminLoginDto;
import com.autoservice.dto.MasterDto;
import com.autoservice.entity.Admin;
import com.autoservice.service.AdminService;
import com.autoservice.service.MasterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final MasterService masterService;

    public AdminController(AdminService adminService, MasterService masterService) {
        this.adminService = adminService;
        this.masterService = masterService;
    }


    @GetMapping("/login")
    public String adminLogin(@ModelAttribute("adminLoginDto") AdminLoginDto adminLoginDto) {
        return "admin/login";
    }

    @PostMapping("/login")
    public String adminLogin(@Valid @ModelAttribute("adminLoginDto") AdminLoginDto adminLoginDto, BindingResult bindingResult, HttpSession session, Model model) {
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

    @GetMapping("/profile")
    public String adminProfile() {
        return "admin/adminProfile";
    }

    @GetMapping("/profile/addMaster")
    public String addMaster(@ModelAttribute("newMaster") MasterDto masterDto) {
        return "/admin/addMaster";
    }

    @PostMapping("/profile/addMaster")
    public String addMaster(@Valid @ModelAttribute("newMaster") MasterDto masterDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addMaster";
        }
        if (masterService.masterExists(masterDto.getPhoneNumber())) {
            model.addAttribute("message", "Master already exists");
            return "admin/addMaster";
        }
        masterService.save(masterDto);
        return "redirect:/admin/profile";
    }
}
