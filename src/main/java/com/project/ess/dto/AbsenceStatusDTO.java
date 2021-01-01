package com.project.ess.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class AbsenceStatusDTO {
    private String managerName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime approvedDatetime;
    private String remark;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AbsenceStatusDTO() {
    }



    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public LocalDateTime getApprovedDatetime() {
        return approvedDatetime;
    }

    public void setApprovedDatetime(LocalDateTime approvedDatetime) {
        this.approvedDatetime = approvedDatetime;
    }

    public AbsenceStatusDTO(String managerName, LocalDateTime approvedDatetime, String remark,String status) {
        this.managerName = managerName;
        this.approvedDatetime = approvedDatetime;
        this.remark = remark;
        this.status=status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
