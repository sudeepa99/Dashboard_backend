package com.dashboard.example.Dashboard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table (name = "refreshTokens")
public class RefreshToken {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int token_id;
    private String token;
    private Date expires_at;

    @OneToOne(mappedBy = "token")
    @JsonBackReference
    private User user;

}
