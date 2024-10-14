package com.dashboard.example.Dashboard.service;

import com.dashboard.example.Dashboard.dto.SignupRequestDTO;
import com.dashboard.example.Dashboard.entity.RefreshToken;
import com.dashboard.example.Dashboard.entity.Role;
import com.dashboard.example.Dashboard.entity.User;
import com.dashboard.example.Dashboard.repo.RefreshTokenRepository;
import com.dashboard.example.Dashboard.repo.RoleRepository;
import com.dashboard.example.Dashboard.repo.UserRepository;
import com.dashboard.example.Dashboard.utill.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTToken jwtToken;

    public String login(String email, String password) {
        Optional<User> user=userRepository.findByEmail(email);

        if (!passwordEncoder.matches(password, user.get().getPassword())){
            throw new RuntimeException("Incorrect password");
        }
        Optional<User> userS = userRepository.findByEmail(email);
        User savedUser = userS.get();
        List<?> listRefreshToken = jwtToken.generateRefreshToken(savedUser);
        List<?> listAccessToken = jwtToken.generateAccessToken(savedUser);
        RefreshToken refreshToken = RefreshToken.builder()
                .token((String) listRefreshToken.get(0))
                .expires_at((Date) listRefreshToken.get(1))
                .user(savedUser)
                .build();
        RefreshToken token = refreshTokenRepository.save(refreshToken);
        savedUser.setToken(token);
        userRepository.save(savedUser);
        String accessToken = jwtToken.createAccessToken(userS.get());
        String refreshTokens = jwtToken.createRefreshToken(userS.get());

        return "Access-Token :"+accessToken + " " + "Refresh-Token :" + refreshTokens;
    }
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerNewUser(SignupRequestDTO signupRequest) {
        Optional<Role> userRole = roleRepository.findByName("USER");

        if (userRepository.existsByEmail(signupRequest.getEmail())){
            throw new RuntimeException("This email is already exit");
        }
        User newUser = new User();
        newUser.setFirstName(signupRequest.getFirstName());
        newUser.setLastName(signupRequest.getLastName());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        newUser.setRoles(Collections.singleton(userRole.get()));
        newUser.setUpdated_at(new Date());
        newUser.setCreated_at(new Date());
        userRepository.save(newUser);

    }

}
