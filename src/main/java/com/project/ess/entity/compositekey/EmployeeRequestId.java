package com.project.ess.entity.compositekey;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EmployeeRequestId implements Serializable {

    private Long employeeNo;
    private String requestNo;

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }


    public EmployeeRequestId(Long employeeNo, String requestNo) {
        this.employeeNo = employeeNo;
        this.requestNo = requestNo;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public EmployeeRequestId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeRequestId that = (EmployeeRequestId) o;
        return Objects.equals(employeeNo, that.employeeNo) &&
                Objects.equals(requestNo, that.requestNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, requestNo);
    }
}
