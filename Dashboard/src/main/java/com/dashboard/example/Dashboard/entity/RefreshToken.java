package com.dashboard.example.Dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "refreshTokens")
public class RefreshToken {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long token_id;
    private String token;
    private Date expires_at;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
