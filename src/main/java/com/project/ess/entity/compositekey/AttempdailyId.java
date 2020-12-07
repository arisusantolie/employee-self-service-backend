package com.project.ess.entity.compositekey;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AttempdailyId implements Serializable {

    private Long employeeNo;
    private LocalDateTime actualTime;

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public AttempdailyId() {
    }

    public AttempdailyId(Long employeeNo, LocalDateTime actualTime) {
        this.employeeNo = employeeNo;
        this.actualTime = actualTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttempdailyId that = (AttempdailyId) o;
        return Objects.equals(employeeNo, that.employeeNo) &&
                Objects.equals(actualTime, that.actualTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, actualTime);
    }
}
