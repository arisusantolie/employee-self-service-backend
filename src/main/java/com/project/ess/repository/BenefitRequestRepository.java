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
import java.util.Map;
import java.util.Optional;

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


    public Optional<BenefitRequestEntity>  findByRequestNo(String requestNo);


    @Query(value ="SELECT emp.`employee_no`,CONCAT(emp.`first_name`,\" \",emp.last_name) AS fullname,employ.`position`,br.`request_no`,br.`transaction_date`,bp.`name` claim_type,br.`benefit_reason`,br.`amount`,br.`request_date_time`,\n" +
            "br.`remark` remark_request,brs.`status`,CONCAT(empmgr.first_name,\" \",empmgr.last_name) action_by,brs.approved_datetime,brs.`remark` remark_approved\n" +
            "FROM benefit_plan bp,benefit_balance bb, benefit_request br,`benefit_request_status` brs ,employee emp,employee empmgr,manager mgr,employment employ,divisi dv\n" +
            "WHERE br.`benefit_balance_id`=bb.`benefit_balance_id` AND bb.`benefit_plan_id`=bp.`benefit_plan_id` AND br.`request_no`=brs.`request_no` \n" +
            "AND bb.`employee_no`=emp.`employee_no` AND brs.`manager_id`=mgr.`manager_id` AND mgr.`employee_no`=empmgr.`employee_no` \n" +
            "AND bb.`employee_no`=employ.`employee_no` AND DATE_FORMAT(br.`request_date_time`,\"%Y-%m-%d\")>=employ.start_date AND DATE_FORMAT(br.`request_date_time`,\"%Y-%m-%d\")<=employ.end_date AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(br.`request_date_time` ,\"%Y-%m-%d\") BETWEEN :startDate AND :endDate \n" +
            "AND brs.`status`=nvl(:status,brs.`status`) AND bb.`employee_no`=nvl(:employeeNo,bb.`employee_no`); " ,nativeQuery = true)
    public List<Map<String,Object>> getBenefitReport(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status,@Param("employeeNo") Long employeeNo);
}
