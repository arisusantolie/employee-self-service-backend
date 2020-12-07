package com.project.ess.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BenefitRequestDTO {

    private Long benefitBalanceId;
    private String benefitReason;
    private BigDecimal amount;
    private String transactionDate;
    private String remark;

    public Long getBenefitBalanceId() {
        return benefitBalanceId;
    }

    public void setBenefitBalanceId(Long benefitBalanceId) {
        this.benefitBalanceId = benefitBalanceId;
    }

    public String getBenefitReason() {
        return benefitReason;
    }

    public void setBenefitReason(String benefitReason) {
        this.benefitReason = benefitReason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
