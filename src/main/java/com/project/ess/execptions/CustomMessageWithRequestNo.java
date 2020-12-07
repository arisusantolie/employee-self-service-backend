package com.project.ess.execptions;

public class CustomMessageWithRequestNo {

    private String message;
    private Boolean error;
    private String requestNo;

    public CustomMessageWithRequestNo(String message, Boolean error, String requestNo) {
        this.message = message;
        this.error = error;
        this.requestNo = requestNo;
    }

    public CustomMessageWithRequestNo() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
