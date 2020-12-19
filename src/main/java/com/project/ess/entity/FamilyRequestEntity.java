package com.project.ess.entity;

import com.project.ess.entity.compositekey.FamilyRequestId;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@IdClass(FamilyRequestId.class)
@Table(name = "familyRequest")
public class FamilyRequestEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "family_id")
    private FamilyEntity familyId;


    private LocalDateTime requestDateTime;

    private String attachmentPath;

    @Lob
    @Column(length = 512)
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



    public FamilyRequestEntity() {
    }

    public FamilyEntity getFamilyId() {
        return familyId;
    }

    public void setFamilyId(FamilyEntity familyId) {
        this.familyId = familyId;
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
