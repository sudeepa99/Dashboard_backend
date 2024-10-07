package com.dashboard.example.Dashboard.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user")
public class User {

    @PostMapping("/register")
    public String UserRegister(@RequestBody User user) {
        return "User Registered";
    }

    @PostMapping("/login")
    public String UserLogin(@RequestBody User user) {
        return "User Logged in";
    }

    @GetMapping("/getUsers")
    public String getUsers() {
        return "User List";
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser(@RequestBody User user) {
        return "User Updated";
    }
    @PatchMapping("/updateUserPart/{id}")
    public String updateUserPart(@RequestBody User user) {
        return "User Partially Updated";
    }

    @DeleteMapping("/deleteUser/{/id}")
    public String deleteUser(@RequestBody User user) {
        return "User Deleted";
    }








}
