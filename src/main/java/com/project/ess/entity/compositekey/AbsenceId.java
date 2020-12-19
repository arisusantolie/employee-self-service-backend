package com.project.ess.entity.compositekey;

import com.project.ess.entity.EmployeeEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AbsenceId implements Serializable {

    private Long employeeNo;
    private String requestNo;


    public AbsenceId() {
    }

    public AbsenceId(Long employeeNo, String requestNo) {
        this.employeeNo = employeeNo;
        this.requestNo = requestNo;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsenceId absenceId = (AbsenceId) o;
        return Objects.equals(employeeNo, absenceId.employeeNo) &&
                Objects.equals(requestNo, absenceId.requestNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, requestNo);
    }
}
