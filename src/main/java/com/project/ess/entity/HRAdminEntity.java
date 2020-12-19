package com.project.ess.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hr_admin")
public class HRAdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hraId;

    @ManyToOne
    @JoinColumn(name = "employee_no")
    private EmployeeEntity employeeEntity;

    private LocalDate startDate;
    private LocalDate endDate;


    public Long getHraId() {
        return hraId;
    }

    public void setHraId(Long hraId) {
        this.hraId = hraId;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
