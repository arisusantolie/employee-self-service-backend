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

    @Id
    private LocalDateTime requestDateTime;

    private String attachmentPath;

    @Lob
    @Column(length = 512)
    private String requestData;
    private String status;

    private Long approvedBy;
    private LocalDateTime approvedDatetime;
    private String fileName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
