package com.dashboard.example.Dashboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table (name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int rule_id;
    private String name;
//    private Date created_at;
//    private Date updated_at;

//    @OneToMany (mappedBy = "rule", cascade = CascadeType.ALL)
//    private Set<RoleRule> roleRules;

    @ManyToMany(mappedBy = "rules", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Role> roles;
}
