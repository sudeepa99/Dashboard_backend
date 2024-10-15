package com.dashboard.example.Dashboard.controller;

import com.dashboard.example.Dashboard.dto.AuthResDTO;
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

    @PostMapping("/register")
    public AuthResDTO UserRegister(@RequestBody SignupRequestDTO signupRequest) {
        return userService.registerNewUser(signupRequest);
    }

    @PostMapping("/login")
    public AuthResDTO UserLogin(@RequestBody LoginRequestDTO loginRequest) {
        return userService.loginUser(loginRequest);
    }


}



