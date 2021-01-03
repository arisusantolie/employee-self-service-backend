package com.project.ess.model.report;

public class AttempdailyReport {
    private String employeeNo;
    private String fullName;
    private String position;
    private String dateCheck;
    private String clock;
    private String requestNo;
    private String remarkRequest;
    private String status;
    private String actionBy;
    private String approvedDatetime;
    private String remarkApproved;

    public String getRemarkApproved() {
        return remarkApproved;
    }

    public void setRemarkApproved(String remarkApproved) {
        this.remarkApproved = remarkApproved;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDateCheck() {
        return dateCheck;
    }

    public void setDateCheck(String dateCheck) {
        this.dateCheck = dateCheck;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getRemarkRequest() {
        return remarkRequest;
    }

    public void setRemarkRequest(String remarkRequest) {
        this.remarkRequest = remarkRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActionBy() {
        return actionBy;
    }

    public void setActionBy(String actionBy) {
        this.actionBy = actionBy;
    }

    public String getApprovedDatetime() {
        return approvedDatetime;
    }

    public void setApprovedDatetime(String approvedDatetime) {
        this.approvedDatetime = approvedDatetime;
    }

    @Override
    public String toString(){
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
