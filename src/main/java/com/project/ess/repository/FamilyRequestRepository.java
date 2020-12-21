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
            "frs.status,frs.approvedDatetime,famreq.fileName,famreq.requestNo," +
            "fam.employee.employeeNo,concat(fam.employee.firstName,' ',fam.employee.lastName) ) from FamilyEntity fam,FamilyRequestEntity famreq,FamilyRequestStatus frs " +
            "where fam=famreq.familyId and famreq=frs.familyRequestEntity  and fam.employee=:employee and frs.status='PENDING' " +
            "order by famreq.requestDateTime asc")
    public List<FamilyNeedApproveResponse> getListFamilyRequest(@Param("employee")EmployeeEntity employeeEntity);


    @Query("select new com.project.ess.model.FamilyNeedApproveResponse(fam.familyId,famreq.requestDateTime,famreq.attachmentPath,famreq.requestData," +
            "frs.status,frs.approvedDatetime,famreq.fileName,famreq.requestNo," +
            "fam.employee.employeeNo,concat(fam.employee.firstName,' ',fam.employee.lastName) ) from FamilyEntity fam,FamilyRequestEntity famreq,FamilyRequestStatus frs " +
            "where fam=famreq.familyId and famreq=frs.familyRequestEntity and frs.status='PENDING' " +
            "order by famreq.requestDateTime asc")
    public List<FamilyNeedApproveResponse> getListFamilyNeedApprove(@Param("empManager")EmployeeEntity employeeEntity);

    @Query("select new com.project.ess.model.FamilyNeedApproveResponse(fam.familyId,famreq.requestDateTime,famreq.attachmentPath,famreq.requestData," +
            "frs.status,frs.approvedDatetime,famreq.fileName,famreq.requestNo," +
            "fam.employee.employeeNo,concat(fam.employee.firstName,' ',fam.employee.lastName) ) from FamilyEntity fam,FamilyRequestEntity famreq,FamilyRequestStatus frs " +
            "where fam=famreq.familyId and famreq=frs.familyRequestEntity and frs.hraId.employeeEntity=:employee and frs.status!='PENDING' " +
            "order by frs.approvedDatetime desc")
    public List<FamilyNeedApproveResponse> getListHistoryFamilyRequest(@Param("employee")EmployeeEntity employeeEntity);
}
