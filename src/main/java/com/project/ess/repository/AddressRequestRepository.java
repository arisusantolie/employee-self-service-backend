package com.project.ess.repository;

import com.project.ess.entity.AddressRequestEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.model.AddressNeedApproveResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRequestRepository extends JpaRepository<AddressRequestEntity,Long> {

    @Query("select new com.project.ess.model.AddressNeedApproveResponse(ae.addressId,are.attachmentPath,are.requestData,are.status" +
            ",are.approvedBy,are.approvedDatetime,are.fileName,emp.employee.employeeNo,concat(emp.employee.firstName,' ',emp.employee.lastName)," +
            "are.requestNo,are.requestDateTime ) " +
            "from AddressEntity ae,AddressRequestEntity are, EmploymentEntity  emp " +
            "where ae.addressId=are.addressId and ae.employee=emp.employee and are.status='PENDING' and current_date>=emp.startDate and current_date<=emp.endDate and emp.employeeDirectToId=:empManager" +
            " order by are.requestDateTime asc")
    public List<AddressNeedApproveResponse> getListAddressNeedApprove(@Param("empManager")EmployeeEntity employeeEntity);


    @Query("select new com.project.ess.model.AddressNeedApproveResponse(ae.addressId,are.attachmentPath,are.requestData,are.status" +
            ",are.approvedBy,are.approvedDatetime,are.fileName,emp.employee.employeeNo,concat(emp.employee.firstName,' ',emp.employee.lastName)," +
            "are.requestNo,are.requestDateTime ) " +
            "from AddressEntity ae,AddressRequestEntity are, EmploymentEntity  emp " +
            "where ae.addressId=are.addressId and ae.employee=emp.employee and are.status!='PENDING' and current_date>=emp.startDate and current_date<=emp.endDate and emp.employeeDirectToId=:empManager" +
            " order by are.requestDateTime asc")
    public List<AddressNeedApproveResponse> getListAddressHistory(@Param("empManager")EmployeeEntity employeeEntity);


}
