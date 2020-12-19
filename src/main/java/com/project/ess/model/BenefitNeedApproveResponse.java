package com.project.ess.model;

import com.project.ess.entity.BenefitRequestEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

public class BenefitNeedApproveResponse {

    BenefitRequestEntity benefitRequestEntity;
    String employeeName;
    Long employeeNo;
    String requestNo;
    Long managerId;
    String managerName;
    LocalDateTime approveDatetime;
    String remark;
    String status;

    public BenefitNeedApproveResponse(BenefitRequestEntity benefitRequestEntity, String employeeName, Long employeeNo, String requestNo,Long managerId,String managerName, LocalDateTime approveDatetime,String remark,String status) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/downloadFile/")
                .path(benefitRequestEntity.getFileName())
                .toUriString();

        this.benefitRequestEntity = benefitRequestEntity;
        this.benefitRequestEntity.setAttachment(fileDownloadUri);
        this.employeeName = employeeName;
        this.employeeNo = employeeNo;
        this.requestNo = requestNo;
        this.managerId=managerId;
        this.managerName=managerName;
        this.approveDatetime=approveDatetime;
        this.remark=remark;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public LocalDateTime getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(LocalDateTime approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BenefitRequestEntity getBenefitRequestEntity() {
        return benefitRequestEntity;
    }

    public void setBenefitRequestEntity(BenefitRequestEntity benefitRequestEntity) {
        this.benefitRequestEntity = benefitRequestEntity;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
