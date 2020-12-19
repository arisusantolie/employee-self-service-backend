package com.project.ess.model;

import com.project.ess.entity.AbsenceEntity;
import com.project.ess.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class AbsenceNeedApproveResponse {

    @Autowired
    UploadFileService uploadFileService;

    AbsenceEntity absenceEntity;
    Long employeeNo;
    String employeeName;
    String requestNo;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public AbsenceNeedApproveResponse(AbsenceEntity absenceEntity, Long employeeNo, String employeeName, String requestNo,String status) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/downloadFile/")
                .path(absenceEntity.getFileName())
                .toUriString();

        this.absenceEntity = absenceEntity;
        this.absenceEntity.setAttachment(fileDownloadUri);
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.requestNo = requestNo;
        this.status=status;
    }


    public AbsenceEntity getAbsenceEntity() {
        return absenceEntity;
    }

    public void setAbsenceEntity(AbsenceEntity absenceEntity) {
        this.absenceEntity = absenceEntity;
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
}
