package com.project.ess.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "benefitBalance")
public class BenefitBalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long benefitBalanceId;

    @ManyToOne
    @JoinColumn(name = "benefitPlanId")
    private BenefitPlanEntity benefitPlanEntity;

    @ManyToOne
    @JoinColumn(name = "employeeNo")
    private EmployeeEntity employee;

    private LocalDate startDate;
    private LocalDate endDate;
    private int period;
    private BigDecimal balanceLimit;
    private BigDecimal balanceUsed;
    private BigDecimal balanceEnd;


    public Long getBenefitBalanceId() {
        return benefitBalanceId;
    }

    public void setBenefitBalanceId(Long benefitBalanceId) {
        this.benefitBalanceId = benefitBalanceId;
    }

    public BenefitPlanEntity getBenefitPlan() {
        return benefitPlanEntity;
    }

    public void setBenefitPlan(BenefitPlanEntity benefitPlanEntity) {
        this.benefitPlanEntity = benefitPlanEntity;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
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

    public BenefitPlanEntity getBenefitPlanEntity() {
        return benefitPlanEntity;
    }

    public void setBenefitPlanEntity(BenefitPlanEntity benefitPlanEntity) {
        this.benefitPlanEntity = benefitPlanEntity;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public BigDecimal getBalanceLimit() {
        return balanceLimit;
    }

    public void setBalanceLimit(BigDecimal balanceLimit) {
        this.balanceLimit = balanceLimit;
    }

    public BigDecimal getBalanceUsed() {
        return balanceUsed;
    }

    public void setBalanceUsed(BigDecimal balanceUsed) {
        this.balanceUsed = balanceUsed;
    }

    public BigDecimal getBalanceEnd() {
        return balanceEnd;
    }

    public void setBalanceEnd(BigDecimal balanceEnd) {
        this.balanceEnd = balanceEnd;
    }
}
