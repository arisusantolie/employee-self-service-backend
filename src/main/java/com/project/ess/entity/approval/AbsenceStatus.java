package com.project.ess.entity.approval;

import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.ManagerEntity;
import com.project.ess.entity.approval.compositekey.AbsenceStatusId;
import com.project.ess.entity.compositekey.AbsenceId;

import javax.persistence.*;

@Entity
@Table(name = "absenceStatus")
@IdClass(AbsenceStatusId.class)
public class AbsenceStatus extends RequestEntity{



    @Id
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "employee_no"),
            @JoinColumn(name = "request_no")
    })
    private AbsenceEntity absenceEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerEntity managerId;


    public AbsenceEntity getAbsenceEntity() {
        return absenceEntity;
    }

    public void setAbsenceEntity(AbsenceEntity absenceEntity) {
        this.absenceEntity = absenceEntity;
    }

    public ManagerEntity getManagerId() {
        return managerId;
    }

    public void setManagerId(ManagerEntity managerId) {
        this.managerId = managerId;
    }

}
