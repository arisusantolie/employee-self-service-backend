package com.project.ess.entity.compositekey;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FamilyRequestId implements Serializable {

    private Long familyId;
    private String requestNo;

    public FamilyRequestId() {
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public FamilyRequestId(Long familyId, String requestNo) {
        this.familyId = familyId;
        this.requestNo = requestNo;
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
        FamilyRequestId that = (FamilyRequestId) o;
        return Objects.equals(familyId, that.familyId) &&
                Objects.equals(requestNo, that.requestNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, requestNo);
    }
}
