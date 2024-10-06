package com.dashboard.example.Dashboard.entity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserRolesId implements Serializable {


    private Long userId;
    private Long roleId;

}
