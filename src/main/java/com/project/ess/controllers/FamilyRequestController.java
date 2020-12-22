package com.project.ess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.model.FamilyNeedApproveResponse;
import com.project.ess.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/family/request")
public class FamilyRequestController {
    @Autowired
    FamilyService familyService;

    @PostMapping
    public ResponseEntity<CustomMessageWithId> updateNewFamily(@RequestParam("family") String family, @RequestParam("file") MultipartFile file, Authentication authentication){

        return familyService.addOrUpdateFamily(family,file,authentication.getName());
    }

    @GetMapping()
    public List<FamilyNeedApproveResponse> getListFamilyRequest(Authentication authentication){

        return familyService.getListFamNeedApprove(authentication.getName());
    }

    @GetMapping("/needapprove")
    public List<FamilyNeedApproveResponse> getListFamilyReqNeedApprove(Authentication authentication){

        return familyService.getListFamNeedApprove(authentication.getName());
    }

    @GetMapping("/history")
    public List<FamilyNeedApproveResponse> getListHistoryFamilyReq(Authentication authentication){

        return familyService.getHistoryFamRequest(authentication.getName());
    }

    @GetMapping("cancel")
    public ResponseEntity<CustomMessageWithId> cancelRequestFamily(@RequestParam("requestNo") String requestNo) throws JsonProcessingException {

        return familyService.cancelFamilyRequest(requestNo);
    }
}
