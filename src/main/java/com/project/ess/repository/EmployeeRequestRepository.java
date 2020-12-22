package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.EmployeeRequestEntity;
import com.project.ess.model.EmployeeNeedApproveResponse;
import com.project.ess.model.EmployeeRequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRequestRepository extends JpaRepository<EmployeeRequestEntity,Long> {
//
    @Query("select new com.project.ess.model.EmployeeRequestResponse(ere.employeeNo.employeeNo,ere.requestNo,ere.fileName,ere.requestDateTime,ers.status,ers.approvedDatetime," +
            "ers.remark,ere.requestData,ere.attachment)" +
            " from EmployeeRequestEntity ere,EmployeeRequestStatus ers where ere=ers.employeeRequestEntity and ere.employeeNo=:employee " +
            "order by ere.requestDateTime desc")
    public List<EmployeeRequestResponse> findData(@Param("employee") EmployeeEntity employeeEntity);



    @Query("select new com.project.ess.model.EmployeeNeedApproveResponse(ere.employeeNo.employeeNo,ere.requestData,ers.status,ers.approvedDatetime,ere.fileName,ere.requestNo,ere.attachment," +
            "ere.requestDateTime,concat(ere.employeeNo.firstName,' ',ere.employeeNo.lastName) )" +
            " from EmployeeRequestEntity ere,EmployeeRequestStatus ers where ere=ers.employeeRequestEntity and ers.status='PENDING' " +
            " order by ere.requestDateTime asc")
    public List<EmployeeNeedApproveResponse> getListEmpNeedApprove();

    @Query("select new com.project.ess.model.EmployeeNeedApproveResponse(ere.employeeNo.employeeNo,ere.requestData,ers.status,ers.approvedDatetime,ere.fileName,ere.requestNo,ere.attachment," +
            "ere.requestDateTime,concat(ere.employeeNo.firstName,' ',ere.employeeNo.lastName) )" +
            " from EmployeeRequestEntity ere,EmployeeRequestStatus ers where ere=ers.employeeRequestEntity and ers.hraId.employeeEntity=:employee and ers.status!='PENDING' " +
            " order by ere.requestDateTime asc")
    public List<EmployeeNeedApproveResponse> getListEmpHistory(@Param("employee") EmployeeEntity employee);



    public EmployeeRequestEntity findByRequestNo(String requestNo);
}
