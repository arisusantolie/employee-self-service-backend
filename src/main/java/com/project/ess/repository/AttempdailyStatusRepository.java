package com.project.ess.repository;

import com.project.ess.entity.AttempdailyEntity;
import com.project.ess.entity.approval.AttempdailyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttempdailyStatusRepository extends JpaRepository<AttempdailyStatus,Long > {

    public AttempdailyStatus findByAttempdailyEntity(AttempdailyEntity attempdailyEntity);

    @Query(value = "SELECT * FROM attempdaily atp,attempdaily_status ats WHERE atp.`request_no`=ats.`request_no` AND \n" +
            "DATE_FORMAT(actual_time,\"%Y-%m-%d\")=(SELECT DATE_FORMAT(actual_time,\"%Y-%m-%d\") FROM attempdaily WHERE request_no=:requestNo AND employee_no=:employeeNo) \n" +
            "AND atp.`request_no`!=:requestNo",nativeQuery = true)
    public AttempdailyStatus getDataAttempdailyForApprove(@Param("requestNo")String requestNo,@Param("employeeNo") Long empNo);

}
