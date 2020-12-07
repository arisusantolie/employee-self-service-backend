package com.project.ess.dto;

import java.math.BigDecimal;

public class AttempdailyDTO {
    private String type;
    private BigDecimal actual_lng;
    private BigDecimal actual_lat;
    private Boolean isOutOffice;
    private String purpose;
    private String remark;

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
}
