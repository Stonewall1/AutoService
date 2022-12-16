package com.autoservice.web.controller;

import com.autoservice.entity.Admin;
import com.autoservice.entity.User;
import com.autoservice.service.AdminService;
import com.autoservice.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class AutoserviceController {
    private final AdminService adminService;
    private final ReviewService reviewService;

    public AutoserviceController(AdminService adminService, ReviewService reviewService) {
        this.adminService = adminService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String homepage(Model model, HttpSession session) {
        if (!adminService.isCreated()) {
            adminService.createAdmin();
        }
        model.addAttribute("currentUser", (User) session.getAttribute("currentUser"));
        model.addAttribute("currentAdmin", (Admin) session.getAttribute("currentAdmin"));
        model.addAttribute("reviewsCount", reviewService.findAll().size());
//        model.addAttribute("averageRating", reviewService.countAverageRating());
        return "homepage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
