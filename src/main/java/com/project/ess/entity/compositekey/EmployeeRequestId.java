package com.project.ess.entity.compositekey;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EmployeeRequestId implements Serializable {

    private Long employeeNo;
    private LocalDateTime requestDateTime;

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

    public EmployeeRequestId(Long employeeNo, LocalDateTime requestDateTime) {
        this.employeeNo = employeeNo;
        this.requestDateTime = requestDateTime;
    }

    public EmployeeRequestId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeRequestId that = (EmployeeRequestId) o;
        return Objects.equals(employeeNo, that.employeeNo) &&
                Objects.equals(requestDateTime, that.requestDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, requestDateTime);
    }
}
