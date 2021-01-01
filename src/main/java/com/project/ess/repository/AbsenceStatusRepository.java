package com.project.ess.repository;

import com.project.ess.dto.AbsenceStatusDTO;
import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.approval.AbsenceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AbsenceStatusRepository extends JpaRepository<AbsenceStatus,Long> {

    @Query("select new com.project.ess.dto.AbsenceStatusDTO(concat(u.managerId.employeeEntity.firstName, ' ',u.managerId.employeeEntity.lastName),u.approvedDatetime,u.remark" +
            ",u.status) from AbsenceStatus u where u.absenceEntity =:absenceEntity")
    public AbsenceStatusDTO findByAbsenceEntityDTO(AbsenceEntity absenceEntity);


    public AbsenceStatus findByAbsenceEntity(AbsenceEntity absenceEntity);


}
