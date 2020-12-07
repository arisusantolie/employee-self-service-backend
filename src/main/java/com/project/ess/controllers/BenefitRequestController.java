package com.project.ess.controllers;

import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.services.BenefitRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/benefit/request")
public class BenefitRequestController {

    @Autowired
    BenefitRequestService benefitRequestService;

    @PostMapping
    public ResponseEntity<CustomMessageWithRequestNo> addBenefit(@RequestParam("benefitrequest") String benefitRequest, @RequestParam("file") MultipartFile file){

        return benefitRequestService.createClaimBenefit(benefitRequest,file);
    }
}
