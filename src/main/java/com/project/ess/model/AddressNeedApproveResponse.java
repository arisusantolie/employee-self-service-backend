package com.project.ess.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.entity.AddressRequestEntity;
import com.project.ess.model.jsondata.AddressRequestJsonData;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

public class AddressNeedApproveResponse {
    Long addressId;
    String attachmentPath;
    String requestData;
    String status;
    Long approvedBy;
    LocalDateTime approvedDatetime;
    String fileName;
    AddressRequestJsonData addressRequestData;
    Long employeeNo;
    String employeeName;
    String requestNo;
    LocalDateTime requestDateTime;

    public AddressNeedApproveResponse(Long addressId, String attachmentPath, String requestData, String status, Long approvedBy, LocalDateTime approvedDatetime, String fileName, Long employeeNo, String employeeName, String requestNo, LocalDateTime requestDateTime) throws JsonProcessingException {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/downloadFile/")
                .path(fileName)
                .toUriString();
        ObjectMapper objectMapper=new ObjectMapper();
        this.addressRequestData=objectMapper.readValue(requestData,AddressRequestJsonData.class);
        this.addressId = addressId;
        this.attachmentPath = fileDownloadUri;
        this.requestData = requestData;
        this.status = status;
        this.approvedBy = approvedBy;
        this.approvedDatetime = approvedDatetime;
        this.fileName = fileName;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.requestNo = requestNo;
        this.requestDateTime = requestDateTime;

    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

//    public String getRequestData() {
//        return requestData;
//    }

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

    public AddressRequestJsonData getAddressRequestData() {
        return addressRequestData;
    }

    public void setAddressRequestData(AddressRequestJsonData addressRequestData) {
        this.addressRequestData = addressRequestData;
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

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }
}
