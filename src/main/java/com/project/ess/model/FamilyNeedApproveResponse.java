package com.project.ess.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.model.jsondata.FamilyRequestJsonData;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

public class FamilyNeedApproveResponse {

    private Long familyId;
    private LocalDateTime requestDateTime;

    private String attachmentPath;
    private String requestData;
    private String status;

    private Long approvedBy;
    private LocalDateTime approvedDatetime;
    private String fileName;
    private String requestNo;
    FamilyRequestJsonData familyRequestData;
    Long employeeNo;
    String employeeName;

    public FamilyNeedApproveResponse(Long familyId, LocalDateTime requestDateTime, String attachmentPath, String requestData, String status, Long approvedBy, LocalDateTime approvedDatetime, String fileName, String requestNo, Long employeeNo, String employeeName) throws JsonProcessingException {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/downloadFile/")
                .path(fileName)
                .toUriString();
        ObjectMapper objectMapper=new ObjectMapper();

        this.familyRequestData=objectMapper.readValue(requestData,FamilyRequestJsonData.class);
        this.familyId = familyId;
        this.requestDateTime = requestDateTime;
        this.attachmentPath = fileDownloadUri;
        this.status = status;
        this.approvedBy = approvedBy;
        this.approvedDatetime = approvedDatetime;
        this.fileName = fileName;
        this.requestNo = requestNo;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }


    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getApprovedDatetime() {
        return approvedDatetime;
    }

    public void setApprovedDatetime(LocalDateTime approvedDatetime) {
        this.approvedDatetime = approvedDatetime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public FamilyRequestJsonData getFamilyRequestData() {
        return familyRequestData;
    }

    public void setFamilyRequestData(FamilyRequestJsonData familyRequestData) {
        this.familyRequestData = familyRequestData;
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
