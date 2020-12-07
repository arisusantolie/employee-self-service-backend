package com.project.ess.model.jsondata;

import java.time.LocalDate;

public class EmployeeRequestJsonData {
    private Long employeeNo;
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

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
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

    @Override
    public String toString(){
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
