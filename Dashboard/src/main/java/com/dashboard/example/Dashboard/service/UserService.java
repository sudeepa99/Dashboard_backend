package com.dashboard.example.Dashboard.service;

import com.dashboard.example.Dashboard.dto.GetUserListDTO;
import com.dashboard.example.Dashboard.entity.User;
import com.dashboard.example.Dashboard.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public GetUserListDTO<?> getAllUsers(){
        List<User> users = userRepository.findAll();
        return GetUserListDTO.builder()
                .status(true)
                .data(users)
                .build();
    }

}
