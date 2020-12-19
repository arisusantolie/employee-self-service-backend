package com.project.ess.entity.approval.compositekey;

import com.project.ess.entity.FamilyRequestEntity;
import com.project.ess.entity.compositekey.FamilyRequestId;

import java.io.Serializable;
import java.util.Objects;

public class FamilyRequestStatusId implements Serializable {

    private FamilyRequestId familyRequestEntity;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyRequestStatusId that = (FamilyRequestStatusId) o;
        return Objects.equals(familyRequestEntity, that.familyRequestEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyRequestEntity);
    }

    public FamilyRequestStatusId(FamilyRequestId familyRequestEntity) {
        this.familyRequestEntity = familyRequestEntity;
    }

    public FamilyRequestId getFamilyRequestEntity() {
        return familyRequestEntity;
    }

    public void setFamilyRequestEntity(FamilyRequestId familyRequestEntity) {
        this.familyRequestEntity = familyRequestEntity;
    }

    public FamilyRequestStatusId() {
    }




}
