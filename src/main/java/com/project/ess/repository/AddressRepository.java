package com.project.ess.repository;

import com.project.ess.entity.AddressEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.model.AddressResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface AddressRepository extends JpaRepository<AddressEntity,Long> {


    @Modifying
    @Query("Update AddressEntity u set u.primaryFlag=false where employee.employeeNo=:employeeno")
    public void setPrimaryFlagAddressFalse(@Param("employeeno") Long employeeNo);

    @Query(value = "SELECT ad.*,ars.`status`,adr.`request_date_time` FROM address ad, address_request adr,address_request_status ars WHERE ad.`address_id`=adr.`address_id` \n" +
            "AND ad.`employee_no`=:employee AND adr.`address_id`=ars.address_id AND adr.`request_no`=ars.request_no  AND (adr.address_id,adr.request_date_time) IN (SELECT ar.address_id,MAX(ar.request_date_time) FROM \n" +
            "address_request ar,address_request_status adr WHERE ar.`request_no`=adr.`request_no` AND adr.status=\"APPROVED\" GROUP BY ar.address_id)",nativeQuery = true)
    public List<Map<String,Object>> getAllListAddress(@Param("employee") Long empNo);

    @Modifying
    @Query("delete from AddressEntity where addressId=:address")
    public void deleteAddressEntity(@Param("address") Long addressId);
}

