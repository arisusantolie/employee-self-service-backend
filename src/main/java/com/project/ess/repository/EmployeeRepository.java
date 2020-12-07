package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

    public Optional<EmployeeEntity> findByEmail(String email);
}
