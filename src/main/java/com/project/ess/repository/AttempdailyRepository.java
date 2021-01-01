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




}
