package com.project.ess.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity()
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeNo;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;


    @Column(nullable = false)
    private String religion;

    @Column(nullable = false, length = 2)
    private String bloodType;


    @Column(nullable = false,length = 100)
     private String email;

    @Column(nullable = false)
    private Long ktpNo;

    @Column(nullable = false)
    private Long bpjsNo;

    @Column(nullable = false)
    private Long familyCardNo;

    @Column(nullable = false)
    private Long npwpNo;

    @Column(nullable = false)
    private String lastEducation;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String mobilePhone;

    @Column(nullable = false)
    private String maritialStatus;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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
