package com.dashboard.example.Dashboard.controller;

import com.dashboard.example.Dashboard.dto.LoginRequestDTO;
import com.dashboard.example.Dashboard.dto.SignupRequestDTO;
import com.dashboard.example.Dashboard.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/auth")
public class User {

    @Autowired
    private AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<String> UserRegister(@RequestBody SignupRequestDTO signupRequest) {
        userService.registerNewUser(signupRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> UserLogin(@RequestBody LoginRequestDTO loginRequest) {
        String token = userService.
    }

    @GetMapping("/users")
    public String getUsers() {
        return "User List";
    }

    @PutMapping("/users/{id}")
    public String updateUser(@RequestBody User user) {
        return "User Updated";
    }
    @PatchMapping("/users/{id}")
    public String updateUserPart(@RequestBody User user) {
        return "User Partially Updated";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@RequestBody User user) {
        return "User Deleted";
    }
}
