package com.project.ess.entity.approval.compositekey;

import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.compositekey.AbsenceId;

import java.io.Serializable;
import java.util.Objects;

public class AbsenceStatusId implements Serializable {



    private Long managerId;


    private AbsenceId absenceEntity;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }




    private static final long serialVersionUID = 4239980609226293562L;

    public AbsenceStatusId() {

    }

    public AbsenceId getAbsenceEntity() {
        return absenceEntity;
    }

    public void setAbsenceEntity(AbsenceId absenceEntity) {
        this.absenceEntity = absenceEntity;
    }


    public AbsenceStatusId(Long managerId, AbsenceId absenceEntity) {
        this.managerId = managerId;
        this.absenceEntity = absenceEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsenceStatusId that = (AbsenceStatusId) o;
        return Objects.equals(managerId, that.managerId) &&
                Objects.equals(absenceEntity, that.absenceEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerId, absenceEntity);
    }
}
