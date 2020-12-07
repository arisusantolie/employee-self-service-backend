package com.project.ess.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BenefitBalanceResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate endDate;
    private int period;
    private Long employeeNo;
    private BigDecimal balanceLimit;
    private Long benefitPlanId;


    public Long getBenefitPlanId() {
        return benefitPlanId;
    }

    public void setBenefitPlanId(Long benefitPlanId) {
        this.benefitPlanId = benefitPlanId;
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public BigDecimal getBalanceLimit() {
        return balanceLimit;
    }

    public void setBalanceLimit(BigDecimal balanceLimit) {
        this.balanceLimit = balanceLimit;
    }
}
