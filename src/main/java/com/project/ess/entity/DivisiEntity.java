package com.project.ess.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "divisi")
public class DivisiEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long divisiId;

    private String divisiName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;

    @OneToMany(mappedBy = "divisiEntity")
    private List<ManagerEntity> managerEntity;

    public List<ManagerEntity> getManagerEntity() {
        return managerEntity;
    }

    public void setManagerEntity(List<ManagerEntity> managerEntity) {
        this.managerEntity = managerEntity;
    }

    public Long getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(Long divisiId) {
        this.divisiId = divisiId;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }
}
