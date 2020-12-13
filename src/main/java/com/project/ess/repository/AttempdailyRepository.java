package com.project.ess.repository;

import com.project.ess.entity.AttempdailyEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.model.AttempdailyNeedResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttempdailyRepository extends JpaRepository<AttempdailyEntity,Long> {

    @Query(value = "select * from attempdaily where employee_no=:employeeNo and type=:type and date(actual_time)=:date",nativeQuery = true)
    public Optional<AttempdailyEntity> findByEmployeeNoAndActualTimeAndType(@Param("employeeNo") Long employeeNo,@Param("type") String type,@Param("date") LocalDate date);

    public List<AttempdailyEntity> findByEmployeeNoOrderByActualTime(EmployeeEntity employeeEntity);

    @Query(value = "select * from attempdaily where month(actual_time)=:month and year(actual_time)=:year",nativeQuery = true)
    public List<AttempdailyEntity> getTimeSheet(@Param("month") int month, @Param("year") int year);

    @Query("select new com.project.ess.model.AttempdailyNeedResponse(ae,emp.employee.employeeNo,concat(emp.employee.firstName,' ',emp.employee.lastName),ae.requestNo ) from AttempdailyEntity ae,EmploymentEntity emp where " +
            "ae.employeeNo=emp.employee and ae.status='PENDING'  and current_date>=emp.startDate and current_date<=emp.endDate and emp.employeeDirectToId=:employee order by ae.actualTime asc")
    public List<AttempdailyNeedResponse> getListCheckInCheckOutNeedApprove(@Param("employee") EmployeeEntity employeeEntity);

    @Query("select new com.project.ess.model.AttempdailyNeedResponse(ae,emp.employee.employeeNo,concat(emp.employee.firstName,' ',emp.employee.lastName),ae.requestNo ) from AttempdailyEntity ae,EmploymentEntity emp where " +
            "ae.employeeNo=emp.employee and ae.status!='PENDING'  and current_date>=emp.startDate and current_date<=emp.endDate and emp.employeeDirectToId=:employee order by ae.actualTime asc")
    public List<AttempdailyNeedResponse> getListCheckInCheckOutHistory(@Param("employee") EmployeeEntity employeeEntity);
}
