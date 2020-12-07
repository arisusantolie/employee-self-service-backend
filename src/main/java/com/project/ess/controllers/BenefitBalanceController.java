package com.project.ess.controllers;

import com.project.ess.dto.BenefitBalanceDTO;
import com.project.ess.model.BenefitBalanceResponse;
import com.project.ess.services.BenefitBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/benefit/balance")
public class BenefitBalanceController {

    @Autowired
    BenefitBalanceService benefitBalanceService;

    @PostMapping
    public BenefitBalanceResponse addBalanceEmployee(@RequestBody BenefitBalanceDTO request){

        return benefitBalanceService.addEmployeeBalance(request);
    }
}
