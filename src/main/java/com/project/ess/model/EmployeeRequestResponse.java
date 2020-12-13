package com.project.ess.model;

import java.time.LocalDateTime;

public class EmployeeRequestResponse {
    private String newFirstName;
    private String newLastName;
    private String newBirthDate;
    private String newReligion;
    private String newBloodType;
    private String newEmail;
    private Long newKtpNo;
    private Long newBpjsNo;
    private Long newFamilyCardNo;
    private Long newNpwpNo;
    private String newLastEducation;
    private String newNationality;
    private String newMobilePhone;
    private String newMaritialStatus;
    private Long employeeNo;
    private String requestNo;
    private String attachment;
    private LocalDateTime requestDatetime;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public LocalDateTime getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(LocalDateTime requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }

    public String getNewBirthDate() {
        return newBirthDate;
    }

    public void setNewBirthDate(String newBirthDate) {
        this.newBirthDate = newBirthDate;
    }

    public String getNewReligion() {
        return newReligion;
    }

    public void setNewReligion(String newReligion) {
        this.newReligion = newReligion;
    }

    public String getNewBloodType() {
        return newBloodType;
    }

    public void setNewBloodType(String newBloodType) {
        this.newBloodType = newBloodType;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public Long getNewKtpNo() {
        return newKtpNo;
    }

    public void setNewKtpNo(Long newKtpNo) {
        this.newKtpNo = newKtpNo;
    }

    public Long getNewBpjsNo() {
        return newBpjsNo;
    }

    public void setNewBpjsNo(Long newBpjsNo) {
        this.newBpjsNo = newBpjsNo;
    }

    public Long getNewFamilyCardNo() {
        return newFamilyCardNo;
    }

    public void setNewFamilyCardNo(Long newFamilyCardNo) {
        this.newFamilyCardNo = newFamilyCardNo;
    }

    public Long getNewNpwpNo() {
        return newNpwpNo;
    }

    public void setNewNpwpNo(Long newNpwpNo) {
        this.newNpwpNo = newNpwpNo;
    }

    public String getNewLastEducation() {
        return newLastEducation;
    }

    public void setNewLastEducation(String newLastEducation) {
        this.newLastEducation = newLastEducation;
    }

    public String getNewNationality() {
        return newNationality;
    }

    public void setNewNationality(String newNationality) {
        this.newNationality = newNationality;
    }

    public String getNewMobilePhone() {
        return newMobilePhone;
    }

    public void setNewMobilePhone(String newMobilePhone) {
        this.newMobilePhone = newMobilePhone;
    }

    public String getNewMaritialStatus() {
        return newMaritialStatus;
    }

    public void setNewMaritialStatus(String newMaritialStatus) {
        this.newMaritialStatus = newMaritialStatus;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }
}
