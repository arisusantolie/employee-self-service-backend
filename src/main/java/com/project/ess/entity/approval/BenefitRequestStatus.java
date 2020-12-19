package com.project.ess.entity.approval;

import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.BenefitRequestEntity;
import com.project.ess.entity.ManagerEntity;
import com.project.ess.entity.approval.compositekey.BenefitRequestStatusId;

import javax.persistence.*;

@Entity
@Table(name = "benefitRequestStatus")
@IdClass(BenefitRequestStatusId.class)
public class BenefitRequestStatus extends RequestEntity{

    @Id
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "benefit_balance_id"),
            @JoinColumn(name = "request_no")
    })
    private BenefitRequestEntity benefitRequestEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerEntity managerId;

    public BenefitRequestEntity getBenefitRequestEntity() {
        return benefitRequestEntity;
    }

    public void setBenefitRequestEntity(BenefitRequestEntity benefitRequestEntity) {
        this.benefitRequestEntity = benefitRequestEntity;
    }

    public ManagerEntity getManagerId() {
        return managerId;
    }

    public void setManagerId(ManagerEntity managerId) {
        this.managerId = managerId;
    }
}
