package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterRequest {
    @NotBlank private String username;
    @NotBlank private String password;
    @Pattern(regexp = "ADMIN|USER", message = "Role must be ADMIN or USER")
    private String role;
    // getters + setters
    public String getUsername() { return username; }
    public void setUsername(String u) { this.username = u; }
    public String getPassword() { return password; }
    public void setPassword(String p) { this.password = p; }
    public String getRole() { return role; }
    public void setRole(String r) { this.role = r; }
}