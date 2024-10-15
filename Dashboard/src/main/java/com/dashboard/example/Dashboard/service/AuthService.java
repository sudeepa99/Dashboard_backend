package com.dashboard.example.Dashboard.service;

import com.dashboard.example.Dashboard.dto.AuthResDTO;
import com.dashboard.example.Dashboard.dto.LoginRequestDTO;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AuthResDTO loginUser(LoginRequestDTO loginRequestDTO) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),loginRequestDTO.getPassword())
            );
            if(authentication.isAuthenticated()){
                Optional<User> user = userRepository.findByEmail(loginRequestDTO.getEmail());
                if(user.isPresent()){
                    User savedUser = user.get();
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
                    return AuthResDTO.builder()
                            .status(true)
                            .message("Login Successfully")
                            .accessToken((String) listAccessToken.get(0))
                            .accessTokenExpireAt((Date) listAccessToken.get(1))
                            .refreshToken((String) listRefreshToken.get(0))
                            .refreshTokenExpireAt((Date) listRefreshToken.get(1))
                            .user(savedUser)
                            .build();
                }else{
                    return null;
                }
            }else{
                Optional<User> user = userRepository.findByEmail(loginRequestDTO.getEmail());
                if(user.isPresent()){
                    return AuthResDTO.builder()
                            .status(false)
                            .message("Password Is Wrong")
                            .build();
                }else{
                    return AuthResDTO.builder()
                            .status(false)
                            .message("Email Not Registered")
                            .build();
                }
            }
        }catch(Exception e){
            return AuthResDTO.builder()
                    .status(false)
                    .message("Internal Server Error")
                    .build();
        }

    }


    public AuthResDTO registerNewUser(SignupRequestDTO signupRequest) {
        Optional<User> user = userRepository.findByEmail(signupRequest.getEmail());

        if(user.isEmpty()){
            try{
                Optional<Role> userRole = roleRepository.findByName("USER");
                if(userRole.isPresent()){
                    User newUser = User.builder()
                            .firstName(signupRequest.getFirstName())
                            .lastName(signupRequest.getLastName())
                            .email(signupRequest.getEmail())
                            .password(passwordEncoder.encode(signupRequest.getPassword()))
                            .roles(Collections.singleton(userRole.get()))
                            .build();
                    userRepository.save(newUser);
                    return AuthResDTO.builder()
                            .status(true)
                            .message("Account Successfully Created")
                            .build();
                }else{
                    return AuthResDTO.builder()
                            .status(false)
                            .message("Role Not Found")
                            .build();
                }
            }catch (Exception e){
                System.out.println(e);
                return AuthResDTO.builder()
                        .status(false)
                        .message("Internal Server Error")
                        .build();
            }
        }else{
            return AuthResDTO.builder()
                    .status(false)
                    .message("Email Already Registered")
                    .build();
        }


    }

}
