package com.project.ess.entity.approval;

import com.project.ess.entity.AttempdailyEntity;
import com.project.ess.entity.BenefitRequestEntity;
import com.project.ess.entity.ManagerEntity;
import com.project.ess.entity.approval.compositekey.AttempdailyStatusId;

import javax.persistence.*;

@Entity
@Table(name = "attempdailyStatus")
@IdClass(AttempdailyStatusId.class)
public class AttempdailyStatus extends RequestEntity {

    @Id
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "employee_no"),
            @JoinColumn(name = "request_no")
    })
    private AttempdailyEntity attempdailyEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerEntity managerId;

    public AttempdailyEntity getAttempdailyEntity() {
        return attempdailyEntity;
    }

    public void setAttempdailyEntity(AttempdailyEntity attempdailyEntity) {
        this.attempdailyEntity = attempdailyEntity;
    }

    public ManagerEntity getManagerId() {
        return managerId;
    }

    public void setManagerId(ManagerEntity managerId) {
        this.managerId = managerId;
    }
}
