package com.project.ess.repository;

import com.project.ess.entity.EmployeeRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRequestRepository extends JpaRepository<EmployeeRequestEntity,Long> {
}
