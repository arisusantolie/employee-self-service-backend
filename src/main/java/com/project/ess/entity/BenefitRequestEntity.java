package com.project.ess.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.ess.entity.compositekey.BenefitRequestId;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity()
@IdClass(BenefitRequestId.class)
@Table(name = "benefitRequest")
public class BenefitRequestEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "benefit_balance_id")
    @JsonBackReference
    private BenefitBalanceEntity benefitBalanceId;


    private LocalDateTime requestDateTime;

    @Id
    private String requestNo;
    private String benefitReason;
    private BigDecimal amount;
    private LocalDate transactionDate;
    private String remark;
    private String attachment;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }


    public BenefitBalanceEntity getBenefitBalanceId() {
        return benefitBalanceId;
    }

    public void setBenefitBalanceId(BenefitBalanceEntity benefitBalanceId) {
        this.benefitBalanceId = benefitBalanceId;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
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

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
