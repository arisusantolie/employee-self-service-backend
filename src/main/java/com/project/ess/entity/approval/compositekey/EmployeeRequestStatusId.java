package com.project.ess.entity.approval.compositekey;

import com.project.ess.entity.EmployeeRequestEntity;
import com.project.ess.entity.compositekey.EmployeeRequestId;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeRequestStatusId implements Serializable {


    private EmployeeRequestId employeeRequestEntity;



    public EmployeeRequestStatusId(Long hraId, EmployeeRequestId employeeRequestEntity) {

        this.employeeRequestEntity = employeeRequestEntity;
    }

    public EmployeeRequestId getEmployeeRequestEntity() {
        return employeeRequestEntity;
    }

    public void setEmployeeRequestEntity(EmployeeRequestId employeeRequestEntity) {
        this.employeeRequestEntity = employeeRequestEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeRequestStatusId that = (EmployeeRequestStatusId) o;
        return Objects.equals(employeeRequestEntity, that.employeeRequestEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeRequestEntity);
    }

    public EmployeeRequestStatusId() {
    }
}
