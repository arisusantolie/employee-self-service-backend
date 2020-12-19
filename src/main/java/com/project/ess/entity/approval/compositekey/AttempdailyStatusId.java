package com.project.ess.entity.approval.compositekey;

import com.project.ess.entity.AttempdailyEntity;
import com.project.ess.entity.compositekey.AttempdailyId;

import java.io.Serializable;
import java.util.Objects;

public class AttempdailyStatusId implements Serializable {

    private Long managerId;

    private AttempdailyId attempdailyEntity;


    public AttempdailyId getAttempdailyEntity() {
        return attempdailyEntity;
    }

    public void setAttempdailyEntity(AttempdailyId attempdailyEntity) {
        this.attempdailyEntity = attempdailyEntity;
    }

    public AttempdailyStatusId() {
    }

    public AttempdailyStatusId(Long managerId, AttempdailyId attempdailyEntity) {
        this.managerId = managerId;
        this.attempdailyEntity = attempdailyEntity;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttempdailyStatusId that = (AttempdailyStatusId) o;
        return Objects.equals(managerId, that.managerId) &&
                Objects.equals(attempdailyEntity, that.attempdailyEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerId, attempdailyEntity);
    }
}
