package com.project.ess.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.ess.entity.compositekey.AttempdailyId;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@IdClass(AttempdailyId.class)
@Table(name ="attempdaily")
public class AttempdailyEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "employee_no")
    @JsonBackReference
    private EmployeeEntity employeeNo;


    private LocalDateTime actualTime;

    private String type;

    @Column(nullable = false,precision=11, scale=8)
    private BigDecimal actual_lng;

    @Column(nullable = false,precision=11, scale=8)
    private BigDecimal actual_lat;

    @Id
    private String requestNo;

    private Boolean isOutOffice;
    private String purpose;
    private String remark;




    public EmployeeEntity getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(EmployeeEntity employeeNo) {
        this.employeeNo = employeeNo;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getActual_lng() {
        return actual_lng;
    }

    public void setActual_lng(BigDecimal actual_lng) {
        this.actual_lng = actual_lng;
    }

    public BigDecimal getActual_lat() {
        return actual_lat;
    }

    public void setActual_lat(BigDecimal actual_lat) {
        this.actual_lat = actual_lat;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Boolean getOutOffice() {
        return isOutOffice;
    }

    public void setOutOffice(Boolean outOffice) {
        isOutOffice = outOffice;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
