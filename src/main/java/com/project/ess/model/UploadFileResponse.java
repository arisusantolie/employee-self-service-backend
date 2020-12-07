package com.project.ess.model;

public class UploadFileResponse {
    private String attachment;
    private String contentType;
    private String fileName;

    public UploadFileResponse(String attachment, String contentType, String fileName) {
        this.attachment = attachment;
        this.contentType = contentType;
        this.fileName = fileName;
    }

    public UploadFileResponse() {
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
