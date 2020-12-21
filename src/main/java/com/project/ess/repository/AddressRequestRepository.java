package com.project.ess.repository;

import com.project.ess.entity.AddressRequestEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.model.AddressNeedApproveResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRequestRepository extends JpaRepository<AddressRequestEntity,Long> {

    @Query("select new com.project.ess.model.AddressNeedApproveResponse(ae.addressId,are.attachmentPath,are.requestData,ars.status" +
            ",ars.approvedDatetime,are.fileName,emp.employeeNo,concat(emp.firstName,' ',emp.lastName)," +
            "are.requestNo,are.requestDateTime) " +
            "from AddressEntity ae,AddressRequestEntity are, EmployeeEntity  emp, AddressRequestStatus ars " +
            "where ae.employee=emp and ae.addressId=are.addressId and are.requestNo=ars.requestNo and ars.status='PENDING' and ae.employee=:employee " +
            " order by are.requestDateTime asc")
    public List<AddressNeedApproveResponse> getListAddressRequestEmp(@Param("employee")EmployeeEntity employeeEntity);

    @Query("select new com.project.ess.model.AddressNeedApproveResponse(ae.addressId,are.attachmentPath,are.requestData,ars.status" +
            ",ars.approvedDatetime,are.fileName,ae.employee.employeeNo,concat(ae.employee.firstName,' ',ae.employee.lastName)," +
            "are.requestNo,are.requestDateTime) " +
            "from AddressEntity ae,AddressRequestEntity are, AddressRequestStatus ars " +
            "where ae=are.addressId and are=ars.addressRequestEntity and ars.status='PENDING'" +
            " order by are.requestDateTime asc")
    public List<AddressNeedApproveResponse> getListAddressNeedApprove(@Param("empManager")EmployeeEntity employeeEntity);




    @Query("select new com.project.ess.model.AddressNeedApproveResponse(ae.addressId,are.attachmentPath,are.requestData,ars.status" +
            ",ars.approvedDatetime,are.fileName,emp.employeeNo,concat(emp.firstName,' ',emp.lastName)," +
            "are.requestNo,are.requestDateTime) " +
            "from AddressEntity ae,AddressRequestEntity are, EmployeeEntity  emp, AddressRequestStatus ars " +
            "where ae.employee=emp and ae.addressId=are.addressId and are.requestNo=ars.requestNo and ars.status!='PENDING' and ars.hraId.employeeEntity=:empno  " +
            " order by are.requestDateTime asc")
    public List<AddressNeedApproveResponse> getListAddressHistory(@Param("empno") EmployeeEntity empNo);


}
