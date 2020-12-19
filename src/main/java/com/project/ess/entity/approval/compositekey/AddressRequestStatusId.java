package com.project.ess.entity.approval.compositekey;

import com.project.ess.entity.AddressRequestEntity;
import com.project.ess.entity.compositekey.AddressRequestId;

import java.io.Serializable;
import java.util.Objects;

public class AddressRequestStatusId implements Serializable {

    private AddressRequestId addressRequestEntity;

    public AddressRequestStatusId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressRequestStatusId that = (AddressRequestStatusId) o;
        return Objects.equals(addressRequestEntity, that.addressRequestEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressRequestEntity);
    }

    public AddressRequestStatusId(AddressRequestId addressRequestEntity) {
        this.addressRequestEntity = addressRequestEntity;
    }

    public AddressRequestId getAddressRequestEntity() {
        return addressRequestEntity;
    }

    public void setAddressRequestEntity(AddressRequestId addressRequestEntity) {
        this.addressRequestEntity = addressRequestEntity;
    }
}
