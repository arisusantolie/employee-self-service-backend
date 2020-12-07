package com.project.ess.execptions;

public class CustomMessageWithId {

    private String message;
    private Boolean error;
    private Long id;


    public CustomMessageWithId(String message, Boolean error, Long id) {
        this.message = message;
        this.error = error;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
