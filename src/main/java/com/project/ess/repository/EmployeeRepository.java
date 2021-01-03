package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

    public Optional<EmployeeEntity> findByEmail(String email);

    @Query("select u from EmployeeEntity u,EmploymentEntity employ where u=employ.employee and current_date >=employ.startDate and current_date <=employ.endDate")
    public List<EmployeeEntity> getAllEmployee();
}
