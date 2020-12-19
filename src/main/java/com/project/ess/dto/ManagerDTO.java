package com.project.ess.dto;

import java.time.LocalDate;

public class ManagerDTO {
    private Long employeeNo;
    private Long divisiId;
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
