package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.EmployeeRequestEntity;
import com.project.ess.model.EmployeeNeedApproveResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRequestRepository extends JpaRepository<EmployeeRequestEntity,Long> {

    public List<EmployeeRequestEntity> findByEmployeeNoOrderByRequestDateTime(EmployeeEntity employeeEntity);


    @Query("select new com.project.ess.model.EmployeeNeedApproveResponse(ere.employeeNo.employeeNo,ere.requestData,ere.status," +
            "ere.approvedBy,ere.approvedDatetime,ere.fileName,ere.requestNo,ere.attachment,ere.requestDateTime," +
            "concat(ere.employeeNo.firstName,' ',ere.employeeNo.lastName)) from EmployeeRequestEntity ere,EmploymentEntity emp" +
            " where ere.employeeNo=emp.employee and current_date>=emp.startDate and current_date<=emp.endDate and ere.status='PENDING' " +
            "and emp.employeeDirectToId=:empManager order by ere.requestDateTime asc")
    public List<EmployeeNeedApproveResponse> getListEmpNeedApprove(@Param("empManager") EmployeeEntity employee);

    @Query("select new com.project.ess.model.EmployeeNeedApproveResponse(ere.employeeNo.employeeNo,ere.requestData,ere.status," +
            "ere.approvedBy,ere.approvedDatetime,ere.fileName,ere.requestNo,ere.attachment,ere.requestDateTime," +
            "concat(ere.employeeNo.firstName,' ',ere.employeeNo.lastName)) from EmployeeRequestEntity ere,EmploymentEntity emp" +
            " where ere.employeeNo=emp.employee and current_date>=emp.startDate and current_date<=emp.endDate and ere.status!='PENDING' " +
            "and emp.employeeDirectToId=:empManager order by ere.requestDateTime asc")
    public List<EmployeeNeedApproveResponse> getListEmpHistory(@Param("empManager") EmployeeEntity employee);


}
