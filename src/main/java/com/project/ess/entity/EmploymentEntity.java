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
    @JoinColumn(name = "employeeDirectToId")
    private EmployeeEntity employeeDirectToId;

    private String department;

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

    public EmployeeEntity getEmployeeDirectToId() {
        return employeeDirectToId;
    }

    public void setEmployeeDirectToId(EmployeeEntity employeeDirectToId) {
        this.employeeDirectToId = employeeDirectToId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
