package com.project.ess.repository;

import com.project.ess.entity.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface FamilyRepository extends JpaRepository<FamilyEntity,Long> {

    @Query(value = "SELECT f.*,fr.`status`,fr.`request_date_time` FROM family f, family_request fr WHERE f.`family_id`=fr.`family_id` \n" +
            "AND f.`employee_no`=2 AND (fr.family_id,fr.request_date_time) IN (SELECT family_id,MAX(request_date_time) FROM family_request GROUP BY family_id)",nativeQuery = true)
    public List<Map<String,Object>> getFamilyListByEmployeeNo(Long employeeNo);
}
