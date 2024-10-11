package com.dashboard.example.Dashboard.service;

import com.dashboard.example.Dashboard.entity.User;
import com.dashboard.example.Dashboard.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
