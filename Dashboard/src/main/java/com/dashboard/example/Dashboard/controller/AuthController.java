package com.dashboard.example.Dashboard.controller;

import com.dashboard.example.Dashboard.dto.LoginRequestDTO;
import com.dashboard.example.Dashboard.dto.SignupRequestDTO;
import com.dashboard.example.Dashboard.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    AuthService userService;
    @Autowired
    private LoginRequestDTO loginRequestDTO;

    @PostMapping("/register")
    public ResponseEntity<String> UserRegister(@RequestBody SignupRequestDTO signupRequest) {
        userService.registerNewUser(signupRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> UserLogin(@RequestBody LoginRequestDTO loginRequest ) {
        String tokens = userService.login(loginRequest.getEmail() , loginRequest.getPassword());
        return ResponseEntity.ok(tokens);
    }

//    @GetMapping("/users")
//    public String getUsers() {
//        return "User List";
//    }
//
//    @PutMapping("/users/{id}")
//    public String updateUser(@RequestBody AuthController user) {
//        return "User Updated";
//    }
//    @PatchMapping("/users/{id}")
//    public String updateUserPart(@RequestBody AuthController user) {
//        return "User Partially Updated";
//    }
//
//    @DeleteMapping("/users/{id}")
//    public String deleteUser(@RequestBody AuthController user) {
//        return "User Deleted";
//    }


}




