package com.project.ess.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BenefitRequestResponse {

    private String status;
    private String attachment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime requestDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime approvedDatetime;
    private String requestNo;
    private Long managerId;
    private Long benefitPlanId;
    private String benefitPlanName;
    private String managerName;
    private BigDecimal amount;
    private String remark;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBenefitPlanName() {
        return benefitPlanName;
    }

    public void setBenefitPlanName(String benefitPlanName) {
        this.benefitPlanName = benefitPlanName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public LocalDateTime getApprovedDatetime() {
        return approvedDatetime;
    }

    public void setApprovedDatetime(LocalDateTime approvedDatetime) {
        this.approvedDatetime = approvedDatetime;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public BenefitRequestResponse(String status, String attachment, LocalDateTime requestDateTime, LocalDateTime approvedDatetime, String requestNo, Long managerId, Long benefitPlanId,String benefitPlanName, String managerName,BigDecimal amount,String remark) {

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/downloadFile/")
                .path(attachment)
                .toUriString();

        this.status = status;
        this.attachment = fileDownloadUri;
        this.requestDateTime = requestDateTime;
        this.approvedDatetime = approvedDatetime;
        this.requestNo = requestNo;
        this.managerId = managerId;
        this.benefitPlanId = benefitPlanId;
        this.managerName = managerName;
        this.benefitPlanName=benefitPlanName;
        this.amount=amount;
        this.remark=remark;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getBenefitPlanId() {
        return benefitPlanId;
    }

    public void setBenefitPlanId(Long benefitPlanId) {
        this.benefitPlanId = benefitPlanId;
    }
}
