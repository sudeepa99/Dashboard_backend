package com.dashboard.example.Dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupRequestDTO {
    private String Name;
    private String email;
    private String password;
}
