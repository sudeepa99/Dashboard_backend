package com.dashboard.example.Dashboard.controller;

import com.dashboard.example.Dashboard.dto.GetUserListDTO;
import com.dashboard.example.Dashboard.entity.User;
import com.dashboard.example.Dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    //User List
    @GetMapping("/")
    public GetUserListDTO<?> getAllUsers() {
        return userService.getAllUsers();
    }
}
