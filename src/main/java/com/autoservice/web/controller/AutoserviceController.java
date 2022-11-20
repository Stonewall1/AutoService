package com.autoservice.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AutoserviceController {

    @GetMapping
    public String homepage(){
        return "homepage";
    }
}
