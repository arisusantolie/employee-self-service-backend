package com.project.ess.repository;

import com.project.ess.entity.AttempdailyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface AttempdailyRepository extends JpaRepository<AttempdailyEntity,Long> {

    @Query(value = "select * from attempdaily where employee_no=:employeeNo and date(actual_time)=:date",nativeQuery = true)
    public Optional<AttempdailyEntity> findByEmployeeNoAndActualTime(@Param("employeeNo") Long employeeNo,@Param("date") LocalDate date);
}
