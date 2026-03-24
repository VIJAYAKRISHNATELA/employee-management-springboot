package com.example.demo.controller;

import com.example.demo.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        // dummy validation
        if(username.equals("admin") && password.equals("admin123")){
            return JwtUtil.generateToken(username);
        }

        throw new RuntimeException("Invalid credentials");
    }
}