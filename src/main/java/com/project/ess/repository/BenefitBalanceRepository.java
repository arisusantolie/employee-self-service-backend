package com.project.ess.repository;

import com.project.ess.entity.BenefitBalanceEntity;
import com.project.ess.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BenefitBalanceRepository extends JpaRepository<BenefitBalanceEntity,Long> {

    public Optional<BenefitBalanceEntity> findByEmployeeAndPeriod(EmployeeEntity employeeEntity, int period);

    public Optional<BenefitBalanceEntity> findByEmployee(EmployeeEntity employeeEntity);

}
