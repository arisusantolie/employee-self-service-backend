package com.project.ess.entity;

import javax.persistence.*;

@Entity
@Table(name = "absenceType")
public class AbsenceTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long absenceTypeId;

    private String name;

    private String typeName;

    public Long getAbsenceTypeId() {
        return absenceTypeId;
    }

    public void setAbsenceTypeId(Long absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
