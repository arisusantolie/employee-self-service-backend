package com.project.ess.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.model.jsondata.EmployeeRequestJsonData;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

public class EmployeeNeedApproveResponse {

    private EmployeeRequestJsonData employeeRequestData;
    private Long employeeNo;
    private String requestData;
    private String status;
    private LocalDateTime approvedDatetime;
    private String fileName;
    private String requestNo;
    private String attachment;
    private LocalDateTime requestDateTime;
    private String employeeName;
    private String remarkRequeuest;
    private String remarkApproval;

    public String getRemarkRequeuest() {
        return remarkRequeuest;
    }

    public void setRemarkRequeuest(String remarkRequeuest) {
        this.remarkRequeuest = remarkRequeuest;
    }

    public String getRemarkApproval() {
        return remarkApproval;
    }

    public void setRemarkApproval(String remarkApproval) {
        this.remarkApproval = remarkApproval;
    }

    public EmployeeNeedApproveResponse(Long employeeNo, String requestData, String status, LocalDateTime approvedDatetime, String fileName, String requestNo, String attachment, LocalDateTime requestDateTime, String employeeName,String remarkRequest,String remarkApproval) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/downloadFile/")
                .path(fileName)
                .toUriString();

        this.employeeRequestData= objectMapper.readValue(requestData, EmployeeRequestJsonData.class);
        this.employeeNo = employeeNo;
        this.requestData = requestData;
        this.status = status;
        this.approvedDatetime = approvedDatetime;
        this.fileName = fileName;
        this.requestNo = requestNo;
        this.attachment = fileDownloadUri;
        this.requestDateTime = requestDateTime;
        this.employeeName = employeeName;

        if(remarkRequest==null){
            this.remarkRequeuest="-";
        }else{
            this.remarkRequeuest=remarkRequest;
        }

        if(remarkApproval==null){
            this.remarkApproval="-";
        }else{
            this.remarkApproval=remarkApproval;
        }

    }

    public EmployeeRequestJsonData getEmployeeRequestData() {
        return employeeRequestData;
    }

    public void setEmployeeRequestData(EmployeeRequestJsonData employeeRequestData) {
        this.employeeRequestData = employeeRequestData;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
