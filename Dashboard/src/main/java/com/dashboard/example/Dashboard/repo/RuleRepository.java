package com.dashboard.example.Dashboard.repo;

import com.dashboard.example.Dashboard.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer> {
}
