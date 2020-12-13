package com.project.ess.repository;

import com.project.ess.entity.BenefitBalanceEntity;
import com.project.ess.entity.BenefitRequestEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.model.BenefitNeedApproveResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BenefitRequestRepository extends JpaRepository<BenefitRequestEntity,Long> {

    public List<BenefitRequestEntity> findByBenefitBalanceIdOrderByRequestDateTimeDesc(BenefitBalanceEntity benefitBalanceEntity);


    @Query("select new com.project.ess.model.BenefitNeedApproveResponse(bre,concat(emp.employee.firstName,' ',emp.employee.lastName),emp.employee.employeeNo,bre.requestNo) " +
            "from BenefitRequestEntity bre,BenefitBalanceEntity bbe,EmploymentEntity emp " +
            "where bre.benefitBalanceId=bbe and bbe.employee=emp.employee and bre.status='PENDING'  and current_date>=emp.startDate and current_date<=emp.endDate and emp.employeeDirectToId=:employee order by bre.requestDateTime asc")
    public List<BenefitNeedApproveResponse> getListBenefitNeedApprove(@Param("employee") EmployeeEntity employeeNo);

    @Query("select new com.project.ess.model.BenefitNeedApproveResponse(bre,concat(emp.employee.firstName,' ',emp.employee.lastName),emp.employee.employeeNo,bre.requestNo) " +
            "from BenefitRequestEntity bre,BenefitBalanceEntity bbe,EmploymentEntity emp " +
            "where bre.benefitBalanceId=bbe and bbe.employee=emp.employee and bre.status!='PENDING'  and current_date>=emp.startDate and current_date<=emp.endDate and emp.employeeDirectToId=:employee order by bre.requestDateTime asc")
    public List<BenefitNeedApproveResponse> getListBenefitHistory(@Param("employee") EmployeeEntity employeeNo);


}
