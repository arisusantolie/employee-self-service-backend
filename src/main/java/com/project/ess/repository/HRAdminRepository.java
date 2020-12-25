package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.HRAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HRAdminRepository extends JpaRepository<HRAdminEntity,Long> {


    @Query("select u from HRAdminEntity u where current_date >=u.startDate and current_date <=u.endDate and u.employeeEntity =:employee")
    public Optional<HRAdminEntity>  findByEmployee(@Param("employee")EmployeeEntity employeeEntity);
}
