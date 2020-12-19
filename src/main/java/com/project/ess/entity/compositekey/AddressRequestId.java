package com.project.ess.entity.compositekey;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AddressRequestId implements Serializable {

    private Long addressId;
    private String requestNo;

    public AddressRequestId() {
    }

    public AddressRequestId(Long addressId, String requestNo) {
        this.addressId = addressId;
        this.requestNo = requestNo;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }


    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressRequestId that = (AddressRequestId) o;
        return Objects.equals(addressId, that.addressId) &&
                Objects.equals(requestNo, that.requestNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, requestNo);
    }
}
