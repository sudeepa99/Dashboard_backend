package com.dashboard.example.Dashboard.repo;

import com.dashboard.example.Dashboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
