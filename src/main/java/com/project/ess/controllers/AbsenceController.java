package com.project.ess.controllers;

import com.project.ess.dto.AbsenceApproveDTO;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.AbsenceNeedApproveResponse;
import com.project.ess.model.AbsenceResponse;
import com.project.ess.services.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/absence")
public class AbsenceController {

    @Autowired
    AbsenceService absenceService;

    @PostMapping
    public ResponseEntity<CustomMessageWithRequestNo> addAbsence(@RequestParam("absence") String absence, @RequestParam("file")MultipartFile file, Authentication authentication){

        return absenceService.addAbsence(absence,file,authentication.getName());
    }

    @GetMapping("/request")
    public List<AbsenceResponse> getAllAbsence(Authentication authentication){

        return absenceService.getDataAbsence(authentication.getName());
    }

    @GetMapping("/request/needapprove")
    public List<AbsenceNeedApproveResponse> getListEmpAbsenceNeedApprove(Authentication authentication){

        return absenceService.getListNeedApprove(authentication.getName());
    }

    @GetMapping("/request/approved/history")
    public List<AbsenceNeedApproveResponse> getListHistoryAbsenceReq(Authentication authentication){

        return absenceService.getListHistoryAbsence(authentication.getName());
    }

    @PostMapping("/request/approve")
    public ResponseEntity<CustomMessageWithId> approveRequestAbsence(@RequestBody AbsenceApproveDTO request){


        return absenceService.approveRequestAbsence(request);
    }

    @GetMapping("request/cancel")
    public ResponseEntity<CustomMessageWithId> cancelRequestAbsence(@RequestParam("requestNo") String requestNo){

        return absenceService.cancelRequestAbsence(requestNo);
    }



}
