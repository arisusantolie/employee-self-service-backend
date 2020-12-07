package com.project.ess.services;

import com.project.ess.dto.BenefitPlanDTO;
import com.project.ess.entity.BenefitPlanEntity;
import com.project.ess.repository.BenefitPlanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenefitPlanService {

    @Autowired
    BenefitPlanRepository benefitPlanRepository;

    public BenefitPlanDTO createBenefitPlan(BenefitPlanDTO request){

        BenefitPlanEntity benefitPlanEntity=new BenefitPlanEntity();

        BeanUtils.copyProperties(request,benefitPlanEntity);
        benefitPlanRepository.save(benefitPlanEntity);

        return request;

    }

}
