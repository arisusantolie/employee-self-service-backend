package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.FamilyRequestEntity;
import com.project.ess.model.FamilyNeedApproveResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FamilyRequestRepository extends JpaRepository<FamilyRequestEntity,Long> {


    @Query("select new com.project.ess.model.FamilyNeedApproveResponse(fam.familyId,famreq.requestDateTime,famreq.attachmentPath,famreq.requestData," +
            "famreq.status,famreq.approvedBy,famreq.approvedDatetime,famreq.fileName,famreq.requestNo," +
            "emp.employee.employeeNo,concat(emp.employee.firstName,' ',emp.employee.lastName) ) from FamilyEntity fam,FamilyRequestEntity famreq,EmploymentEntity emp " +
            "where fam.familyId=famreq.familyId and fam.employee=emp.employee and famreq.status='PENDING' and current_date>=emp.startDate and current_date<=emp.endDate " +
            "and emp.employeeDirectToId=:empManager order by famreq.requestDateTime asc")
    public List<FamilyNeedApproveResponse> getListFamilyNeedApprove(@Param("empManager")EmployeeEntity employeeEntity);

    @Query("select new com.project.ess.model.FamilyNeedApproveResponse(fam.familyId,famreq.requestDateTime,famreq.attachmentPath,famreq.requestData," +
            "famreq.status,famreq.approvedBy,famreq.approvedDatetime,famreq.fileName,famreq.requestNo," +
            "emp.employee.employeeNo,concat(emp.employee.firstName,' ',emp.employee.lastName) ) from FamilyEntity fam,FamilyRequestEntity famreq,EmploymentEntity emp " +
            "where fam.familyId=famreq.familyId and fam.employee=emp.employee and famreq.status!='PENDING' and current_date>=emp.startDate and current_date<=emp.endDate " +
            "and emp.employeeDirectToId=:empManager order by famreq.requestDateTime asc")
    public List<FamilyNeedApproveResponse> getListHistoryFamilyRequest(@Param("empManager")EmployeeEntity employeeEntity);
}
