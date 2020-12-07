package com.project.ess.entity;

import com.project.ess.entity.compositekey.AddressRequestId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@IdClass(AddressRequestId.class)
@Table(name = "addressRequest")
public class AddressRequestEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressId;

    @Id
    private LocalDateTime requestDateTime;


    private String attachmentPath;


    @Lob
    @Column(columnDefinition = "json",length = 512)
    private String requestData;
    private String status;
    private Long approvedBy;
    private LocalDateTime approvedDatetime;

    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getApprovedDatetime() {
        return approvedDatetime;
    }

    public void setApprovedDatetime(LocalDateTime approvedDatetime) {
        this.approvedDatetime = approvedDatetime;
    }

    public AddressEntity getAddressId() {
        return addressId;
    }

    public void setAddressId(AddressEntity addressId) {
        this.addressId = addressId;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
