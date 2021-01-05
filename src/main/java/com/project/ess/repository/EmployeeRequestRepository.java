package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.EmployeeRequestEntity;
import com.project.ess.model.EmployeeNeedApproveResponse;
import com.project.ess.model.EmployeeRequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeRequestRepository extends JpaRepository<EmployeeRequestEntity,Long> {
//
    @Query("select new com.project.ess.model.EmployeeRequestResponse(ere.employeeNo.employeeNo,ere.requestNo,ere.fileName,ere.requestDateTime,ers.status,ers.approvedDatetime," +
            "ers.remark,ere.requestData,ere.attachment)" +
            " from EmployeeRequestEntity ere,EmployeeRequestStatus ers where ere=ers.employeeRequestEntity and ere.employeeNo=:employee " +
            "order by ere.requestDateTime desc")
    public List<EmployeeRequestResponse> findData(@Param("employee") EmployeeEntity employeeEntity);



    @Query("select new com.project.ess.model.EmployeeNeedApproveResponse(ere.employeeNo.employeeNo,ere.requestData,ers.status,ers.approvedDatetime,ere.fileName,ere.requestNo,ere.attachment," +
            "ere.requestDateTime,concat(ere.employeeNo.firstName,' ',ere.employeeNo.lastName),ere.remark,ers.remark )" +
            " from EmployeeRequestEntity ere,EmployeeRequestStatus ers where ere=ers.employeeRequestEntity and ers.status='PENDING' " +
            " order by ere.requestDateTime asc")
    public List<EmployeeNeedApproveResponse> getListEmpNeedApprove();

    @Query("select new com.project.ess.model.EmployeeNeedApproveResponse(ere.employeeNo.employeeNo,ere.requestData,ers.status,ers.approvedDatetime,ere.fileName,ere.requestNo,ere.attachment," +
            "ere.requestDateTime,concat(ere.employeeNo.firstName,' ',ere.employeeNo.lastName),ere.remark,ers.remark )" +
            " from EmployeeRequestEntity ere,EmployeeRequestStatus ers where ere=ers.employeeRequestEntity and ers.hraId.employeeEntity=:employee and ers.status!='PENDING' " +
            " order by ere.requestDateTime asc")
    public List<EmployeeNeedApproveResponse> getListEmpHistory(@Param("employee") EmployeeEntity employee);



    public EmployeeRequestEntity findByRequestNo(String requestNo);

    @Query(value = "SELECT emp.`employee_no`,CONCAT(emp.`first_name`,\" \",emp.last_name) AS fullname,employ.`position`,er.`request_date_time`,er.`request_no`,ers.`remark` remark_request,ers.`status`,\n" +
            "CONCAT(empmgr.first_name,\" \",empmgr.last_name) action_by,ers.approved_datetime,ers.`remark` remark_approved\n" +
            "FROM employee_request er,employee_request_status ers,employee emp,employee empmgr,hr_admin hra,employment employ,divisi dv\n" +
            "WHERE er.`employee_no`=emp.`employee_no` AND er.`request_no`=ers.`request_no` AND emp.employee_no=employ.`employee_no` AND ers.`hra_id` =hra.`hra_id` \n" +
            "AND hra.`employee_no`=empmgr.`employee_no` AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(er.`request_date_time`,\"%Y-%m-%d\")>=employ.start_date AND DATE_FORMAT(er.`request_date_time`,\"%Y-%m-%d\")<=employ.end_date  AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(er.`request_date_time` ,\"%Y-%m-%d\") BETWEEN :startDate AND :endDate \n" +
            "AND ers.`status`=nvl(:status,ers.`status`) AND er.`employee_no`=nvl(:employeeNo,er.`employee_no`)\n" +
            "UNION ALL\n" +
            "SELECT emp.`employee_no`,CONCAT(emp.`first_name`,\" \",emp.last_name) AS fullname,employ.`position`,er.`request_date_time`,er.`request_no`,ers.`remark` remark_request,ers.`status`,\n" +
            "\"-\" action_by,ers.approved_datetime,ers.`remark` remark_approved\n" +
            "FROM employee_request er,employee_request_status ers,employee emp,employment employ,divisi dv\n" +
            "WHERE er.`employee_no`=emp.`employee_no` AND er.`request_no`=ers.`request_no` AND emp.employee_no=employ.`employee_no` AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(er.`request_date_time`,\"%Y-%m-%d\")>=employ.start_date AND DATE_FORMAT(er.`request_date_time`,\"%Y-%m-%d\")<=employ.end_date  AND employ.`divisi_id`=dv.`divisi_id` AND\n" +
            "DATE_FORMAT(er.`request_date_time` ,\"%Y-%m-%d\") BETWEEN :startDate  AND :endDate \n" +
            "AND ers.`status`=nvl(:status,ers.`status`) AND er.`employee_no`=nvl(:employeeNo,er.`employee_no`) AND ers.`hra_id`IS NULL; ",nativeQuery = true)
    public List<Map<String,Object>> getEmployeeReport(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status,@Param("employeeNo") Long employeeNo);
}
