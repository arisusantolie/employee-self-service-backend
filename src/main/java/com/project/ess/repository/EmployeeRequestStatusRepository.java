package com.project.ess.repository;

import com.project.ess.entity.EmployeeRequestEntity;
import com.project.ess.entity.approval.EmployeeRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRequestStatusRepository extends JpaRepository<EmployeeRequestStatus,Long> {

    public EmployeeRequestStatus findByEmployeeRequestEntity(EmployeeRequestEntity requestEntity);

}
