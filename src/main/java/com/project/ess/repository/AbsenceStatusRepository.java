package com.project.ess.repository;

import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.approval.AbsenceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceStatusRepository extends JpaRepository<AbsenceStatus,Long> {

    public AbsenceStatus findByAbsenceEntity(AbsenceEntity absenceEntity);
}
