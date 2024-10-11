package com.dashboard.example.Dashboard.service;

import com.dashboard.example.Dashboard.dto.SignupRequestDTO;
import com.dashboard.example.Dashboard.entity.User;
import com.dashboard.example.Dashboard.repo.UserRepository;
import com.dashboard.example.Dashboard.utill.JwtToken;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtToken tokenUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Transactional
    public User  registerNewUser(SignupRequestDTO signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())){
            throw new RuntimeException("This email already exists");
        }
        User newUser = new User();
        newUser.setName(signupRequest.getName());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        return userRepository.save(newUser);

    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Incorrect password");
        }

        String accessToken = tokenUtil.createAccessToken(user.getUser_id());
        String refreshToken = tokenUtil.createRefreshToken(user.getUser_id());

        return "Access-Token :"+accessToken + " " + "Refresh-Token :" + refreshToken;
    }
}
