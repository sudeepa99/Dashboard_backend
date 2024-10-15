package com.dashboard.example.Dashboard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table (name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private int role_id;
    @Column(nullable = false,unique = true)
    private String name;
//    private Date created_at;
//    private Date updated_at;


    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<User> users;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Rule> rules;






}
