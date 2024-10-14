package com.dashboard.example.Dashboard.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int user_id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Date created_at;
    @Column(nullable = false)
    private Date updated_at;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Role> roles = new HashSet<>();
    @OneToOne
    @JsonManagedReference
    private RefreshToken token;
}
