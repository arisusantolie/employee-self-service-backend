package com.project.ess.model;

import com.project.ess.entity.EmployeeEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;

public class EmployeeProfileResponse {

    private LocalDate birthDate;
    private String bloodType;
    private Long bpjsNo;
    private String email;
    private Long employeeNo;
    private Long familyCardNo;
    private String firstName;
    private Long ktpNo;
    private String lastEducation;
    private String lastName;
    private String maritialStatus;
    private String mobilePhone;
    private String nationality;
    private Long npwpNo;
    private String religion;

    private String profilePicture;

    public EmployeeProfileResponse() {
    }

    public EmployeeProfileResponse(LocalDate birthDate, String bloodType, Long bpjsNo, String email, Long employeeNo, Long familyCardNo, String firstName, Long ktpNo, String lastEducation, String lastName, String maritialStatus, String mobilePhone, String nationality, Long npwpNo, String religion, String profilePicture) {
        this.birthDate = birthDate;
        this.bloodType = bloodType;
        this.bpjsNo = bpjsNo;
        this.email = email;
        this.employeeNo = employeeNo;
        this.familyCardNo = familyCardNo;
        this.firstName = firstName;
        this.ktpNo = ktpNo;
        this.lastEducation = lastEducation;
        this.lastName = lastName;
        this.maritialStatus = maritialStatus;
        this.mobilePhone = mobilePhone;
        this.nationality = nationality;
        this.npwpNo = npwpNo;
        this.religion = religion;

        if(profilePicture.isEmpty() || profilePicture==null){
            this.profilePicture=profilePicture;
        }else{
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/v1/downloadFile/")
                    .path(profilePicture)
                    .toUriString();

            this.profilePicture = fileDownloadUri;
        }

    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Long getBpjsNo() {
        return bpjsNo;
    }

    public void setBpjsNo(Long bpjsNo) {
        this.bpjsNo = bpjsNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Long getFamilyCardNo() {
        return familyCardNo;
    }

    public void setFamilyCardNo(Long familyCardNo) {
        this.familyCardNo = familyCardNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getKtpNo() {
        return ktpNo;
    }

    public void setKtpNo(Long ktpNo) {
        this.ktpNo = ktpNo;
    }

    public String getLastEducation() {
        return lastEducation;
    }

    public void setLastEducation(String lastEducation) {
        this.lastEducation = lastEducation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMaritialStatus() {
        return maritialStatus;
    }

    public void setMaritialStatus(String maritialStatus) {
        this.maritialStatus = maritialStatus;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Long getNpwpNo() {
        return npwpNo;
    }

    public void setNpwpNo(Long npwpNo) {
        this.npwpNo = npwpNo;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
