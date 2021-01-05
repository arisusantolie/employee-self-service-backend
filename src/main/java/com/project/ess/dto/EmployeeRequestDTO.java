package com.project.ess.dto;

import javax.persistence.Column;
import java.time.LocalDate;

public class EmployeeRequestDTO {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String religion;
    private String bloodType;
    private String email;
    private Long ktpNo;
    private Long bpjsNo;
    private Long familyCardNo;
    private Long npwpNo;
    private String lastEducation;
    private String nationality;
    private String mobilePhone;
    private String maritialStatus;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getKtpNo() {
        return ktpNo;
    }

    public void setKtpNo(Long ktpNo) {
        this.ktpNo = ktpNo;
    }

    public Long getBpjsNo() {
        return bpjsNo;
    }

    public void setBpjsNo(Long bpjsNo) {
        this.bpjsNo = bpjsNo;
    }

    public Long getFamilyCardNo() {
        return familyCardNo;
    }

    public void setFamilyCardNo(Long familyCardNo) {
        this.familyCardNo = familyCardNo;
    }

    public Long getNpwpNo() {
        return npwpNo;
    }

    public void setNpwpNo(Long npwpNo) {
        this.npwpNo = npwpNo;
    }

    public String getLastEducation() {
        return lastEducation;
    }

    public void setLastEducation(String lastEducation) {
        this.lastEducation = lastEducation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMaritialStatus() {
        return maritialStatus;
    }

    public void setMaritialStatus(String maritialStatus) {
        this.maritialStatus = maritialStatus;
    }
}
