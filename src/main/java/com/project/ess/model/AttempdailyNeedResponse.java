package com.project.ess.model;

import com.project.ess.entity.AttempdailyEntity;

public class AttempdailyNeedResponse {

    AttempdailyEntity attempdailyEntity;
    Long employeeNo;
    String employeeName;
    String requestNo;

    public AttempdailyNeedResponse(AttempdailyEntity attempdailyEntity, Long employeeNo, String employeeName, String requestNo) {
        this.attempdailyEntity = attempdailyEntity;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.requestNo = requestNo;
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
