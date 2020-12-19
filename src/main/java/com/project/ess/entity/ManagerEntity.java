package com.project.ess.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "manager")
public class ManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId;

    @ManyToOne()
    @JoinColumn(name = "employee_no")
    private EmployeeEntity employeeEntity;

    @ManyToOne
    @JoinColumn(name = "divisi_id")
    private DivisiEntity divisiEntity;

    private LocalDate startDate;
    private LocalDate endDate;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public DivisiEntity getDivisiEntity() {
        return divisiEntity;
    }

    public void setDivisiEntity(DivisiEntity divisiEntity) {
        this.divisiEntity = divisiEntity;
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
