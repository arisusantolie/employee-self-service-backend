package com.project.ess.execptions;

import java.util.Date;

public class ErrorMessage {
    private Date timestamp;
    private String message;
    private Boolean error;

    public ErrorMessage(Date timestamp, String message, Boolean error) {
        this.timestamp = timestamp;
        this.message = message;
        this.error = error;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
