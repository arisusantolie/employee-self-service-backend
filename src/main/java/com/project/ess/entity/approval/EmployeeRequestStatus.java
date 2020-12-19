package com.project.ess.entity.approval;

import com.project.ess.entity.BenefitRequestEntity;
import com.project.ess.entity.EmployeeRequestEntity;
import com.project.ess.entity.HRAdminEntity;
import com.project.ess.entity.ManagerEntity;
import com.project.ess.entity.approval.compositekey.EmployeeRequestStatusId;

import javax.persistence.*;

@Entity
@Table(name = "employeeRequestStatus")
@IdClass(EmployeeRequestStatusId.class)
public class EmployeeRequestStatus extends RequestEntity{


    @Id
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "employee_no"),
            @JoinColumn(name = "request_no")
    })
    private EmployeeRequestEntity employeeRequestEntity;


    @ManyToOne
    @JoinColumn(name = "hra_id")
    private HRAdminEntity hraId;

    public EmployeeRequestEntity getEmployeeRequestEntity() {
        return employeeRequestEntity;
    }

    public void setEmployeeRequestEntity(EmployeeRequestEntity employeeRequestEntity) {
        this.employeeRequestEntity = employeeRequestEntity;
    }

    public HRAdminEntity getHraId() {
        return hraId;
    }

    public void setHraId(HRAdminEntity hraId) {
        this.hraId = hraId;
    }
}
