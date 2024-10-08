package com.dashboard.example.Dashboard.controller;

import com.dashboard.example.Dashboard.dto.SignupRequestDTO;
import com.dashboard.example.Dashboard.repo.UserRepository;
import com.dashboard.example.Dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/auth")
public class User {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> UserRegister(@RequestBody SignupRequestDTO signupRequest) {
        userService.registerNewUser(signupRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public String UserLogin(@RequestBody User user) {
        return "User Logged in";
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
