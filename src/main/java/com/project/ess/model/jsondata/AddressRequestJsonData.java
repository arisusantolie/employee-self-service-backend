package com.project.ess.model.jsondata;

public class AddressRequestJsonData {
    private Long addressId;
    private String address;
    private String city;
    private String country;
    private Boolean primaryFlag;
    private String province;
    private String stayStatus;
    private String type;
    private String zipCode;
    private String newAdddress;
    private String newCity;
    private String newCountry;
    private Boolean newPrimaryFlag;
    private String newProvince;
    private String newStayStatus;
    private String newType;
    private String newZipCode;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getPrimaryFlag() {
        return primaryFlag;
    }

    public void setPrimaryFlag(Boolean primaryFlag) {
        this.primaryFlag = primaryFlag;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStayStatus() {
        return stayStatus;
    }

    public void setStayStatus(String stayStatus) {
        this.stayStatus = stayStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNewAdddress() {
        return newAdddress;
    }

    public void setNewAdddress(String newAdddress) {
        this.newAdddress = newAdddress;
    }

    public String getNewCity() {
        return newCity;
    }

    public void setNewCity(String newCity) {
        this.newCity = newCity;
    }

    public String getNewCountry() {
        return newCountry;
    }

    public void setNewCountry(String newCountry) {
        this.newCountry = newCountry;
    }

    public Boolean getNewPrimaryFlag() {
        return newPrimaryFlag;
    }

    public void setNewPrimaryFlag(Boolean newPrimaryFlag) {
        this.newPrimaryFlag = newPrimaryFlag;
    }

    public String getNewProvince() {
        return newProvince;
    }

    public void setNewProvince(String newProvince) {
        this.newProvince = newProvince;
    }

    public String getNewStayStatus() {
        return newStayStatus;
    }

    public void setNewStayStatus(String newStayStatus) {
        this.newStayStatus = newStayStatus;
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }

    public String getNewZipCode() {
        return newZipCode;
    }

    public void setNewZipCode(String newZipCode) {
        this.newZipCode = newZipCode;
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
