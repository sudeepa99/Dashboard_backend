package com.dashboard.example.Dashboard.service;

import com.dashboard.example.Dashboard.dto.SignupRequestDTO;
import com.dashboard.example.Dashboard.entity.User;
import com.dashboard.example.Dashboard.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerNewUser(SignupRequestDTO signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())){
            throw new RuntimeException("This email already exists");
        }

        User newUser = new User();
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(signupRequest.getPassword());

        userRepository.save(newUser);

    }
}
