package com.project.ess.entity.approval;

import com.project.ess.entity.BenefitRequestEntity;
import com.project.ess.entity.FamilyRequestEntity;
import com.project.ess.entity.HRAdminEntity;
import com.project.ess.entity.ManagerEntity;
import com.project.ess.entity.approval.compositekey.FamilyRequestStatusId;

import javax.persistence.*;

@Entity
@Table(name = "familyRequestStatus")
@IdClass(FamilyRequestStatusId.class)
public class FamilyRequestStatus extends RequestEntity{


    @Id
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "family_id"),
            @JoinColumn(name = "request_no")
    })
    private FamilyRequestEntity familyRequestEntity;


    @ManyToOne
    @JoinColumn(name = "hra_id")
    private HRAdminEntity hraId;

    public FamilyRequestEntity getFamilyRequestEntity() {
        return familyRequestEntity;
    }

    public void setFamilyRequestEntity(FamilyRequestEntity familyRequestEntity) {
        this.familyRequestEntity = familyRequestEntity;
    }

    public HRAdminEntity getHraId() {
        return hraId;
    }

    public void setHraId(HRAdminEntity hraId) {
        this.hraId = hraId;
    }
}
