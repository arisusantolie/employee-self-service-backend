package com.project.ess.repository;

import com.project.ess.entity.BenefitBalanceEntity;
import com.project.ess.entity.BenefitRequestEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.model.BenefitNeedApproveResponse;
import com.project.ess.model.BenefitRequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BenefitRequestRepository extends JpaRepository<BenefitRequestEntity,Long> {

//    public List<BenefitRequestEntity> findByBenefitBalanceIdOrderByRequestDateTimeDesc(BenefitBalanceEntity benefitBalanceEntity);

    @Query("select new com.project.ess.model.BenefitRequestResponse(brs.status,bre.fileName,bre.requestDateTime,brs.approvedDatetime,bre.requestNo," +
            "brs.managerId.managerId,bre.benefitBalanceId.benefitPlanEntity.benefitPlanId,bre.benefitBalanceId.benefitPlanEntity.name," +
            "concat(brs.managerId.employeeEntity.firstName,' ',brs.managerId.employeeEntity.lastName) ) " +
            "from BenefitRequestEntity bre,BenefitRequestStatus brs,ManagerEntity mgr where bre=brs.benefitRequestEntity and brs.managerId=mgr and " +
            "bre.benefitBalanceId=:benefitbalance order by bre.requestDateTime asc")
    public List<BenefitRequestResponse> findByBenefitBalanceIdOrderByRequestDateTimeAsc(@Param("benefitbalance") BenefitBalanceEntity benefitBalanceEntity);


    @Query("select new com.project.ess.model.BenefitNeedApproveResponse(bre,concat(bbe.employee.firstName,' ',bbe.employee.lastName),bbe.employee.employeeNo,bre.requestNo," +
            "brs.managerId.managerId,concat(brs.managerId.employeeEntity.firstName,' ',brs.managerId.employeeEntity.lastName),brs.approvedDatetime,brs.remark,brs.status ) " +
            "from BenefitRequestEntity bre,BenefitBalanceEntity bbe,BenefitRequestStatus brs " +
            "where bre.benefitBalanceId=bbe and bre=brs.benefitRequestEntity and brs.status='PENDING'  " +
            "and brs.managerId.employeeEntity=:employee order by bre.requestDateTime asc")
    public List<BenefitNeedApproveResponse> getListBenefitNeedApprove(@Param("employee") EmployeeEntity employeeNo);

    @Query("select new com.project.ess.model.BenefitNeedApproveResponse(bre,concat(bbe.employee.firstName,' ',bbe.employee.lastName),bbe.employee.employeeNo,bre.requestNo," +
            "brs.managerId.managerId,concat(brs.managerId.employeeEntity.firstName,' ',brs.managerId.employeeEntity.lastName),brs.approvedDatetime,brs.remark,brs.status ) " +
            "from BenefitRequestEntity bre,BenefitBalanceEntity bbe,BenefitRequestStatus brs " +
            "where bre.benefitBalanceId=bbe and bre=brs.benefitRequestEntity and brs.status!='PENDING'  " +
            "and brs.managerId.employeeEntity=:employee order by bre.requestDateTime asc")
    public List<BenefitNeedApproveResponse> getListBenefitHistory(@Param("employee") EmployeeEntity employeeNo);


}
