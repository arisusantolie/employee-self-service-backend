package com.project.ess.repository;

import com.project.ess.entity.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface FamilyRepository extends JpaRepository<FamilyEntity,Long> {

    @Query(value = "\n" +
            "SELECT f.*,frs.`status`,fr.`request_date_time` FROM family f, family_request fr,family_request_status frs WHERE f.`family_id`=fr.`family_id` AND fr.`request_no`=frs.request_no\n" +
            "AND f.`employee_no`=:employee   AND (fr.family_id,fr.request_date_time) IN \n" +
            "(SELECT fr.family_id,MAX(fr.request_date_time) FROM family_request fr,family_request_status frs WHERE fr.`family_id`=frs.`family_id` AND frs.`status`=\"APPROVED\" GROUP BY fr.family_id);",nativeQuery = true)
    public List<Map<String,Object>> getFamilyListByEmployeeNo(@Param("employee") Long employeeNo);

    @Modifying
    @Query("delete from FamilyEntity where familyId=:familyId")
    public void deleteFamilyById(@Param("familyId") Long familyId);


    @Query(value = "SELECT emp.`employee_no`,CONCAT(emp.`first_name`,\" \",emp.last_name) AS fullname,employ.`position`,adr.`request_date_time`,adr.`request_no`,adrs.`status`,\n" +
            "CONCAT(empmgr.first_name,\" \",empmgr.last_name) action_by,adrs.approved_datetime,adrs.`remark` remark_approved\n" +
            "FROM family ad, family_request adr,family_request_status adrs,employee emp,employee empmgr,hr_admin hra,employment employ,divisi dv\n" +
            "WHERE ad.family_id=adr.family_id AND ad.`employee_no`=emp.`employee_no` AND adr.`request_no`=adrs.`request_no` AND emp.employee_no=employ.`employee_no` AND adrs.`hra_id` =hra.`hra_id` \n" +
            "AND hra.`employee_no`=empmgr.`employee_no` AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(adr.`request_date_time`,\"%Y-%m-%d\")>=employ.start_date AND DATE_FORMAT(adr.`request_date_time`,\"%Y-%m-%d\")<=employ.end_date  AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(adr.`request_date_time` ,\"%Y-%m-%d\") BETWEEN :startDate AND :endDate \n" +
            "AND adrs.`status`=nvl(:status,adrs.`status`) AND ad.`employee_no`=nvl(:employeeNo,ad.`employee_no`)\n" +
            "UNION ALL\n" +
            "SELECT emp.`employee_no`,CONCAT(emp.`first_name`,\" \",emp.last_name) AS fullname,employ.`position`,adr.`request_date_time`,adr.`request_no`,adrs.`status`,\n" +
            "\"-\" action_by,adrs.approved_datetime,adrs.`remark` remark_approved\n" +
            "FROM family ad,family_request adr,family_request_status adrs,employee emp,employment employ,divisi dv\n" +
            "WHERE ad.family_id=adr.family_id AND ad.`employee_no`=emp.`employee_no` AND adr.`request_no`=adrs.`request_no` AND emp.employee_no=employ.`employee_no` AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(adr.`request_date_time`,\"%Y-%m-%d\")>=employ.start_date AND DATE_FORMAT(adr.`request_date_time`,\"%Y-%m-%d\")<=employ.end_date  AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(adr.`request_date_time` ,\"%Y-%m-%d\") BETWEEN :startDate AND :endDate  \n" +
            "AND adrs.`status`=nvl(:status,adrs.`status`) AND ad.`employee_no`=nvl(:employeeNo,ad.`employee_no`) AND adrs.`hra_id`IS NULL; ",nativeQuery = true)
    public List<Map<String,Object>> getFamilyReport(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status,@Param("employeeNo") Long employeeNo);

}
