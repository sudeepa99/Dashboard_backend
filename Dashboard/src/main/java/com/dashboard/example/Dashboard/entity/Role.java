package com.dashboard.example.Dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;
    private String name;
    private Date created_at;
    private Date updated_at;

//    @OneToMany (mappedBy = "role", cascade = CascadeType.ALL)
//    private Set<UserRole> userRoles;
//
//    @OneToMany (mappedBy = "role", cascade = CascadeType.ALL)
//    private Set<RoleRule> roleRules;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_rules",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<Rule> rules;



}
