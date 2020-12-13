package com.project.ess.controllers;

import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.BenefitNeedApproveResponse;
import com.project.ess.model.BenefitRequestResponse;
import com.project.ess.services.BenefitRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/benefit/request")
public class BenefitRequestController {

    @Autowired
    BenefitRequestService benefitRequestService;

    @PostMapping
    public ResponseEntity<CustomMessageWithRequestNo> addBenefit(@RequestParam("benefitrequest") String benefitRequest, @RequestParam("file") MultipartFile file,Authentication authentication){

        return benefitRequestService.createClaimBenefit(authentication.getName(),benefitRequest,file);
    }

    @GetMapping
    public List<BenefitRequestResponse> getListBenefitRequest(Authentication authentication){

        return benefitRequestService.getListBenefitRequest(authentication.getName());
    }

    @GetMapping("/needapprove")
    public List<BenefitNeedApproveResponse> getListBenefitRequestNeedApprove(Authentication authentication){

        return benefitRequestService.getListBenefitRequestNeedApprove(authentication.getName());
    }

    @GetMapping("/history")
    public List<BenefitNeedApproveResponse> getListHistoryBenefitRequest(Authentication authentication){

        return benefitRequestService.getListBenefitHistoryRequest(authentication.getName());
    }
}
