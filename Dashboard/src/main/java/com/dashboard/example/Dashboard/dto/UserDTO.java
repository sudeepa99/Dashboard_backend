package com.dashboard.example.Dashboard.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDTO {
    private int id;
    private String firstName;
    private String email;
    private String password;
    private String lastName;


}
