package com.tutorial.apidemo.apidemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("register")
    public String showRegisterForm() {
        return "register";
    }
}
