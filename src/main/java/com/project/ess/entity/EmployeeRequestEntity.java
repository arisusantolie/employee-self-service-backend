package com.project.ess.entity;


import com.project.ess.entity.compositekey.EmployeeRequestId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "employeeRequest")
@IdClass(EmployeeRequestId.class)
public class EmployeeRequestEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "employeeNo")
    private EmployeeEntity employeeNo;

    @Id
    private LocalDateTime requestDateTime;

    private String attachment;

    @Lob
    @Column(length = 512)
    private String requestData;
    private String status;
    private Long approvedBy;
    private LocalDateTime approvedDatetime;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getApprovedDatetime() {
        return approvedDatetime;
    }

    public void setApprovedDatetime(LocalDateTime approvedDatetime) {
        this.approvedDatetime = approvedDatetime;
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }
}
