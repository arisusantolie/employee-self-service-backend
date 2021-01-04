package com.project.ess.projection;

import com.project.ess.entity.ManagerEntity;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface EmploymentBaseProj {

    @Value("#{target.divisiEntity.managerEntity[0].employeeEntity.firstName+' '+target.divisiEntity.managerEntity[0].employeeEntity.lastName}")
    String getDirectSupervisor();

    @Value("#{target.divisiEntity.departmentEntity.departmentName}")
    String getDepartment();
    String getEmployeeStatus();
    String getPosition();
    LocalDate getStartDate();

    @Value("#{target.endDate=='4000-12-31' ? '-' : target.endDate}")
    LocalDate getEndDate();

    @Value("#{target.employee.firstName+' '+target.employee.lastName}")
    String getName();

    @Value("#{target.divisiEntity.managerEntity[0].managerId}")
    Long getManagerId();
}
