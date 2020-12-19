package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.EmploymentEntity;
import com.project.ess.projection.EmploymentBaseProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<EmploymentEntity,Long> {

    @Query("select u from EmploymentEntity u,DivisiEntity de,ManagerEntity mgr where current_date >=u.startDate and current_date <=u.endDate " +
            "and u.divisiEntity=de and mgr.divisiEntity=de and current_date >=mgr.startDate and current_date <=mgr.endDate and u.employee=:employee")
    public EmploymentBaseProj getEmployment(@Param("employee") EmployeeEntity employeeNo);

    @Query("select u from EmploymentEntity u,ManagerEntity mgr,DivisiEntity dv where mgr.divisiEntity=dv and dv=u.divisiEntity " +
            "and current_date >=mgr.startDate and current_date <=mgr.endDate and mgr.employeeEntity=:employee")
    public List<EmploymentBaseProj> getMyTeam(@Param("employee") EmployeeEntity employeeEntity);
}
