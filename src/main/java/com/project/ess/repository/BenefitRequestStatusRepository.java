package com.project.ess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.ess.entity.approval.BenefitRequestStatus;

import java.util.List;

public interface BenefitRequestStatusRepository extends JpaRepository<BenefitRequestStatus,Long> {


}
