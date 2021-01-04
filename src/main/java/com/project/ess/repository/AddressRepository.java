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
            "address_request ar,address_request_status adr WHERE ar.`request_no`=adr.`request_no` GROUP BY ar.address_id)",nativeQuery = true)
    public List<Map<String,Object>> getAllListAddress(@Param("employee") Long empNo);

    @Modifying
    @Query("delete from AddressEntity where addressId=:address")
    public void deleteAddressEntity(@Param("address") Long addressId);


    @Query(value = "SELECT emp.`employee_no`,CONCAT(emp.`first_name`,\" \",emp.last_name) AS fullname,employ.`position`,adr.`request_date_time`,adr.`request_no`,adrs.`status`,\n" +
            "CONCAT(empmgr.first_name,\" \",empmgr.last_name) action_by,adrs.approved_datetime,adrs.`remark` remark_approved\n" +
            "FROM address ad, address_request adr,address_request_status adrs,employee emp,employee empmgr,hr_admin hra,employment employ,divisi dv\n" +
            "WHERE ad.`address_id`=adr.`address_id` AND ad.`employee_no`=emp.`employee_no` AND adr.`request_no`=adrs.`request_no` AND emp.employee_no=employ.`employee_no` AND adrs.`hra_id` =hra.`hra_id` \n" +
            "AND hra.`employee_no`=empmgr.`employee_no` AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(adr.`request_date_time`,\"%Y-%m-%d\")>=employ.start_date AND DATE_FORMAT(adr.`request_date_time`,\"%Y-%m-%d\")<=employ.end_date  AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(adr.`request_date_time` ,\"%Y-%m-%d\") BETWEEN :startDate AND :endDate \n" +
            "AND adrs.`status`=nvl(:status,adrs.`status`) AND ad.`employee_no`=nvl(:employeeNo,ad.`employee_no`)\n" +
            "UNION ALL\n" +
            "SELECT emp.`employee_no`,CONCAT(emp.`first_name`,\" \",emp.last_name) AS fullname,employ.`position`,adr.`request_date_time`,adr.`request_no`,adrs.`status`,\n" +
            "\"-\" action_by,adrs.approved_datetime,adrs.`remark` remark_approved\n" +
            "FROM address ad,address_request adr,address_request_status adrs,employee emp,employment employ,divisi dv\n" +
            "WHERE ad.`address_id`=adr.`address_id` AND ad.`employee_no`=emp.`employee_no` AND adr.`request_no`=adrs.`request_no` AND emp.employee_no=employ.`employee_no` AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(adr.`request_date_time`,\"%Y-%m-%d\")>=employ.start_date AND DATE_FORMAT(adr.`request_date_time`,\"%Y-%m-%d\")<=employ.end_date  AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(adr.`request_date_time` ,\"%Y-%m-%d\") BETWEEN :startDate AND :endDate \n" +
            "AND adrs.`status`=nvl(:status,adrs.`status`) AND ad.`employee_no`=nvl(:employeeNo,ad.`employee_no`) AND adrs.`hra_id`IS NULL",nativeQuery = true)
    public List<Map<String,Object>> getAddressReport(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status,@Param("employeeNo") Long employeeNo);
}

