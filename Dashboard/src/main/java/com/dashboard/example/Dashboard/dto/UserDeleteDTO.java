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
public class UserDeleteDTO <DataType> {
    private Boolean status;
    private DataType data;
    private String message;

}
