package com.project.ess.repository;

import com.project.ess.dto.AttempdailyRequestDTO;
import com.project.ess.entity.AttempdailyEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.model.AttempdailyNeedResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AttempdailyRepository extends JpaRepository<AttempdailyEntity,Long> {

    @Query(value = "select * from attempdaily where employee_no=:employeeNo and type=:type and date(actual_time)=:date",nativeQuery = true)
    public Optional<AttempdailyEntity> findByEmployeeNoAndActualTimeAndType(@Param("employeeNo") Long employeeNo,@Param("type") String type,@Param("date") LocalDate date);

    @Query("select new com.project.ess.dto.AttempdailyRequestDTO(u.type,u.actual_lng,u.actual_lat,u.isOutOffice,u.purpose,u.remark,u.actualTime,u.requestNo,ats.status) " +
            "from AttempdailyEntity u,AttempdailyStatus ats where u=ats.attempdailyEntity and u.employeeNo=:employee order by u.actualTime desc")
    public List<AttempdailyRequestDTO> findByEmployeeNoOrderByActualTime(@Param("employee") EmployeeEntity employeeEntity);
//
//    @Query(value = "select * from attempdaily where month(actual_time)=:month and year(actual_time)=:year",nativeQuery = true)
//    public List<AttempdailyEntity> getTimeSheet(@Param("month") int month, @Param("year") int year);
//
    @Query("select new com.project.ess.model.AttempdailyNeedResponse(ae,emp.employeeNo,concat(emp.firstName,' ',emp.lastName),ae.requestNo,ats.status,ats.remark ) " +
            "from AttempdailyEntity ae,AttempdailyStatus ats ,EmployeeEntity emp where ae.employeeNo=emp and ae=ats.attempdailyEntity and " +
            "ats.status='PENDING' and ats.managerId.employeeEntity=:employee order by ae.actualTime asc")
    public List<AttempdailyNeedResponse> getListCheckInCheckOutNeedApprove(@Param("employee") EmployeeEntity employeeEntity);

    @Query("select new com.project.ess.model.AttempdailyNeedResponse(ae,emp.employeeNo,concat(emp.firstName,' ',emp.lastName),ae.requestNo,ats.status,ats.remark ) " +
            "from AttempdailyEntity ae,AttempdailyStatus ats ,EmployeeEntity emp where ae.employeeNo=emp and ae=ats.attempdailyEntity and " +
            "ats.status!='PENDING' and ats.managerId.employeeEntity=:employee order by ae.actualTime asc")
    public List<AttempdailyNeedResponse> getListCheckInCheckOutHistory(@Param("employee") EmployeeEntity employeeEntity);

    @Query(value = "SELECT (SELECT DATE_FORMAT(atp.actual_time,\"%H:%i\") FROM attempdaily atp WHERE DATE(atp.actual_time)=DATE(attp.actual_time) AND atp.type=\"CHECKIN\" GROUP BY DATE(attp.`actual_time`)) AS checkinTime,\n" +
            "(SELECT DATE_FORMAT(atp.actual_time,\"%H:%i\")  FROM attempdaily atp WHERE DATE(atp.actual_time)=DATE(attp.actual_time) AND atp.type=\"CHECKOUT\" GROUP BY DATE(attp.`actual_time`)) AS checkoutTime,\n" +
            "ats.status,ats.remark,DATE_FORMAT(attp.actual_time,\"%W, %d-%b-%Y\") AS DATE FROM attempdaily attp,attempdaily_status ats WHERE attp.`request_no`=ats.request_no AND attp.employee_no=:employeeno AND DATE_FORMAT(attp.`actual_time`,\"%c\")=:month \n" +
            "AND DATE_FORMAT(attp.actual_time,\"%Y\")=:year AND ats.status!='PENDING' GROUP BY DATE(attp.`actual_time`);",nativeQuery = true)
    public List<Map<String,Object>> getListTimeSheet(@Param("employeeno") Long empNo,@Param("month") String month,@Param("year") String year);

    public Optional<AttempdailyEntity>  findByRequestNo(String requestNo);


    @Query(value = "SELECT emp.`employee_no`,CONCAT(emp.`first_name`,\" \",emp.last_name) AS fullname,employ.`position`, DATE_FORMAT(atp.`actual_time`,\"%Y-%m-%d\") date_check, DATE_FORMAT(atp.`actual_time`,\"%H:%i\") clock\n" +
            ",atp.`request_no`,atp.`remark` remark_request,atps.status,CONCAT(empmgr.first_name,\" \",empmgr.last_name) action_by,atps.approved_datetime,atps.remark remark_approved \n" +
            "FROM attempdaily atp,attempdaily_status atps,employee emp,employee empmgr,manager mgr,employment employ,divisi dv\n" +
            "WHERE atp.`request_no`=atps.`request_no` AND atp.`employee_no`=emp.`employee_no` \n" +
            "AND atps.`manager_id`=mgr.`manager_id` AND mgr.`employee_no`=empmgr.`employee_no` \n" +
            "AND atp.`employee_no`=employ.`employee_no` AND DATE_FORMAT(atp.`actual_time`,\"%Y-%m-%d\")>=employ.start_date AND DATE_FORMAT(atp.`actual_time`,\"%Y-%m-%d\")<=employ.end_date  AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(atp.`actual_time`,\"%Y-%m-%d\") BETWEEN :startDate AND :endDate \n" +
            "AND atps.`status`=nvl(:status,atps.`status`) AND atp.`employee_no`=nvl(:employeeNo,atp.`employee_no`) ORDER BY atp.`actual_time` ASC",nativeQuery = true)
    public List<Map<String,Object>> getReportAttempdaily(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status,@Param("employeeNo") Long employeeNo);



}
