package com.project.ess.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface EmploymentBaseProj {

    @Value("#{target.employeeDirectToId.firstName+' '+target.employeeDirectToId.lastName}")
    String getDirectSupervisor();

    String getDepartment();
    String getEmployeeStatus();
    String getPosition();
    LocalDate getStartDate();

    @Value("#{target.endDate=='4000-12-31' ? target.endDate : '-'}")
    String getEndDate();


}
