package com.dashboard.example.Dashboard.controller;

import com.dashboard.example.Dashboard.dto.*;
import com.dashboard.example.Dashboard.entity.User;
import com.dashboard.example.Dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //User update
    @PutMapping("/{id}")
    public UserUpdateDTO<?> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO ){
        userDTO.setId(id);
        return userService.updateUser(userDTO);
    }

    //User Delete
    @DeleteMapping("/{id}")
    public UserDeleteDTO<?> deleteUser(@PathVariable int id) {
        UserDTO userDTO = UserDTO.builder().id(id).build();
        return userService.deleteUser(userDTO);

    }
}
