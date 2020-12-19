package com.project.ess.model;

import com.project.ess.entity.AttempdailyEntity;

public class AttempdailyNeedResponse {

    AttempdailyEntity attempdailyEntity;
    Long employeeNo;
    String employeeName;
    String requestNo;
    String status;
    String remark;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AttempdailyNeedResponse(AttempdailyEntity attempdailyEntity, Long employeeNo, String employeeName, String requestNo, String status, String remark) {
        this.attempdailyEntity = attempdailyEntity;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.requestNo = requestNo;
        this.status = status;
        this.remark = remark;
    }

    public AttempdailyEntity getAttempdailyEntity() {
        return attempdailyEntity;
    }

    public void setAttempdailyEntity(AttempdailyEntity attempdailyEntity) {
        this.attempdailyEntity = attempdailyEntity;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
