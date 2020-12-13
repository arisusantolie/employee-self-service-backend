package com.project.ess.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity()
@Table(name = "family")
public class FamilyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @ManyToOne
    @JoinColumn(name = "employeeNo")
    private EmployeeEntity employee;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String maritalStatus;

    private LocalDate marriedDate;

    @Column(nullable = false)
    private String relationship;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobilePhone;

    @Column(nullable = false)
    private String bloodType;

    @Column(nullable = false)
    private Long familyCardNo;

    private Long ktpNo;

    private String aliveStatus;

    private String nationality;

    @Column(length = 255,nullable = false)
    private String address;

    private String city;

    private String zipCode;

    private String birthPlace;

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public LocalDate getMarriedDate() {
        return marriedDate;
    }

    public void setMarriedDate(LocalDate marriedDate) {
        this.marriedDate = marriedDate;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Long getFamilyCardNo() {
        return familyCardNo;
    }

    public void setFamilyCardNo(Long familyCardNo) {
        this.familyCardNo = familyCardNo;
    }

    public Long getKtpNo() {
        return ktpNo;
    }

    public void setKtpNo(Long ktpNo) {
        this.ktpNo = ktpNo;
    }

    public String getAliveStatus() {
        return aliveStatus;
    }

    public void setAliveStatus(String aliveStatus) {
        this.aliveStatus = aliveStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
}
