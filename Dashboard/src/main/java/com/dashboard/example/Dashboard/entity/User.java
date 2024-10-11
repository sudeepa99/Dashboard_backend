package com.dashboard.example.Dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String name;
    private String password;
    private String email;
    private Date created_at;
    private Date updated_at;

//    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<UserRole> userRoles;
//
//    @OneToOne (mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//fetchtype.lazy aviod loading unnecessary data and improve performance.
//    private RefreshToken refreshToken;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private RefreshToken refreshToken;

}
