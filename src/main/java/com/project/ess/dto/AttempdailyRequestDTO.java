package com.project.ess.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AttempdailyRequestDTO {

    private String type;
    private BigDecimal actual_lng;
    private BigDecimal actual_lat;
    private Boolean isOutOffice;
    private String purpose;
    private String remark;
    private LocalDateTime actualTime;
    private String requestNo;
    private String status;


    public AttempdailyRequestDTO() {
    }

    public AttempdailyRequestDTO(String type, BigDecimal actual_lng, BigDecimal actual_lat, Boolean isOutOffice, String purpose, String remark, LocalDateTime actualTime, String requestNo,String status) {
        this.type = type;
        this.actual_lng = actual_lng;
        this.actual_lat = actual_lat;
        this.isOutOffice = isOutOffice;
        this.purpose = purpose;
        this.remark = remark;
        this.actualTime = actualTime;
        this.requestNo = requestNo;
        this.status=status;

    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getActual_lng() {
        return actual_lng;
    }

    public void setActual_lng(BigDecimal actual_lng) {
        this.actual_lng = actual_lng;
    }

    public BigDecimal getActual_lat() {
        return actual_lat;
    }

    public void setActual_lat(BigDecimal actual_lat) {
        this.actual_lat = actual_lat;
    }

    public Boolean getOutOffice() {
        return isOutOffice;
    }

    public void setOutOffice(Boolean outOffice) {
        isOutOffice = outOffice;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
