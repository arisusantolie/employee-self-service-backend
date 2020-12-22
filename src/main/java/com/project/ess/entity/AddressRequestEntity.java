package com.project.ess.entity;

import com.project.ess.entity.compositekey.AddressRequestId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity()
@IdClass(AddressRequestId.class)
@Table(name = "addressRequest")
public class AddressRequestEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressId;


    private LocalDateTime requestDateTime;


    private String attachmentPath;


    @Lob
    @Column(columnDefinition = "json",length = 512)
    private String requestData;

    private String fileName;

    @Id
    private String requestNo;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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


}
