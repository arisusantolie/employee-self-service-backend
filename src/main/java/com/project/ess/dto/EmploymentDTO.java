package com.project.ess.dto;

import java.time.LocalDate;

public class EmploymentDTO {

    private Long employeeNo;

    private Long divisiId;

    private String position;

    private String employeeStatus;

    private LocalDate startDate;
    private LocalDate endDate;

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }




    public Long getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(Long divisiId) {
        this.divisiId = divisiId;
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
