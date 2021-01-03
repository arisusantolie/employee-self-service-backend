package com.project.ess.repository;

import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.EmploymentEntity;
import com.project.ess.model.AbsenceNeedApproveResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

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


   @Query(value = "SELECT emp.employee_no,CONCAT(emp.`first_name`,\" \",emp.last_name) AS fullname,employ.`position`,ab.`request_no`,abt.name request_type,\n" +
           "DATE_FORMAT(ab.`request_date_time`,\"%Y-%m-%d\")request_date,ab.`start_date`,ab.`end_date`,DATEDIFF(ab.`end_date`,ab.`start_date`)+1 days,\n" +
           "abst.status,CONCAT(empmgr.first_name,\" \",empmgr.last_name) action_by,abst.approved_datetime,abst.remark\n" +
           " FROM absence ab, absence_status abst,absence_type abt,employee emp,manager mgr,employee empmgr,employment employ,divisi dv \n" +
           "WHERE abt.`absence_type_id`=ab.`absence_type_id` \n" +
           "AND ab.`request_no`=abst.`request_no` AND ab.`employee_no`=emp.`employee_no` AND abst.`manager_id`=mgr.`manager_id` \n" +
           "AND mgr.`employee_no`=empmgr.`employee_no` AND emp.`employee_no`=employ.`employee_no` AND ab.end_date>=employ.start_date AND ab.end_date<=employ.end_date \n" +
           "AND employ.`divisi_id`=dv.`divisi_id`\n" +
           "AND DATE_FORMAT(ab.`request_date_time`,\"%Y-%m-%d\") BETWEEN :startDate AND :endDate \n" +
           "AND abst.`status`=nvl(:status,abst.`status`) AND ab.`employee_no`=nvl(:employeeNo,ab.`employee_no`); ",nativeQuery = true)
   public List<Map<String,Object>> getAbsenceReport(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status,@Param("employeeNo") Long employeeNo);
}
