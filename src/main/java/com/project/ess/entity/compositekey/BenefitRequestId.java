package com.project.ess.entity.compositekey;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class BenefitRequestId implements Serializable {
    private Long benefitBalanceId;
    private LocalDateTime requestDateTime;

    public BenefitRequestId() {
    }


    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public BenefitRequestId(Long benefitBalanceId, LocalDateTime requestDateTime) {
        this.benefitBalanceId = benefitBalanceId;
        this.requestDateTime = requestDateTime;
    }

    public Long getBenefitBalanceId() {
        return benefitBalanceId;
    }

    public void setBenefitBalanceId(Long benefitBalanceId) {
        this.benefitBalanceId = benefitBalanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BenefitRequestId that = (BenefitRequestId) o;
        return Objects.equals(benefitBalanceId, that.benefitBalanceId) &&
                Objects.equals(requestDateTime, that.requestDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(benefitBalanceId, requestDateTime);
    }
}