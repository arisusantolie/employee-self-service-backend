package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.EmploymentEntity;
import com.project.ess.projection.EmploymentBaseProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<EmploymentEntity,Long> {

    @Query("select u from EmploymentEntity u where current_date >=u.startDate and current_date <=u.endDate and u.employee=:employee")
    public EmploymentBaseProj getEmployment(@Param("employee") EmployeeEntity employeeNo);

    @Query("select u from EmploymentEntity u where u.employeeDirectToId=:employee")
    public List<EmploymentBaseProj> getMyTeam(@Param("employee") EmployeeEntity employeeEntity);
}
