package com.project.ess.entity.approval.compositekey;

import com.project.ess.entity.BenefitBalanceEntity;
import com.project.ess.entity.BenefitRequestEntity;
import com.project.ess.entity.compositekey.BenefitRequestId;

import java.io.Serializable;
import java.util.Objects;

public class BenefitRequestStatusId implements Serializable {

    private Long managerId;

    private BenefitRequestId benefitRequestEntity;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public BenefitRequestId getBenefitRequestEntity() {
        return benefitRequestEntity;
    }

    public void setBenefitRequestEntity(BenefitRequestId benefitRequestEntity) {
        this.benefitRequestEntity = benefitRequestEntity;
    }

    public BenefitRequestStatusId(Long managerId, BenefitRequestId benefitRequestEntity) {
        this.managerId = managerId;
        this.benefitRequestEntity = benefitRequestEntity;
    }

    public BenefitRequestStatusId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BenefitRequestStatusId that = (BenefitRequestStatusId) o;
        return Objects.equals(managerId, that.managerId) &&
                Objects.equals(benefitRequestEntity, that.benefitRequestEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerId, benefitRequestEntity);
    }
}
