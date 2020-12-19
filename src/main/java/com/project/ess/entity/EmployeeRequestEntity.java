package com.project.ess.entity;


import com.project.ess.entity.compositekey.EmployeeRequestId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity()
@IdClass(EmployeeRequestId.class)
@Table(name = "employeeRequest")
public class EmployeeRequestEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "employeeNo")
    private EmployeeEntity employeeNo;


    private LocalDateTime requestDateTime;

    private String attachment;

    @Lob
    @Column(length = 512)
    private String requestData;
    private String fileName;

    @Id
    private String requestNo;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
