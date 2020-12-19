package com.project.ess.entity.compositekey;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class BenefitRequestId implements Serializable {
    private Long benefitBalanceId;
    private String requestNo;

    public BenefitRequestId() {
    }

    public BenefitRequestId(Long benefitBalanceId, String requestNo) {
        this.benefitBalanceId = benefitBalanceId;
        this.requestNo = requestNo;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
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
                Objects.equals(requestNo, that.requestNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(benefitBalanceId, requestNo);
    }
}