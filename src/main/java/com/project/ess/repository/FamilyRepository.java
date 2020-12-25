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

}
