package com.project.ess.entity.compositekey;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AddressRequestId implements Serializable {

    private Long addressId;
    private LocalDateTime requestDateTime;

    public AddressRequestId() {
    }

    public AddressRequestId(Long addressId, LocalDateTime requestDateTime) {
        this.addressId = addressId;
        this.requestDateTime = requestDateTime;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressRequestId that = (AddressRequestId) o;
        return Objects.equals(addressId, that.addressId) &&
                Objects.equals(requestDateTime, that.requestDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, requestDateTime);
    }
}
