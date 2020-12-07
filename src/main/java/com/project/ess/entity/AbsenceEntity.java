package com.project.ess.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.ess.entity.compositekey.AbsenceId;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "absence")
@IdClass(AbsenceId.class)
public class AbsenceEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "employee_no")
    private EmployeeEntity employeeNo;

    @Id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime requestDateTime;

    private String requestNo;
    private int amount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String remark;
    private String type;
    private String status;
    private String attachment;
    private Long approvedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate approvedDatetime;
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

    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDate getApprovedDatetime() {
        return approvedDatetime;
    }

    public void setApprovedDatetime(LocalDate approvedDatetime) {
        this.approvedDatetime = approvedDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeEntity getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(EmployeeEntity employeeNo) {
        this.employeeNo = employeeNo;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
