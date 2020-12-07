package com.project.ess.repository;

import com.project.ess.entity.AddressEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.model.AddressResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface AddressRepository extends JpaRepository<AddressEntity,Long> {


    @Modifying
    @Query("Update AddressEntity u set u.primaryFlag=false where employee.employeeNo=:employeeno")
    public void setPrimaryFlagAddressFalse(@Param("employeeno") Long employeeNo);

    @Query(value = "SELECT ad.*,adr.`status`,adr.`request_date_time` FROM address ad, address_request adr WHERE ad.`address_id`=adr.`address_id` " +
            "AND ad.`employee_no`=:employee AND (adr.address_id,adr.request_date_time) IN (SELECT address_id,MAX(request_date_time) FROM " +
            "address_request GROUP BY address_id)",nativeQuery = true)
    public List<Map<String,Object>> getAllListAddress(@Param("employee") Long empNo);
}

