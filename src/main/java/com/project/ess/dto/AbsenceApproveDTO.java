package com.project.ess.dto;

public class AbsenceApproveDTO {
    private String RequestNo;
    private String Remark;
    private String status;

    public String getRequestNo() {
        return RequestNo;
    }

    public void setRequestNo(String requestNo) {
        RequestNo = requestNo;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
