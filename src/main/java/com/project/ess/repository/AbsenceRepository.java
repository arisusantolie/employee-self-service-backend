package com.project.ess.repository;

import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<AbsenceEntity, Long> {

    public List<AbsenceEntity> findByEmployeeNoOrderByRequestDateTimeDesc(EmployeeEntity employeeNo);
}
