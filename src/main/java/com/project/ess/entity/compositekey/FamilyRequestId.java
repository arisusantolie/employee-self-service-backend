package com.project.ess.entity.compositekey;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FamilyRequestId implements Serializable {

    private Long familyId;
    private LocalDateTime requestDateTime;

    public FamilyRequestId(Long familyId, LocalDateTime requestDateTime) {
        this.familyId = familyId;
        this.requestDateTime = requestDateTime;
    }

    public FamilyRequestId() {
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
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
        FamilyRequestId that = (FamilyRequestId) o;
        return Objects.equals(familyId, that.familyId) &&
                Objects.equals(requestDateTime, that.requestDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, requestDateTime);
    }
}
