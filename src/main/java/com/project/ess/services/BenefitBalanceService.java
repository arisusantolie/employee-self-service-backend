package com.project.ess.services;

import com.project.ess.dto.BenefitBalanceDTO;
import com.project.ess.entity.BenefitBalanceEntity;
import com.project.ess.entity.BenefitPlanEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.model.BenefitBalanceResponse;
import com.project.ess.repository.BenefitBalanceRepository;
import com.project.ess.repository.BenefitPlanRepository;
import com.project.ess.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BenefitBalanceService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    BenefitPlanRepository benefitPlanRepository;

    @Autowired
    BenefitBalanceRepository benefitBalanceRepository;

    public BenefitBalanceResponse addEmployeeBalance(BenefitBalanceDTO request){



        EmployeeEntity employeeEntity=employeeRepository.findById(request.getEmployeeNo()).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        if(benefitBalanceRepository.findByEmployeeAndPeriod(employeeEntity,LocalDate.now().getYear()).isPresent()){
            throw new CustomGenericException("This employee has a balance in this period");
        }

        BenefitPlanEntity benefitPlanEntity=benefitPlanRepository.findById(request.getBenefitPlanId()).get();

        BenefitBalanceEntity benefitBalanceEntity=new BenefitBalanceEntity();
        benefitBalanceEntity.setEmployee(employeeEntity);
        benefitBalanceEntity.setBenefitPlan(benefitPlanEntity);
        benefitBalanceEntity.setBalanceLimit(BigDecimal.valueOf(50000000));

        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.with(firstDayOfYear());
        LocalDate lastDay = now.with(lastDayOfYear());

        benefitBalanceEntity.setStartDate(firstDay);
        benefitBalanceEntity.setEndDate(lastDay);
        benefitBalanceEntity.setPeriod(now.getYear());

        benefitBalanceRepository.save(benefitBalanceEntity);



        BenefitBalanceResponse returnValue=new BenefitBalanceResponse();

        BeanUtils.copyProperties(benefitBalanceEntity,returnValue);
        returnValue.setEmployeeNo(benefitBalanceEntity.getEmployee().getEmployeeNo());
        returnValue.setBenefitPlanId(benefitBalanceEntity.getBenefitPlan().getBenefitPlanId());
        return returnValue;
    }
}
