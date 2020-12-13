package com.project.ess.repository;

import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.EmploymentEntity;
import com.project.ess.model.AbsenceNeedApproveResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<AbsenceEntity, Long> {

    public List<AbsenceEntity> findByEmployeeNoOrderByRequestDateTimeDesc(EmployeeEntity employeeNo);

    @Query("select new com.project.ess.model.AbsenceNeedApproveResponse(ab,emp.employee.employeeNo,concat(emp.employee.firstName,' ',emp.employee.lastName),ab.requestNo) from AbsenceEntity ab,EmploymentEntity emp where ab.employeeNo=emp.employee " +
            "  and current_date>=emp.startDate and current_date<=emp.endDate and emp.employeeDirectToId.employeeNo=:employee and ab.status='PENDING' order by ab.requestDateTime asc")
    public List<AbsenceNeedApproveResponse> getListAbsenceNeedApprove(@Param("employee")Long empNo);

    @Query("select new com.project.ess.model.AbsenceNeedApproveResponse(ab,emp.employee.employeeNo,concat(emp.employee.firstName,' ',emp.employee.lastName),ab.requestNo) from AbsenceEntity ab,EmploymentEntity emp where ab.employeeNo=emp.employee " +
            "  and current_date>=emp.startDate and current_date<=emp.endDate and emp.employeeDirectToId.employeeNo=:employee and ab.status!='PENDING' order by ab.requestDateTime asc")
    public List<AbsenceNeedApproveResponse> getListHistoryAbsence(@Param("employee")Long empNo);
}
