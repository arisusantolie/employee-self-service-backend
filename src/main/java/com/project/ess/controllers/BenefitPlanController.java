package com.project.ess.controllers;

import com.project.ess.dto.BenefitPlanDTO;
import com.project.ess.services.BenefitPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/benefit/plan")
public class BenefitPlanController {

    @Autowired
    BenefitPlanService benefitPlanService;

    @PostMapping
    public BenefitPlanDTO createNewBenefitPlan(@RequestBody BenefitPlanDTO request){

        return benefitPlanService.createBenefitPlan(request);
    }
}
