package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.HRAdminEntity;
import com.project.ess.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<ManagerEntity,Long> {

    @Query("select u from ManagerEntity u where current_date >=u.startDate and current_date <=u.endDate and u.employeeEntity =:employee")
    public Optional<ManagerEntity> findByEmployee(@Param("employee") EmployeeEntity employeeEntity);
}
