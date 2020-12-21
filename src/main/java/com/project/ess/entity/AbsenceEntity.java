package com.project.ess.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.ess.entity.approval.AbsenceStatus;
import com.project.ess.entity.compositekey.AbsenceId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity()
@IdClass(AbsenceId.class)
@Table(name = "absence")
public class AbsenceEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "employee_no")
    @JsonBackReference
    private EmployeeEntity employeeNo;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime requestDateTime;

    @Id
    private String requestNo;

    private int amount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String remark;

    @ManyToOne
    @JoinColumn(name = "absence_type_id")
    private AbsenceTypeEntity absenceTypeEntity;


    public AbsenceTypeEntity getAbsenceTypeEntity() {
        return absenceTypeEntity;
    }

    public void setAbsenceTypeEntity(AbsenceTypeEntity absenceTypeEntity) {
        this.absenceTypeEntity = absenceTypeEntity;
    }

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


}
