package com.project.ess.repository;

import com.project.ess.entity.BenefitBalanceEntity;
import com.project.ess.entity.BenefitRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenefitRequestRepository extends JpaRepository<BenefitRequestEntity,Long> {

    public List<BenefitRequestEntity> findByBenefitBalanceIdOrderByRequestDateTimeDesc(BenefitBalanceEntity benefitBalanceEntity);
}
