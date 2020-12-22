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

    @Query("select new com.project.ess.model.AbsenceNeedApproveResponse(ab,emp.employee.employeeNo,concat(emp.employee.firstName,' ',emp.employee.lastName),ab.requestNo,abstat.status) " +
            "from AbsenceEntity ab,AbsenceStatus abstat, " +
            " EmploymentEntity emp,DivisiEntity de,ManagerEntity me where ab.employeeNo=emp.employee " +
            "  and current_date>=emp.startDate and current_date<=emp.endDate and ab=abstat.absenceEntity and emp.divisiEntity=de and me.divisiEntity=de" +
            " and me.managerId=:managerId and abstat.status ='PENDING' order by ab.requestDateTime asc")
    public List<AbsenceNeedApproveResponse> getListAbsenceNeedApprove(@Param("managerId")Long managerId);

    @Query("select new com.project.ess.model.AbsenceNeedApproveResponse(ab,emp.employee.employeeNo,concat(emp.employee.firstName,' ',emp.employee.lastName),ab.requestNo,abstat.status) " +
            "from AbsenceEntity ab,AbsenceStatus abstat, " +
            " EmploymentEntity emp,DivisiEntity de,ManagerEntity me where ab.employeeNo=emp.employee " +
            "  and current_date>=emp.startDate and current_date<=emp.endDate and ab=abstat.absenceEntity and emp.divisiEntity=de and me.divisiEntity=de" +
            " and me.managerId=:managerId and abstat.status !='PENDING' order by ab.requestDateTime asc")
    public List<AbsenceNeedApproveResponse> getListHistoryAbsence(@Param("managerId")Long managerId);

   public AbsenceEntity findByRequestNo(String requestNo);
}
