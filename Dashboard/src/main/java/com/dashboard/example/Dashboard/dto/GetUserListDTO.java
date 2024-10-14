package com.dashboard.example.Dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class GetUserListDTO <DataType> {
    private Boolean status;
    private DataType data;
    private String message;


}
