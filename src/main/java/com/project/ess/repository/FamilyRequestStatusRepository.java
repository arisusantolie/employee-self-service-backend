package com.project.ess.repository;

import com.project.ess.entity.approval.FamilyRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRequestStatusRepository extends JpaRepository<FamilyRequestStatus,Long> {
}
