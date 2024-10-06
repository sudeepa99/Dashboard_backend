package com.dashboard.example.Dashboard.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RoleRuleId implements Serializable {
    private Long roleId;
    private Long ruleId;
}
