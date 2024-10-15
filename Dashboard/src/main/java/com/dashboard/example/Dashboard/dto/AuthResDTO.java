package com.dashboard.example.Dashboard.dto;

import com.dashboard.example.Dashboard.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AuthResDTO {
    private Boolean status;
    private String accessToken;
    private String refreshToken;
    private Date accessTokenExpireAt;
    private Date refreshTokenExpireAt;
    private String message;
    private User user;
}
