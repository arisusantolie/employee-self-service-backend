package com.project.ess.repository;

import com.project.ess.entity.AbsenceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsenceTypeRepository extends JpaRepository<AbsenceTypeEntity,Long> {

    public List<AbsenceTypeEntity> findByTypeName(String name);
}
