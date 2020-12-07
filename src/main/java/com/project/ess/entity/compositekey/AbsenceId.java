package com.project.ess.entity.compositekey;

import com.project.ess.entity.EmployeeEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AbsenceId implements Serializable {

    private Long employeeNo;
    private LocalDateTime requestDateTime;


    public AbsenceId() {
    }

    public AbsenceId(Long employeeNo, LocalDateTime requestDateTime) {
        this.employeeNo = employeeNo;
        this.requestDateTime = requestDateTime;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsenceId absenceId = (AbsenceId) o;
        return Objects.equals(employeeNo, absenceId.employeeNo) &&
                Objects.equals(requestDateTime, absenceId.requestDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, requestDateTime);
    }
}
