package com.project.ess.model;

import com.project.ess.entity.BenefitRequestEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class BenefitNeedApproveResponse {

    BenefitRequestEntity benefitRequestEntity;
    String employeeName;
    Long employeeNo;
    String requestNo;

    public BenefitNeedApproveResponse(BenefitRequestEntity benefitRequestEntity, String employeeName, Long employeeNo, String requestNo) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/downloadFile/")
                .path(benefitRequestEntity.getFileName())
                .toUriString();

        this.benefitRequestEntity = benefitRequestEntity;
        this.benefitRequestEntity.setAttachment(fileDownloadUri);
        this.employeeName = employeeName;
        this.employeeNo = employeeNo;
        this.requestNo = requestNo;
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
