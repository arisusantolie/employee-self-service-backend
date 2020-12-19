package com.project.ess.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity()
@Table(name = "employment")
public class EmploymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employmentId;

    @ManyToOne
    @JoinColumn(name = "employeeNo")
    private EmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "divisi_id")
    private DivisiEntity divisiEntity;

    private String position;

    private String employeeStatus;

    private LocalDate startDate;
    private LocalDate endDate;

    public Long getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(Long employmentId) {
        this.employmentId = employmentId;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }


    public DivisiEntity getDivisiEntity() {
        return divisiEntity;
    }

    public void setDivisiEntity(DivisiEntity divisiEntity) {
        this.divisiEntity = divisiEntity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
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
