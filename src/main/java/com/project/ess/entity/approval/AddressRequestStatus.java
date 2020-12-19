package com.project.ess.entity.approval;

import com.project.ess.entity.AddressRequestEntity;

import com.project.ess.entity.HRAdminEntity;

import com.project.ess.entity.approval.compositekey.AddressRequestStatusId;

import javax.persistence.*;

@Entity
@Table(name = "addressRequestStatus")
@IdClass(AddressRequestStatusId.class)
public class AddressRequestStatus extends RequestEntity{

    @Id
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "address_id"),
            @JoinColumn(name = "request_no")
    })
    private AddressRequestEntity addressRequestEntity;


    @ManyToOne
    @JoinColumn(name = "hraId")
    private HRAdminEntity hraId;

    public AddressRequestEntity getAddressRequestEntity() {
        return addressRequestEntity;
    }

    public void setAddressRequestEntity(AddressRequestEntity addressRequestEntity) {
        this.addressRequestEntity = addressRequestEntity;
    }

    public HRAdminEntity getHraId() {
        return hraId;
    }

    public void setHraId(HRAdminEntity hraId) {
        this.hraId = hraId;
    }
}
